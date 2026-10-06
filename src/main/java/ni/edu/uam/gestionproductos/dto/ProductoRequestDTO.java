package ni.edu.uam.gestionproductos.dto;

import java.math.BigDecimal;

public class ProductoRequestDTO {

    private String codigo;
    private String nombre;
    private BigDecimal precioVenta;
    private Integer existencia;
    private Integer categoriaId;

    // Opcionales (columnas agregadas en V2 y V3)
    private String descripcion;
    private Integer proveedorId;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }

    public Integer getExistencia() { return existencia; }
    public void setExistencia(Integer existencia) { this.existencia = existencia; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getProveedorId() { return proveedorId; }
    public void setProveedorId(Integer proveedorId) { this.proveedorId = proveedorId; }
}
