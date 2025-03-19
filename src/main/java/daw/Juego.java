/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

/**
 *
 * @author salvador
 */
public class Juego {
    
    private Celula[][] tablero;

    public Juego(int tamano) {
        this.tablero = new Celula[tamano][tamano];
    }
    
    public void matarCelula(int fila, int columna){
        tablero[fila][columna].muerte();
    }
    
    public void revivirCelula(int fila, int columna){
        tablero[fila][columna].vida();
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
    
    
}
