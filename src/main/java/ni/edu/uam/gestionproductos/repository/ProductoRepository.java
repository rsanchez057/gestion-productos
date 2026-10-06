package ni.edu.uam.gestionproductos.repository;

import ni.edu.uam.gestionproductos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Consulta derivada: producto.categoria.id = ?
    List<Producto> findByCategoriaId(Integer categoriaId);

    // Consulta derivada sobre la relación N:N: producto.etiquetas.id = ?
    List<Producto> findByEtiquetasId(Integer etiquetaId);
}
