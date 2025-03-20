
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
    
    public void recorrerTablero(){
        for (int i = 0; i <= tablero.length; i++) {
            for (int j = 0; j <= tablero[0].length; j++) {
                if(tablero[i][j].isViva()){
                    if(mementoMori(i, j)){
                        tablero[i][j].muerte();
                    }
                }else{
                    if(comprobarRevivicion(i, j)){
                        tablero[i][j].vida();
                    }
                }
            }
        }
    }
    
    public int comprobarVecinas(int fila, int columna){
        
        int cantidad = 0;
        
        for (int i = fila-1; i <= fila + 1; i++) {
            for (int j = columna-1; j <= columna + 1; j++) {
                
                if(fila != i && columna != j){
                    if(tablero[i][j].isViva()){
                        cantidad++;
                    }
                }
            }
        }
        return cantidad;
    }
    
    public boolean comprobarRevivicion(int fila, int columna){
        
        int vecinas = comprobarVecinas(fila, columna);
        
        return vecinas == 3;
    }
    
    public boolean mementoMori(int fila, int columna){
        
        boolean morir = true;
        int vecinas = comprobarVecinas(fila, columna);
        
        if(vecinas <= 1 ){
            return morir;
        }else if(vecinas > 3){
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
    
    
}
