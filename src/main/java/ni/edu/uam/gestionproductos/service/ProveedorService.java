package ni.edu.uam.gestionproductos.service;

import ni.edu.uam.gestionproductos.dto.ProveedorRequestDTO;
import ni.edu.uam.gestionproductos.entity.Proveedor;
import ni.edu.uam.gestionproductos.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public List<Proveedor> listar() {
        return repository.findAll();
    }

    public Proveedor guardar(ProveedorRequestDTO dto) {
        Proveedor entidad = new Proveedor();
        entidad.setNombre(dto.getNombre());
        entidad.setTelefono(dto.getTelefono());
        entidad.setCorreo(dto.getCorreo());
        entidad.setActivo(dto.isActivo());
        return repository.save(entidad);
    }
}