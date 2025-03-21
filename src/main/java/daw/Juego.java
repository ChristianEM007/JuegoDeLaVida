package daw;

import java.util.Arrays;
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
    
    public Juego(Celula[][] tableroNuevo){
        this.tablero = tableroNuevo;
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
    
    public int comprobarVivas(){
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
                    resultado[i][j] = (mementoMori(i, j))? new Celula(false) : new Celula(true);
                } else {
                    resultado[i][j] = (comprobarRevivicion(i, j))? new Celula(true) : new Celula(false);
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
    
    public boolean casillaValida(int fila, int columna){
        return (0<=fila&&fila<tablero.length)&&(0<=columna&&columna<tablero.length);
    }

    public boolean comprobarRevivicion(int fila, int columna) {
        int vecinas = comprobarVecinas(fila, columna);
        return vecinas == 3;
    }

    public boolean mementoMori(int fila, int columna) {
        boolean morir = true;
        int vecinas = comprobarVecinas(fila, columna);

        if (vecinas <= 1) {
            return morir;
        } else if (vecinas > 3) {
            return morir;
        }
        return false;
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
