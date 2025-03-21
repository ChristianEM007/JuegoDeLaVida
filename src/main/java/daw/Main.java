package daw;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author christian y salva
 */
public class Main {

    public static void main(String[] args) {
        // MENU PRINCIPAL ------------------------------------------------------
        int opcion = menu();

        Juego generacionAct = new Juego(0);

        switch (opcion) {
            // NUEVA PARTIDA ---------------------------------------------------
            case 1 -> {
                System.out.println("Has elegido 'Nueva partida'. COMENZANDO...");
                // TAMAÑO DEL TABLERO
                int tamaño = pregTamanio();

                // INICIALIZAMOS EL TABLERO
                generacionAct = new Juego(tamaño);

                //opcion = menu1_5(); *******************************************Falta preguntar al usuario por coordenadas concretas de las celulas, espabila christian
                /*
                if(opcion==1){
                    // PORCENTAJE DE CELULAS VIVAS 
                    int porcentaje = pregPorcentaje();
                    int numVivas = (tamaño * tamaño * porcentaje) / 100;

                    // INICIALIZAR LAS CELULAS VIVAS
                    generacionAct.inicioPartida(numVivas);
                } else {
                    String[] posiciones = pregPosiciones();
                    generacionAct.inicioPartidaPosiciones(posiciones);
                }
                */
                // PORCENTAJE DE CELULAS VIVAS 
                int porcentaje = pregPorcentaje();
                int numVivas = (tamaño * tamaño * porcentaje) / 100;

                // INICIALIZAR LAS CELULAS VIVAS
                generacionAct.inicioPartida(numVivas);
            }
            case 2 -> {
                // CARGAR PARTIDA ----------------------------------------------
                System.out.println("Estamos en mantenimiento vuelva en un siglo");
                System.exit(0); // Esto es temporal mientras no se completa
            }
            case 3 -> {
                // SALIR DEL JUEGO ---------------------------------------------
                System.out.println("Saliendo.......");
                System.exit(0); // Termina programa
            }
            default -> {
                System.out.println("Esto no deberia salir...");
            }
        }

        // LOOP JUGABLE --------------------------------------------------------
        System.out.println("La primera generacion es: ");
        System.out.println(generacionAct.toString());
        boolean salidaJuego = false;
        List<Juego> historico = new ArrayList<>();
        historico.add(generacionAct);
        do {
            // MENU 2 ----------------------------------------------------------
            opcion = menu2();

            if (opcion == 1) {
                // SIGUIENTE GENERACION ----------------------------------------
                Juego generacionNueva = generacionAct.recorrerTablero();
                System.out.println("Generación anterior ------------------------");
                System.out.print(generacionAct.toString());
                System.out.println("Numero de células vivas: " + generacionAct.comprobarVivas());
                System.out.println("Generación actual ------------------------");
                System.out.print(generacionNueva.toString());
                System.out.println("Numero de células vivas: " + generacionNueva.comprobarVivas());

                // COMPROBAMOS LA CONDICION DE DERROTA -------------------------
                historico.add(generacionNueva);
                if (historico.size() == 4) {
                    historico.remove(0);
                    salidaJuego = condicionDerrota(historico);
                }
                if (salidaJuego) {
                    System.out.println("El juego no ha avanzado en los últimos 3 turnos. Has perdido");
                }
                generacionAct = generacionNueva;
            } else {
                // MENU 3 (GUARDAR PARTIDA)-------------------------------------
                opcion = menu3();

                if (opcion == 1) {
                    // GUARDAR PARTIDA -----------------------------------------
                    System.out.println("La funcion está en mantenimiento");
                }

                System.out.println("Gracias por jugar");
                salidaJuego = true;
            }
        } while (!salidaJuego);
    }

