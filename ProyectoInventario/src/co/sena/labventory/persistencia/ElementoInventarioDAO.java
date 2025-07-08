package co.sena.labventory.persistencia;

import co.sena.labventory.modelo.ElementoInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa el patrón DAO (Data Access Object) para la tabla
 * Elementos_inventario.
 * Contiene todos los métodos para las operaciones CRUD (Crear, Leer, Actualizar,
 * Eliminar).
 */
public class ElementoInventarioDAO {

    /**
     * Inserta un nuevo elemento en la base de datos.
     * @param elemento El objeto ElementoInventario con los datos a insertar.
     */
    public void insertar(ElementoInventario elemento) {
        String sql = "INSERT INTO Elementos_inventario (nombre_elemento, existencias_elemento, id_proveedor, id_categoria, id_laboratorio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, elemento.getNombreElemento());
            pstmt.setInt(2, elemento.getExistenciasElemento());
            pstmt.setInt(3, elemento.getIdProveedor());
            pstmt.setInt(4, elemento.getIdCategoria());
            pstmt.setInt(5, elemento.getIdLaboratorio());

            pstmt.executeUpdate();
            System.out.println("Elemento insertado con éxito.");

        } catch (SQLException e) {
            System.err.println("Error al insertar el elemento: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los elementos de la base de datos.
     * @return Una lista de objetos ElementoInventario.
     */
    public List<ElementoInventario> obtenerTodos() {
        List<ElementoInventario> elementos = new ArrayList<>();
        String sql = "SELECT * FROM Elementos_inventario ORDER BY id_elemento";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ElementoInventario elemento = new ElementoInventario();
                elemento.setIdElemento(rs.getInt("id_elemento"));
                elemento.setNombreElemento(rs.getString("nombre_elemento"));
                elemento.setExistenciasElemento(rs.getInt("existencias_elemento"));
                elemento.setIdProveedor(rs.getInt("id_proveedor"));
                elemento.setIdCategoria(rs.getInt("id_categoria"));
                elemento.setIdLaboratorio(rs.getInt("id_laboratorio"));

                elementos.add(elemento);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los elementos: " + e.getMessage());
        }

        return elementos;
    }

    /**
     * Actualiza un elemento existente en la base de datos.
     * @param elemento El objeto ElementoInventario con el ID a actualizar y los nuevos datos.
     */
    public void actualizar(ElementoInventario elemento) {
        String sql = "UPDATE Elementos_inventario SET nombre_elemento = ?, existencias_elemento = ? WHERE id_elemento = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, elemento.getNombreElemento());
            pstmt.setInt(2, elemento.getExistenciasElemento());
            pstmt.setInt(3, elemento.getIdElemento());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Elemento actualizado con éxito.");
            } else {
                System.out.println("No se encontró el elemento con el ID proporcionado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el elemento: " + e.getMessage());
        }
    }

    /**
     * Elimina un elemento de la base de datos por su ID.
     * @param id El ID del elemento a eliminar.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM Elementos_inventario WHERE id_elemento = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Elemento eliminado con éxito.");
            } else {
                System.out.println("No se encontró el elemento con el ID proporcionado para eliminar.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el elemento: " + e.getMessage());
        }
    }
}