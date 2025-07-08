package co.sena.labventory.vista;

import co.sena.labventory.modelo.ElementoInventario;
import co.sena.labventory.modelo.Proveedor;
import co.sena.labventory.persistencia.ConexionDB;
import co.sena.labventory.persistencia.ElementoInventarioDAO;
import co.sena.labventory.persistencia.ProveedorDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ElementoInventarioDAO elementoDAO = new ElementoInventarioDAO();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==== MENÚ DE INVENTARIO LABVENTORY ====");
            System.out.println("1. Ver todos los elementos del inventario");
            System.out.println("2. Agregar nuevo elemento al inventario");
            System.out.println("3. Actualizar un elemento del inventario");
            System.out.println("4. Eliminar un elemento del inventario");
            System.out.println("---------------------------------------");
            System.out.println("5. Ver todos los proveedores");
            System.out.println("6. Ver todas las categorías");
            System.out.println("7. Agregar nuevo proveedor");
            System.out.println("---------------------------------------");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                switch (opcion) {
                    case 1:
                        System.out.println("--- Lista de Elementos ---");
                        List<ElementoInventario> lista = elementoDAO.obtenerTodos();
                        if (lista.isEmpty()) {
                            System.out.println("No hay elementos en el inventario.");
                        } else {
                            for (ElementoInventario el : lista) {
                                System.out.println("ID: " + el.getIdElemento() + ", Nombre: " + el.getNombreElemento() + ", Existencias: " + el.getExistenciasElemento());
                            }
                        }
                        break;
                    case 2:
                        System.out.println("--- Agregar Nuevo Elemento ---");
                        ElementoInventario nuevo = new ElementoInventario();
                        System.out.print("Nombre del elemento: ");
                        nuevo.setNombreElemento(scanner.nextLine());
                        System.out.print("Existencias iniciales: ");
                        nuevo.setExistenciasElemento(scanner.nextInt());
                        System.out.print("ID del Laboratorio (ej: 1): ");
                        nuevo.setIdLaboratorio(scanner.nextInt());
                        System.out.print("ID del Proveedor (ej: 1): ");
                        nuevo.setIdProveedor(scanner.nextInt());
                        System.out.print("ID de la Categoría (ej: 1): ");
                        nuevo.setIdCategoria(scanner.nextInt());
                        scanner.nextLine(); // Limpiar buffer
                        elementoDAO.insertar(nuevo);
                        break;
                    case 3:
                        System.out.println("--- Actualizar Elemento ---");
                        System.out.print("Ingrese el ID del elemento a actualizar: ");
                        ElementoInventario actual = new ElementoInventario();
                        actual.setIdElemento(scanner.nextInt());
                        scanner.nextLine(); 
                        System.out.print("Nuevo nombre del elemento: ");
                        actual.setNombreElemento(scanner.nextLine());
                        System.out.print("Nuevas existencias: ");
                        actual.setExistenciasElemento(scanner.nextInt());
                        scanner.nextLine();
                        elementoDAO.actualizar(actual);
                        break;
                    case 4:
                        System.out.println("--- Eliminar Elemento ---");
                        System.out.print("Ingrese el ID del elemento a eliminar: ");
                        elementoDAO.eliminar(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    case 5:
                        verTablaSimple("proveedores", "id_proveedor", "nombre_proveedor");
                        break;
                    case 6:
                        verTablaSimple("categorias", "id_categoria", "nombre_categoria");
                        break;
                    case 7:
                        System.out.println("--- Agregar Nuevo Proveedor ---");
                        Proveedor nuevoProveedor = new Proveedor();
                        System.out.print("Nombre del nuevo proveedor: ");
                        nuevoProveedor.setNombreProveedor(scanner.nextLine());
                        proveedorDAO.insertar(nuevoProveedor);
                        break;
                    case 8:
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void verTablaSimple(String nombreTabla, String idColumna, String nombreColumna) {
        System.out.println("--- Lista de " + nombreTabla + " ---");
        String sql = "SELECT * FROM " + nombreTabla;
        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean encontrado = false;
            while (rs.next()) {
                encontrado = true;
                System.out.println("ID: " + rs.getInt(idColumna) + ", Nombre: " + rs.getString(nombreColumna));
            }
            if (!encontrado) {
                System.out.println("No hay registros en la tabla " + nombreTabla);
            }

        } catch (Exception e) {
            System.err.println("Error al consultar la tabla " + nombreTabla + ": " + e.getMessage());
        }
    }
}