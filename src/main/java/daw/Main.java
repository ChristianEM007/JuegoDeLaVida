package daw;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author christian y salva
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String menu = """
                       -------------------------------------------------
                       | Hola Usuario! Bienvenido al Juego de la vida! |
                       | Elija que partida desee jugar:                |
                       |                                               |  
                       |            1.- Partida nueva                  |
                       |            2.- Cargar partida                 |
                       |            3.- Salir                          |
                       -------------------------------------------------
                      """;
        boolean salida = true;
        int opcion = 0;

        do {
            System.out.println(menu);

            try {
                opcion = sc.nextInt();

                if (opcion > 0 && opcion < 4) {
                    salida = false;
                } else {
                    System.out.println("Intentalo de nuevo tontito");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Intentalo de nuevo tontito");
                sc.nextLine();
            }
        } while (salida);

        switch (opcion) {
            case 1 -> {
            }
            case 2 -> {
            }
            case 3 -> {
                System.out.println("Saliendo.......");
                System.exit(0); // termina programa
            }
            default -> {
                System.out.println("esto no deberia salir...");
            }
        }
    }
}
