package ni.edu.uam.gestionproductos.service;

import ni.edu.uam.gestionproductos.dto.CategoriaRequestDTO;
import ni.edu.uam.gestionproductos.entity.Categoria;
import ni.edu.uam.gestionproductos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria guardar(CategoriaRequestDTO dto) {
        Categoria entidad = new Categoria();
        entidad.setNombre(dto.getNombre());
        entidad.setActiva(dto.getActiva() != null ? dto.getActiva() : true);
        return repository.save(entidad);
    }
}