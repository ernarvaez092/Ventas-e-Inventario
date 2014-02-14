/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Proforma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Efren Narvaez
 */
public class BDProforma {
     public static Proforma insertarProforma(Proforma p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into proforma (nProvCodigo,nProCodigo,nCantidad,cProfSerie) values (?,?,?,?)");
        ps.setInt(1, p.getnProvCodigo());
        ps.setInt(2, p.getnProCodigo());
        ps.setInt(3, p.getCantidad());
        ps.setString(4,p.getcProfSerie());
        ps.executeUpdate();
        cnn.close();
        ps.close();
        return p;
    }
}
