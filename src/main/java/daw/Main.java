package daw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author christian y salva
 */
public class Main {

    public static final String MAP_KEY_TAMANIO = "Tamaños";
    public static final String MAP_KEY_NUM_GEN = "NumeroGeneracion";
    public static final String MAP_KEY_FILAS = "Filas";

    public static void main(String[] args) {
        // MENU PRINCIPAL ------------------------------------------------------
        int opcion = menu();

        Juego generacionAct = new Juego(0);
        boolean partidaCargada = false;
        Map<String, String> datosPartida = new HashMap<>();

        switch (opcion) {
            // NUEVA PARTIDA ---------------------------------------------------
            case 1 -> {
                System.out.println("Has elegido 'Nueva partida'. COMENZANDO...");
                // TAMAÑO DEL TABLERO
                int tamaño = pregTamanio();

                // INICIALIZAMOS EL TABLERO
                generacionAct = new Juego(tamaño);

                opcion = menu1_5();
                if (opcion == 1) {
                    // PORCENTAJE DE CELULAS VIVAS 
                    int porcentaje = pregPorcentaje();
                    int numVivas = ((tamaño * tamaño) * porcentaje) / 100;

                    // INICIALIZAR LAS CELULAS VIVAS
                    generacionAct.inicioPartida(numVivas);
                } else {
                    // POSICIONES DE CELULAS VIVAS
                    String pos = pregPosiciones();
                    String[] posiciones = pos.split(",");

                    // INICIALIZAR LAS CELULAS VIVAS
                    generacionAct.inicioPartidaPosiciones(posiciones);
                }
            }
            case 2 -> {
                // CARGAR PARTIDA ----------------------------------------------
                System.out.println("Cargando...");

                partidaCargada = true;
                String nombreFichero = "partidaCelulas.txt";
                datosPartida = cargarPartida(nombreFichero);

                // INICIALIZAMOS EL TABLERO
                String[] tamanio = datosPartida.get(MAP_KEY_TAMANIO).split(" ");
                generacionAct = new Juego(Integer.parseInt(tamanio[0]));

                // INICIALIZAR LAS CELULAS VIVAS
                generacionAct.inicioCargarPartida(datosPartida.get(MAP_KEY_FILAS));
            }
            case 3 -> {
                // BORRAR PARTIDA ----------------------------------------------
                System.out.println("Borrando...");
                File archivo = new File("/home/christian/NetBeansProjects/JuegoDeLaVida/partidaCelulas.txt");
                
                if(archivo.exists()){
                    archivo.delete();
                    System.out.println("Eliminada exitosamente");
                    
                }else{
                    System.out.println("No tenias ninguna partida guardada :D");
                }
                
                System.exit(0);
                
            }
            case 4 ->{
                // SALIR DEL JUEGO ---------------------------------------------
                System.out.println("Saliendo.......");
                System.exit(0); // Termina programa
            }
            default -> {
                System.out.println("Esto no deberia salir...");
            }
        }

        // LOOP JUGABLE --------------------------------------------------------
            int numeroGen = (partidaCargada) ? Integer.parseInt(datosPartida.get(MAP_KEY_NUM_GEN)) : 1;
            String mensaje = (partidaCargada) ? "La generacion cargada es: " : "La primera generacion es: ";
            System.out.println(mensaje);
            System.out.println(generacionAct.toString());
        
        List<Juego> historico = new ArrayList<>();
        historico.add(generacionAct);
        List<Integer> numeroCelVivasGen = new ArrayList<>();
        numeroCelVivasGen.add(generacionAct.comprobarVivas());

        boolean salidaJuego = false;
        do {
            // MENU 2 ----------------------------------------------------------
            opcion = menu2();

            if (opcion == 1) {
                // SIGUIENTE GENERACION ----------------------------------------
                Juego generacionNueva = generacionAct.recorrerTablero();
                System.out.println("Generación anterior ----------------------");
                System.out.print(generacionAct.toString());
                System.out.println("Numero de células vivas: " + generacionAct.comprobarVivas());
                numeroGen++;
                System.out.println("Generación actual ------------------------ Nº: " + numeroGen);
                System.out.print(generacionNueva.toString());
                System.out.println("Numero de células vivas: " + generacionNueva.comprobarVivas());
                numeroCelVivasGen.add(generacionNueva.comprobarVivas());

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
                    generacionAct.guardarPartida(generacionAct.getTamanio(), numeroGen, numeroCelVivasGen);
                }

                System.out.println("Gracias por jugar");
                salidaJuego = true;
            }
        } while (!salidaJuego);
    }

    public static Map<String, String> cargarPartida(String idFichero) {
        Map<String, String> datos = new HashMap<>();
        String linea;
        try (Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            linea = datosFichero.nextLine();
            datos.put(MAP_KEY_TAMANIO, linea);
            linea = datosFichero.nextLine();
            datos.put(MAP_KEY_NUM_GEN, linea);
            StringBuilder filas = new StringBuilder();
            do {
                linea = datosFichero.nextLine();
                filas.append(linea);
            } while (!linea.contains(";"));
            datos.put(MAP_KEY_FILAS, filas.toString());
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return datos;
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
                       |            3.- Borrar partida                 |
                       |            4.- Salir                          |
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

    public static String pregPosiciones() {
        Scanner sc = new Scanner(System.in);
        String porcentaje;
        String pregunta = "Ahora elija las posiciones de las celulas vivas(0-0,0-1,0-2...): ";
        do {
            try {
                System.out.print(pregunta);
                porcentaje = sc.nextLine();
                while (porcentaje.matches(".\\D*[^0-9,-]")) {
                    System.out.println("El dato solo tiene que tener numeros, la coma y el guion");
                    System.out.print(pregunta);
                    porcentaje = sc.nextLine();
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("El dato solo tiene que tener numeros, la coma y el guion");
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
