package daw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author christian y salva
 */
public class Juego {

    private final Celula[][] tablero;

    public Juego(int tamano) {
        this.tablero = new Celula[tamano][tamano];

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                this.tablero[i][j] = new Celula();
            }
        }
    }

    public Juego(Celula[][] tableroNuevo) {
        this.tablero = tableroNuevo;
    }

    public int getTamanio() {
        return tablero.length;
    }

    public void inicioPartida(int numVivas) {
        Random rand = new Random();
        while (numVivas > 0) {
            int fila = rand.nextInt(0, tablero.length);
            int columna = rand.nextInt(0, tablero.length);
            if (!(tablero[fila][columna].isViva())) {
                tablero[fila][columna].vida();
                numVivas--;
            }
        }
    }

    public void inicioPartidaPosiciones(String[] posiciones) {
        for (String pos : posiciones) {
            String[] numeros = pos.split("-");
            if (casillaValida(Integer.parseInt(numeros[0]), Integer.parseInt(numeros[1]))) {
                tablero[Integer.parseInt(numeros[0])][Integer.parseInt(numeros[1])].vida();
            }
        }
    }
    
    public void inicioCargarPartida(String filas) {
        filas = filas.replace(";", "");
        String[] vivas = filas.split(" ");

        int fila = 0;
        int columna = 0;
        for (String viva : vivas) {
            if (viva.equals("1")) {
                tablero[fila][columna].vida();
            }
            columna++;
            if (columna == tablero.length) {
                columna = 0;
                fila++;
            }
        }
    }

    public int comprobarVivas() {
        int resultado = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j].isViva()) {
                    resultado++;
                }
            }
        }
        return resultado;
    }

    public Juego recorrerTablero() {
        Celula[][] resultado = new Celula[tablero.length][tablero.length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j].isViva()) {
                    resultado[i][j] = (mementoMori(i, j)) ? new Celula(false) : new Celula(true);
                } else {
                    resultado[i][j] = (comprobarRevivicion(i, j)) ? new Celula(true) : new Celula(false);
                }
            }
        }
        Juego nuevaGen = new Juego(resultado);
        return nuevaGen;
    }

    public int comprobarVecinas(int fila, int columna) {
        int cantidad = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if ((fila != i || columna != j) && casillaValida(i, j)) {
                    if (tablero[i][j].isViva()) {
                        cantidad++;
                    }
                }
            }
        }
        return cantidad;
    }

    public boolean casillaValida(int fila, int columna) {
        return (0 <= fila && fila < tablero.length) && (0 <= columna && columna < tablero.length);
    }

    public boolean comprobarRevivicion(int fila, int columna) {
        int vecinas = comprobarVecinas(fila, columna);
        return vecinas == 3;
    }

    public boolean mementoMori(int fila, int columna) {
        int vecinas = comprobarVecinas(fila, columna);
        return vecinas <= 1 || vecinas > 3;
    }

    public void guardarPartida(int tamanio, int numeroGen, List<Integer> numeroCelVivasGen) {
        String idFichero = "partidaCelulas.txt";
        String tmp;
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            flujo.write(String.valueOf(tamanio) + " " + String.valueOf(tamanio));
            flujo.newLine();
            flujo.write(String.valueOf(numeroGen));
            flujo.newLine();
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    tmp = (tablero[i][j].isViva()) ? "1" : "0";
                    flujo.write(tmp + " ");
                }
                if (i == tablero[i].length - 1) {
                    flujo.write(";");
                }
                flujo.newLine();
            }
            for (int i = 0; i < numeroCelVivasGen.size(); i++) {
                if (i != numeroCelVivasGen.size() - 1) {
                    flujo.write(String.valueOf(numeroCelVivasGen.get(i)) + " ");
                } else {
                    flujo.write(String.valueOf(numeroCelVivasGen.get(i)));
                }
            }
            flujo.flush();
            System.out.println("Partida guardada correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                sb.append(tablero[i][j].toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Juego other = (Juego) obj;
        return Arrays.deepEquals(this.tablero, other.tablero);
    }

}
