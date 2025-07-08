package co.sena.labventory.persistencia;

import co.sena.labventory.modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProveedorDAO {
    
    public void insertar(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedores (nombre_proveedor) VALUES (?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, proveedor.getNombreProveedor());
            pstmt.executeUpdate();
            System.out.println("Proveedor insertado con éxito.");

        } catch (SQLException e) {
            System.err.println("Error al insertar el proveedor: " + e.getMessage());
        }
    }
}