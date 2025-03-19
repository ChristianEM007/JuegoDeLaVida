/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

/**
 *
 * @author salvador
 */
public class Celula {
    
    private boolean viva;

    public Celula() {
        this.viva = false;
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
        if(viva){
            sb.append("0");
        }else{
            sb.append("1");
        }
        return sb.toString();
    }
}
