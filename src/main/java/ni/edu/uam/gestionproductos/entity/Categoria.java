package ni.edu.uam.gestionproductos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Boolean activa;

    // --- ESTA ES LA PARTE IMPORTANTE ---
    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria") // Ignora el atributo 'categoria' dentro de los productos para evitar el ciclo infinito
    private List<Producto> productos;

    // Getters y Setters (Asegúrate de tenerlos todos)

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }

    // ¡Vital para que el JSON lo muestre!
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}