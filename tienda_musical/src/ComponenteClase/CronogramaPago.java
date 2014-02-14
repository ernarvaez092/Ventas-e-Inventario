/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteClase;

import java.util.Date;
/**
 *
 * @author Efren Narvaez
 */
public class CronogramaPago {

    private int nCPagoCodigo;
    private int nNroCuota;
    private Date cCPagoFechaInicio;
    private Date cCPagoFecha;
    private Double nCPagoCuota;
    private Double nCPagoRecibido;
    private Venta venta;

    public int getnNroCuota() {
        return nNroCuota;
    }

    public void setnNroCuota(int nNroCuota) {
        this.nNroCuota = nNroCuota;
    }

    public Date getcCPagoFechaInicio() {
        return cCPagoFechaInicio;
    }

    public void setcCPagoFechaInicio(Date cCPagoFechaInicio) {
        this.cCPagoFechaInicio = cCPagoFechaInicio;
    }

    public Date getcCPagoFecha() {
        return cCPagoFecha;
    }

    public void setcCPagoFecha(Date cCPagoFecha) {
        this.cCPagoFecha = cCPagoFecha;
    }
    
    public int getnCPagoCodigo() {
        return nCPagoCodigo;
    }

    public void setnCPagoCodigo(int nCPagoCodigo) {
        this.nCPagoCodigo = nCPagoCodigo;
    }

    public Double getnCPagoCuota() {
        return nCPagoCuota;
    }

    public void setnCPagoCuota(Double nCPagoCuota) {
        this.nCPagoCuota = nCPagoCuota;
    }

    public Double getnCPagoRecibido() {
        return nCPagoRecibido;
    }

    public void setnCPagoRecibido(Double nCPagoRecibido) {
        this.nCPagoRecibido = nCPagoRecibido;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}