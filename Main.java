package gestorbiblioteca;

import java.util.Scanner;

/**
 *
 * @author zakar
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;
        Biblioteca biblioMostoles = new Biblioteca();

        System.out.println("--- Bienvenido al Sistema de Biblioteca ---");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Agregar libro");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Mostrar catálogo");
        System.out.println("6. Mostrar usuarios");
        System.out.println("7. Salir");

        System.out.println("Seleccione una opción");
        opcion = sc.nextInt();

        //validar opcion
        while (opcion < 1 || opcion > 7) {
            System.out.println("opcion no valdia , vuelve a ingresar");
            opcion = sc.nextInt();
        }

        while (opcion != 7) {
            biblioMostoles.ofrecerServicio(opcion);
            System.out.println("\n");
            System.out.println("Seleccione una opción :");
            opcion = sc.nextInt();
        }

    }
}
