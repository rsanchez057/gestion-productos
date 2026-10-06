# gestion-productos (Práctica Guiada #2)

API REST con Spring Boot 3, Spring Data JPA, PostgreSQL y Flyway.
Arquitectura: Controller -> Service -> Repository -> PostgreSQL.

## Ejecutar
1. `CREATE DATABASE gestion_productos;` (sin tablas; las crea Flyway)
2. Ajustar usuario/clave en `src/main/resources/application.properties`
3. `mvn spring-boot:run` (Java 21)

## Endpoints
| Método | Ruta | Descripción |
|---|---|---|
| GET/POST | /api/categorias | Listar / crear |
| GET/POST | /api/proveedores | Listar / crear |
| GET/POST | /api/etiquetas | Listar / crear |
| GET | /api/productos | Listar |
| GET | /api/productos/{id} | Buscar |
| POST | /api/productos | Crear (ProductoRequestDTO) |
| PUT | /api/productos/{id} | Actualizar |
| DELETE | /api/productos/{id} | Eliminar (204) |
| GET | /api/productos/categoria/{categoriaId} | Productos por categoría |
| POST | /api/productos/{productoId}/etiquetas/{etiquetaId} | Asociar etiqueta |
| DELETE | /api/productos/{productoId}/etiquetas/{etiquetaId} | Quitar asociación (Reto 1) |
| GET | /api/productos/etiqueta/{etiquetaId} | Productos por etiqueta (Reto 2) |

## JSON de ejemplo
POST /api/productos
{"codigo":"TEC-001","nombre":"Teclado mecánico","precioVenta":75.50,"existencia":20,"categoriaId":2}

(opcionales: "descripcion", "proveedorId")

POST /api/etiquetas  {"nombre":"Oferta"}

## Migraciones
V1 categoria/producto · V2 descripcion · V3 proveedor · V4 etiqueta + producto_etiqueta
