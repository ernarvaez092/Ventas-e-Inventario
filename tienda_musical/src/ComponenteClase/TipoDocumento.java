/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteClase;

/**
 *
 * @author Efren Narvaez
 */
public class TipoDocumento {

    private int nTipDocumento;
    private String cTipoDocDescripcion;
    private String cTipDocSerie;
    private String cTipDocCodigo;

    public String getcTipDocCodigo() {
        return cTipDocCodigo;
    }

    public void setcTipDocCodigo(String cTipDocCodigo) {
        this.cTipDocCodigo = cTipDocCodigo;
    }

    public String getcTipDocSerie() {
        return cTipDocSerie;
    }

    public void setcTipDocSerie(String cTipDocSerie) {
        this.cTipDocSerie = cTipDocSerie;
    }

    public String getcTipoDocDescripcion() {
        return cTipoDocDescripcion;
    }

    public void setcTipoDocDescripcion(String cTipoDocDescripcion) {
        this.cTipoDocDescripcion = cTipoDocDescripcion;
    }

    public int getnTipDocumento() {
        return nTipDocumento;
    }

    public void setnTipDocumento(int nTipDocumento) {
        this.nTipDocumento = nTipDocumento;
    }
}
