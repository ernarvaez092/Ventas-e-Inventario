/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.CronogramaPago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Efren Narvaez
 */
public abstract class BDCronogramaPago {

    public static void insertarPagoCredito(CronogramaPago cp) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into cronogramapago (nNroCuota,cCPagoFechaInicio,cCPagoFecha,nCPagoCuota,nCPagoRecibido,nVenCodigo) values (?,?,?,?,?,?)");
        ps.setInt(1, cp.getnNroCuota());
        ps.setDate(2, new java.sql.Date(cp.getcCPagoFechaInicio().getTime()));
        ps.setDate(3, new java.sql.Date(cp.getcCPagoFecha().getTime()));
        ps.setDouble(4, cp.getnCPagoCuota());
        ps.setDouble(5, cp.getnCPagoRecibido());
        ps.setInt(6, cp.getVenta().getnVenCodigo());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static boolean actualizarCronogramaPago(CronogramaPago cp,int nroCuota) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update cronogramapago set nCPagoRecibido=? where nNroCuota="+nroCuota+" and nVenCodigo="+cp.getVenta().getnVenCodigo());
        ps.setDouble(1, cp.getnCPagoRecibido());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static ArrayList<CronogramaPago> mostrarCronogramaPago() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<CronogramaPago> lista = new ArrayList<CronogramaPago>();

        ps = cnn.prepareStatement("select nNroCuota,nCPagoCodigo,cCPagoFechaInicio,cCPagoFecha,nCPagoCuota,nCPagoRecibido,nVenCodigo from cronogramapago");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CronogramaPago cp = new CronogramaPago() {
            };
            cp.setnNroCuota(rs.getInt("nNroCuota"));
            cp.setnCPagoCodigo(rs.getInt("nCPagoCodigo"));
            cp.setcCPagoFecha((java.util.Date) rs.getDate("cCPagoFechaInicio"));
            cp.setcCPagoFecha((java.util.Date) rs.getDate("cCPagoFecha"));
            cp.setnCPagoCuota(rs.getDouble("nCPagoCuota"));
            cp.setnCPagoRecibido(rs.getDouble("nCPagoRecibido"));
            cp.setVenta(BDVenta.buscarVenta(rs.getInt("nVenCodigo")));
            lista.add(cp);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    public static ArrayList<CronogramaPago> listarCronogramaPagoPorVenta(int nVenCodigo) {
        return consultarSQL("select nNroCuota,nCPagoCodigo,cCPagoFechaInicio,cCPagoFecha,nCPagoCuota,nCPagoRecibido,nVenCodigo from cronogramapago where nVenCodigo="+nVenCodigo);
    }

    private static ArrayList<CronogramaPago> consultarSQL(String sql) {
        ArrayList<CronogramaPago> list = new ArrayList<CronogramaPago>();
        Connection cn = BD.getConnection();
        try {
            CronogramaPago cp;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cp = new CronogramaPago() {};
                cp.setnNroCuota(rs.getInt("nNroCuota"));
                cp.setnCPagoCodigo(rs.getInt("nCPagoCodigo"));
                cp.setcCPagoFecha((java.util.Date) rs.getDate("cCPagoFechaInicio"));
                cp.setcCPagoFecha((java.util.Date) rs.getDate("cCPagoFecha"));
                cp.setnCPagoCuota(rs.getDouble("nCPagoCuota"));
                cp.setnCPagoRecibido(rs.getDouble("nCPagoRecibido"));
                cp.setVenta(BDVenta.buscarVenta(rs.getInt("nVenCodigo")));
                list.add(cp);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
}