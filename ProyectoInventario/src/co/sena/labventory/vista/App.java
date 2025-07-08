package co.sena.labventory.vista;

import co.sena.labventory.modelo.ElementoInventario;
import co.sena.labventory.persistencia.ElementoInventarioDAO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ElementoInventarioDAO dao = new ElementoInventarioDAO();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==== MENÚ DE INVENTARIO LABVENTORY ====");
            System.out.println("1. Ver todos los elementos");
            System.out.println("2. Agregar nuevo elemento");
            System.out.println("3. Actualizar un elemento");
            System.out.println("4. Eliminar un elemento");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                switch (opcion) {
                    case 1:
                        System.out.println("--- Lista de Elementos ---");
                        List<ElementoInventario> lista = dao.obtenerTodos();
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
                        System.out.print("ID del Proveedor: ");
                        nuevo.setIdProveedor(scanner.nextInt());
                        System.out.print("ID de la Categoría: ");
                        nuevo.setIdCategoria(scanner.nextInt());
                        System.out.print("ID del Laboratorio: ");
                        nuevo.setIdLaboratorio(scanner.nextInt());
                        scanner.nextLine(); // Limpiar buffer
                        dao.insertar(nuevo);
                        break;
                    case 3:
                        System.out.println("--- Actualizar Elemento ---");
                        System.out.print("Ingrese el ID del elemento a actualizar: ");
                        ElementoInventario actual = new ElementoInventario();
                        actual.setIdElemento(scanner.nextInt());
                        scanner.nextLine(); // Limpiar buffer
                        System.out.print("Nuevo nombre del elemento: ");
                        actual.setNombreElemento(scanner.nextLine());
                        System.out.print("Nuevas existencias: ");
                        actual.setExistenciasElemento(scanner.nextInt());
                        scanner.nextLine(); // Limpiar buffer
                        dao.actualizar(actual);
                        break;
                    case 4:
                        System.out.println("--- Eliminar Elemento ---");
                        System.out.print("Ingrese el ID del elemento a eliminar: ");
                        dao.eliminar(scanner.nextInt());
                        scanner.nextLine(); // Limpiar buffer
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer para evitar un bucle infinito
            }
        }
        scanner.close();
    }
}