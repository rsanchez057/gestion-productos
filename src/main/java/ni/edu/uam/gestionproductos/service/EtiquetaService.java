package ni.edu.uam.gestionproductos.service;

import ni.edu.uam.gestionproductos.dto.EtiquetaRequestDTO;
import ni.edu.uam.gestionproductos.entity.Etiqueta;
import ni.edu.uam.gestionproductos.repository.EtiquetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaService {

    private final EtiquetaRepository repository;

    public EtiquetaService(EtiquetaRepository repository) {
        this.repository = repository;
    }

    public List<Etiqueta> listar() {
        return repository.findAll();
    }

    public Etiqueta guardar(EtiquetaRequestDTO dto) {
        Etiqueta entidad = new Etiqueta();
        entidad.setNombre(dto.getNombre());
        return repository.save(entidad);
    }
}