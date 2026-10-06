package ni.edu.uam.gestionproductos.controller;

import ni.edu.uam.gestionproductos.dto.EtiquetaRequestDTO;
import ni.edu.uam.gestionproductos.entity.Etiqueta;
import ni.edu.uam.gestionproductos.service.EtiquetaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    private final EtiquetaService service;

    public EtiquetaController(EtiquetaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Etiqueta> listar() {
        return service.listar();
    }

    @PostMapping
    public Etiqueta guardar(@RequestBody EtiquetaRequestDTO dto) {
        return service.guardar(dto);
    }
}