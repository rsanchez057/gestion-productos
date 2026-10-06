package ni.edu.uam.gestionproductos.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrity(DataIntegrityViolationException ex) {
        // Mensaje genérico; si quieres puedes inspeccionar ex.getMostSpecificCause() para un texto más concreto.
        String msg = "Violación de integridad en la base de datos.";
        if (ex.getMostSpecificCause() != null) {
            msg += " " + ex.getMostSpecificCause().getMessage();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);
    }
}