package ni.edu.uam.gestionproductos.controller;

import ni.edu.uam.gestionproductos.dto.LoginRequest;
import ni.edu.uam.gestionproductos.dto.LoginResponse;
import ni.edu.uam.gestionproductos.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokens;

    public AuthController(AuthenticationManager manager, TokenService tokens) {
        this.manager = manager;
        this.tokens = tokens;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest datos) {
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(datos.username(), datos.password())
        );
        return ResponseEntity.ok(new LoginResponse(tokens.crearToken(auth), "Bearer"));
    }

    // RETO ADICIONAL: Endpoint protegido que devuelve información del JWT
    @GetMapping("/perfil")
    public Map<String, Object> perfil(Authentication auth) {
        return Map.of(
                "usuario", auth.getName(),
                "roles", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );
    }
}