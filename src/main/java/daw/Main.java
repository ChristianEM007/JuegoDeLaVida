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
                System.out.println("Estamos en mantenimiento vuelva pronto");
            }
            case 2 -> {
                System.out.println("Estamos en mantenimiento vuelva en un siglo");
            }
            case 3 -> {
                System.out.println("Saliendo.......");
                System.exit(0); // termina programa
            }
            default -> {
                System.out.println("esto no deberia salir...");
            }
        }

        // loop jugable
        boolean salidaJuego = false;
        do {
            String menu2 = """
                       -------------------------------------------------
                       |               Juego de la vida                |
                       |               Elija la opcion:                |
                       |                                               |  
                       |            1.- Siguiente generacion           |
                       |            2.- Terminar partida               |
                       |                                               |
                       -------------------------------------------------
                      """;
            salida = true;  // reutilizamos salida
            opcion = 0;
            sc.nextLine(); //limpiamos scanner
            do {
                System.out.println(menu2);

                try {
                    opcion = sc.nextInt();

                    if (opcion > 0 && opcion < 3) {
                        salida = false;
                    } else {
                        System.out.println("Intentalo de nuevo tontito");
                    }
                } catch (InputMismatchException ime) {
                    System.out.println("Intentalo de nuevo tontito");
                    sc.nextLine();
                }
            } while (salida);

            if (opcion == 1) {

            } else {

                String menu3 = """
                       -------------------------------------------------
                       |               Juego de la vida                |
                       |               ¿Guardar Partida?               |
                       |                                               |  
                       |                    1.- Si                     |
                       |                    2.- No                     |
                       |                                               |
                       -------------------------------------------------
                      """;
                salida = true;  // reutilizamos salida
                opcion = 0;
                sc.nextLine(); //limpiamos scanner
                do {
                    System.out.println(menu3);

                    try {
                        opcion = sc.nextInt();

                        if (opcion > 0 && opcion < 3) {
                            salida = false;
                        } else {
                            System.out.println("Intentalo de nuevo tontito");
                        }
                    } catch (InputMismatchException ime) {
                        System.out.println("Intentalo de nuevo tontito");
                        sc.nextLine();
                    }
                } while (salida);
                
                if(opcion == 1){
                    //guardar partida ---------------------------------------------------
                }
                
                System.out.println("Gracias por jugar");
                salidaJuego = true;
            }

        } while (!salidaJuego);

    }
}
