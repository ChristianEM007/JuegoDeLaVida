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
// MENU PRINCIPAL --------------------------------------------------------------
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

        Juego generacionAc;
                
        switch (opcion) {

            case 1 -> {
// NUEVA PARTIDA ---------------------------------------------------------------
                System.out.println("Has elegido 'Nueva partida'. COMENZANDO...");
                int tamaño = 0;
                int porcentaje = 0;
                salida = true;
    // TAMAÑO DEL TABLERO
                do {
                    //comprobamos que el usuario no la lie
                    System.out.println("Elige el tamaño del tablero (max 25):");
                    try {
                        tamaño = sc.nextInt();

                        if (tamaño < 26) {
                            salida = false;
                        } else {
                            System.out.println("Intentalo de nuevo tontito ya te dije que maximo 25");
                        }
                    } catch (InputMismatchException ime) {
                        System.out.println("Intentalo de nuevo tontito");
                        sc.nextLine();
                    }
                } while (salida);
                
                generacionAc = new Juego(tamaño);
    // PORCENTAJE DE CELULAS VIVAS 
                salida = true;
                //comprobamos que el usuario no la lie
                do {
                    System.out.println("Ahora elija el porcentaje de celulas vivas:");
                    try {
                        porcentaje = sc.nextInt();

                        if (porcentaje < 101) {
                            salida = false;
                        } else {
                            System.out.println("Intentalo de nuevo tontito ya te dije que maximo 25");
                        }
                    } catch (InputMismatchException ime) {
                        System.out.println("Intentalo de nuevo tontito");
                        sc.nextLine();
                    }
                } while (salida);
                
                int numVivas = (tamaño*porcentaje)/100;
                generacionAc.inicioPartida(numVivas);//************************************************************************en proceso
            }
            case 2 -> {
// CARGAR PARTIDA --------------------------------------------------------------

                System.out.println("Estamos en mantenimiento vuelva en un siglo");
            }
            case 3 -> {
// SALIR DEL JUEGO -------------------------------------------------------------
                System.out.println("Saliendo.......");
                System.exit(0); // termina programa
            }
            default -> {
                System.out.println("esto no deberia salir...");
            }
        }

// LOOP JUGABLE ----------------------------------------------------------------
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
// MENU 2 ----------------------------------------------------------------------
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
// SIGUIENTE GENERACION --------------------------------------------------------
                
                
                
            } else {
// MENU 3 (GUARDAR PARTIDA)-----------------------------------------------------
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

                if (opcion == 1) {
// GUARDAR PARTIDA -------------------------------------------------------------
                    System.out.println("mantenimiento");
                }

                System.out.println("Gracias por jugar");
                salidaJuego = true;
            }

        } while (!salidaJuego);

    }
}
