package ni.edu.uam.gestionproductos.service;

import ni.edu.uam.gestionproductos.dto.ProductoRequestDTO;
import ni.edu.uam.gestionproductos.entity.Categoria;
import ni.edu.uam.gestionproductos.entity.Etiqueta;
import ni.edu.uam.gestionproductos.entity.Producto;
import ni.edu.uam.gestionproductos.entity.Proveedor;
import ni.edu.uam.gestionproductos.repository.CategoriaRepository;
import ni.edu.uam.gestionproductos.repository.EtiquetaRepository;
import ni.edu.uam.gestionproductos.repository.ProductoRepository;
import ni.edu.uam.gestionproductos.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EtiquetaRepository etiquetaRepository;
    private final ProveedorRepository proveedorRepository;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository,
                           EtiquetaRepository etiquetaRepository,
                           ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.etiquetaRepository = etiquetaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));
    }

    public Producto guardar(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        aplicarDatos(producto, dto);
        return productoRepository.save(producto);
    }

    public Producto actualizar(Integer id, ProductoRequestDTO dto) {
        Producto producto = buscarPorId(id);
        aplicarDatos(producto, dto);
        return productoRepository.save(producto);
    }

    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> listarPorCategoria(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    public List<Producto> listarPorEtiqueta(Integer etiquetaId) {
        return productoRepository.findByEtiquetasId(etiquetaId);
    }

    @Transactional
    public Producto agregarEtiqueta(Integer productoId, Integer etiquetaId) {
        Producto producto = buscarPorId(productoId);

        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId)
                .orElseThrow(() ->
                        new RuntimeException("Etiqueta no encontrada"));

        producto.getEtiquetas().add(etiqueta);

        return productoRepository.save(producto);
    }

    // Reto 1: elimina SOLO la asociación (fila de producto_etiqueta).
    @Transactional
    public Producto quitarEtiqueta(Integer productoId, Integer etiquetaId) {
        Producto producto = buscarPorId(productoId);

        boolean removida = producto.getEtiquetas()
                .removeIf(e -> e.getId().equals(etiquetaId));

        if (!removida) {
            throw new RuntimeException("El producto no tiene asociada esa etiqueta");
        }

        return productoRepository.save(producto);
    }

    // Copia los datos del DTO a la entidad (compartido por crear y actualizar).
    private void aplicarDatos(Producto producto, ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository
                .findById(dto.getCategoriaId())
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada"));

        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setExistencia(dto.getExistencia() != null ? dto.getExistencia() : 0);
        producto.setCategoria(categoria);

        if (dto.getDescripcion() != null) {
            producto.setDescripcion(dto.getDescripcion());
        }

        if (dto.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository
                    .findById(dto.getProveedorId())
                    .orElseThrow(() ->
                            new RuntimeException("Proveedor no encontrado"));
            producto.setProveedor(proveedor);
        }
    }
}
