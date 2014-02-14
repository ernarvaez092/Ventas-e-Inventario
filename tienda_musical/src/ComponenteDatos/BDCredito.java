/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Credito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Efren Narvaez
 */
public abstract class BDCredito {

    public static void insertarPagoCredito(Credito c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into credito (nVenCodigo,nCreNroCuotas,nCreMontoPorCuota,cCreEstado,nCreMontoDebito) values (?,?,?,?,?)");
        ps.setInt(1, c.getVenta().getnVenCodigo());
        ps.setInt(2, c.getnCreNroCuotas());
        ps.setDouble(3, c.getNcreMontoPorCuota());
        ps.setString(4, c.getcCreEstado());
        ps.setDouble(5, c.getnCreMontoDebito());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static Credito buscarCredito(int codigo) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Credito c = null;
        ps = cnn.prepareStatement("select nVenCodigo,nCreNroCuotas,nCreMontoPorCuota,cCreEstado,nCreMontoDebito from credito where nVenCodigo=?");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            c = new Credito() {
            };
            c.setVenta(BDVenta.buscarVenta(rs.getInt("nVenCodigo")));
            c.setnCreNroCuotas(rs.getInt("nCreNroCuotas"));
            c.setNcreMontoPorCuota(rs.getDouble("nCreMontoPorCuota"));
            c.setcCreEstado(rs.getString("cCreEstado"));
            c.setnCreMontoDebito(rs.getDouble("nCreMontoDebito"));
        }
        cnn.close();
        ps.close();
        return c;
    }
    
    public static boolean actualizarCronogramaPago(Credito c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update credito set cCreEstado=? where nVenCodigo="+c.getVenta().getnVenCodigo());
        ps.setString(1,c.getcCreEstado());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static ArrayList<Credito> mostrarPagoCredito() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Credito> lista = new ArrayList<Credito>();

        ps = cnn.prepareStatement("select nVenCodigo,nCreNroCuotas,nCreMontoPorCuota,cCreEstado,nCreMontoDebito from credito");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Credito c = new Credito() {
            };
            c.setVenta(BDVenta.buscarVenta(rs.getInt("nVenCodigo")));
            c.setnCreNroCuotas(rs.getInt("nCreNroCuotas"));
            c.setNcreMontoPorCuota(rs.getDouble("nCreMontoPorCuota"));
            c.setcCreEstado(rs.getString("cCreEstado"));
            c.setnCreMontoDebito(rs.getDouble("nCreMontoDebito"));
            lista.add(c);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}