    public static int menu() {
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
                       Respuesta: """;
        int opcion;
        do {
            try {
                System.out.print(menu);
                opcion = sc.nextInt();
                while (opcion < 1 || opcion > 3) {
                    System.out.println("El dato tiene que ser 1, 2 o 3");
                    System.out.print(menu);
                    opcion = sc.nextInt();
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("El dato tiene que ser numerico");
                sc.nextLine();
            }
        } while (true);
        return opcion;
    }

    public static int menu1_5() {
        Scanner sc = new Scanner(System.in);
        String menu2 = """
                       -------------------------------------------------
                       |               Juego de la vida                |
                       |               Elija la opcion:                |
                       |                                               |  
                       |            1.- Iniciar por porcentaje         |
                       |            2.- Iniciar por posiciones         |
                       |                                               |
                       -------------------------------------------------
                       Respuesta: """;
        int opcion;
        do {
            try {
                System.out.print(menu2);
                opcion = sc.nextInt();
                while (opcion < 1 || opcion > 2) {
                    System.out.println("El dato tiene que ser 1 o 2");
                    System.out.print(menu2);
                    opcion = sc.nextInt();
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("El dato tiene que ser numerico");
                sc.nextLine();
            }
        } while (true);
        return opcion;
    }

    public static int menu2() {
        Scanner sc = new Scanner(System.in);
        String menu2 = """
                       -------------------------------------------------
                       |               Juego de la vida                |
                       |               Elija la opcion:                |
                       |                                               |  
                       |            1.- Siguiente generacion           |
                       |            2.- Terminar partida               |
                       |                                               |
                       -------------------------------------------------
                       Respuesta: """;
        int opcion;
        do {
            try {
                System.out.print(menu2);
                opcion = sc.nextInt();
                while (opcion < 1 || opcion > 2) {
                    System.out.println("El dato tiene que ser 1 o 2");
                    System.out.print(menu2);
                    opcion = sc.nextInt();
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("El dato tiene que ser numerico");
                sc.nextLine();
            }
        } while (true);
        return opcion;
    }

    public static int menu3() {
        Scanner sc = new Scanner(System.in);
        String menu3 = """
                       -------------------------------------------------
                       |               Juego de la vida                |
                       |               ¿Guardar Partida?               |
                       |                                               |  
                       |                    1.- Si                     |
                       |                    2.- No                     |
                       |                                               |
                       -------------------------------------------------
                       Respuesta: """;
        int opcion;
        do {
            try {
                System.out.print(menu3);
                opcion = sc.nextInt();
                while (opcion < 1 || opcion > 2) {
                    System.out.println("El dato tiene que ser 1 o 2");
                    System.out.print(menu3);
                    opcion = sc.nextInt();
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("El dato tiene que ser numerico");
                sc.nextLine();
            }
        } while (true);
        return opcion;
    }

    public static int pregTamanio() {
        Scanner sc = new Scanner(System.in);
        int tamaño;
        String pregunta = "Elige el tamaño del tablero (max 25): ";
        do {
            try {
                System.out.print(pregunta);
                tamaño = sc.nextInt();
                while (tamaño > 25 || tamaño < 1) {
                    System.out.println("El dato tiene que ser entre 1 y 25");
                    System.out.print(pregunta);
                    tamaño = sc.nextInt();
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("El dato tiene que ser numerico");
                sc.nextLine();
            }
        } while (true);
        return tamaño;
    }

    public static int pregPorcentaje() {
        Scanner sc = new Scanner(System.in);
        int porcentaje;
        String pregunta = "Ahora elija el porcentaje de celulas vivas: ";
        do {
            try {
                System.out.print(pregunta);
                porcentaje = sc.nextInt();
                while (porcentaje > 100 || porcentaje < 1) {
                    System.out.println("El dato tiene que ser entre 1 y 100");
                    System.out.print(pregunta);
                    porcentaje = sc.nextInt();
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("El dato tiene que ser numerico");
                sc.nextLine();
            }
        } while (true);
        return porcentaje;
    }

    public static boolean condicionDerrota(List<Juego> historico) {
        Juego primero = historico.get(0);
        for (int i = 0; i < historico.size(); i++) {
            if (!primero.equals(historico.get(i))) {
                return false;
            }
        }
        return true;
    }
}
