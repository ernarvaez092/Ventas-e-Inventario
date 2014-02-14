/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteClase;

/**
 *
 * @author Efren Narvaez
 */
public class Contado {

    private Venta venta;
    private Double nContMontoPago;
    private String cContEstado;

    public String getcContEstado() {
        return cContEstado;
    }

    public void setcContEstado(String cContEstado) {
        this.cContEstado = cContEstado;
    }

    public Double getnContMontoPago() {
        return nContMontoPago;
    }

    public void setnContMontoPago(Double nContMontoPago) {
        this.nContMontoPago = nContMontoPago;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
