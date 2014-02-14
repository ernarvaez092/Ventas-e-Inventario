
package ComponenteDatos;

import ComponenteClase.Contado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Efren Narvaez
 */
public abstract class BDContado {

    public static void insertarPagoContado(Contado c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into contado (nVenCodigo,cContEstado,nContMontoPagado) values (?,?,?)");
        ps.setInt(1, c.getVenta().getnVenCodigo());
        ps.setString(2, c.getcContEstado());
        ps.setDouble(3, c.getnContMontoPago());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    
    public static ArrayList<Contado> mostrarPagoContado() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Contado> lista = new ArrayList<Contado>();

        ps = cnn.prepareStatement("select nVenCodigo,cContEstado,nContMontoPagado from contado");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Contado c = new Contado() {
            };
            c.setVenta(BDVenta.buscarVenta(rs.getInt("nVenCodigo")));
            c.setcContEstado(rs.getString("cContEstado"));
            c.setnContMontoPago(rs.getDouble("nContMontoPago"));
            lista.add(c);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}