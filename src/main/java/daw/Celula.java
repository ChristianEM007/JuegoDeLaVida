
package daw;

/**
 *
 * @author christian y salva
 */
public class Celula {
    
    private boolean viva;

    public Celula() {
        this.viva = false;
    }
    
    public Celula(boolean estado){
        this.viva = estado;
    }

    public boolean isViva() {
        return viva;
    }

    public void muerte() {
        this.viva = false;
    }

    public void vida() {
        this.viva = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char carViva = '\u25A0';
        char carMuerta = '\u25A1';
        if(viva){
            sb.append(carViva);
        }else{
            sb.append(carMuerta);
        }
        return sb.toString();
    }
}
