/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.TipoDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Efren Narvaez
 */
public abstract class BDTipoDocumento {
    
    public static void insertarTipoDocumento(TipoDocumento t) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into tipodocumento (cTipDocDescripcion,cTipDocSerie,cTipDocCodigo) values (?,?,?)");
        ps.setString(1, t.getcTipoDocDescripcion());
        ps.setString(2, t.getcTipDocSerie());
        ps.setString(3, t.getcTipDocCodigo());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static TipoDocumento buscarTipoDocumento(int codigo) throws SQLException {
        return buscarTipoDocumento("select nTipDocumento,cTipDocDescripcion,cTipDocSerie,cTipDocCodigo from tipodocumento where nTipDocumento="+codigo, null);
    }
    
    public static TipoDocumento buscarTipoDocumentoSerie(String serie) throws SQLException {
        return buscarTipoDocumento("select nTipDocumento,cTipDocDescripcion,cTipDocSerie,cTipDocCodigo from tipodocumento where cTipDocSerie='"+serie+"'", null);
    }
    
    public static TipoDocumento buscarTipoDocumentoCodigo(String codigo) throws SQLException {
        return buscarTipoDocumento("select nTipDocumento,cTipDocDescripcion,cTipDocSerie,cTipDocCodigo from tipodocumento where cTipDocCodigo='"+codigo+"'", null);
    }
    
    public static TipoDocumento buscarTipoDocumento(String sql,TipoDocumento t) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            t= new TipoDocumento(){};
            t.setnTipDocumento(rs.getInt("nTipDocumento"));
            t.setcTipoDocDescripcion(rs.getString("cTipDocDescripcion"));
            t.setcTipDocSerie(rs.getString("cTipDocSerie"));
            t.setcTipDocCodigo(rs.getString("cTipDocCodigo"));
        }
        cnn.close();
        ps.close();
        return t;
    }

    /*public static boolean eliminarCompra(int codcateg) throws SQLException {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps = cnn.prepareStatement("delete from COMPRA WHERE idcompra=?");
    ps.setInt(1, codcateg);
    int rowsUpdated = ps.executeUpdate();
    cnn.close();
    ps.close();
    if (rowsUpdated > 0) {
    return true;
    } else {
    return false;
    }
    }*/
    public static ArrayList<TipoDocumento> mostrarTipoDocumento() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<TipoDocumento> lista = new ArrayList<TipoDocumento>();

        ps = cnn.prepareStatement("select nTipDocumento,cTipDocDescripcion,cTipDocSerie,cTipDocCodigo from tipodocumento");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TipoDocumento t = new TipoDocumento() {
            };
            t.setnTipDocumento(rs.getInt("nTipDocumento"));
            t.setcTipoDocDescripcion(rs.getString("cTipDocDescripcion"));
            t.setcTipDocSerie(rs.getString("cTipDocSerie"));
            t.setcTipDocCodigo(rs.getString("cTipDocCodigo"));
            //
            lista.add(t);
        }
        cnn.close();
        ps.close();
        return lista;
    }
    
}