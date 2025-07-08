package co.sena.labventory.modelo;

/**
 * Esta clase representa el Modelo de un Elemento del Inventario.
 * Actúa como un "molde" para crear objetos de tipo ElementoInventario.
 */
public class ElementoInventario {

    // 1. Atributos (corresponden a las columnas de la tabla)
    private int idElemento;
    private String nombreElemento;
    private int existenciasElemento;
    private int idProveedor;
    private int idCategoria;
    private int idLaboratorio;

    // 2. Constructores (métodos especiales para crear objetos)

    // Constructor vacío (necesario para ciertas operaciones)
    public ElementoInventario() {
    }

    // Constructor con todos los parámetros (para crear objetos completos)
    public ElementoInventario(int idElemento, String nombreElemento, int existenciasElemento, int idProveedor, int idCategoria) {
        this.idElemento = idElemento;
        this.nombreElemento = nombreElemento;
        this.existenciasElemento = existenciasElemento;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
    }


    // 3. Getters y Setters (métodos para acceder y modificar los atributos)
    // Esto se conoce como "Encapsulamiento"

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public int getExistenciasElemento() {
        return existenciasElemento;
    }

    public void setExistenciasElemento(int existenciasElemento) {
        this.existenciasElemento = existenciasElemento;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // 4. Método toString (para mostrar la información del objeto de forma legible)
    // Muy útil para hacer pruebas en la consola.
    @Override
    public String toString() {
        return "ElementoInventario{" +
                "id=" + idElemento +
                ", nombre='" + nombreElemento + '\'' +
                ", existencias=" + existenciasElemento +
                '}';
    }
    public int getIdLaboratorio() {
        return idLaboratorio;
    }
    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }
}