/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteClase;

/**
 *
 * @author Efren Narvaez
 */
public class Proforma {

    private int nProfCodigo;
    private int nProvCodigo;
    private int nProCodigo;
    private int cantidad;
    private String cProfSerie;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getnProCodigo() {
        return nProCodigo;
    }

    public void setnProCodigo(int nProCodigo) {
        this.nProCodigo = nProCodigo;
    }

    public int getnProfCodigo() {
        return nProfCodigo;
    }

    public void setnProfCodigo(int nProfCodigo) {
        this.nProfCodigo = nProfCodigo;
    }

    public int getnProvCodigo() {
        return nProvCodigo;
    }

    public void setnProvCodigo(int nProvCodigo) {
        this.nProvCodigo = nProvCodigo;
    }

    public String getcProfSerie() {
        return cProfSerie;
    }

    public void setcProfSerie(String cProfSerie) {
        this.cProfSerie = cProfSerie;
    } 
    
}