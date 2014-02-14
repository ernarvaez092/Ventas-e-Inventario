/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Transaccion;
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
public abstract class BDTransaccion {

    public static void insertarTransaccion(Transaccion t) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into transaccion (nVenCodigo,nProCodigo,nTraPrecio,nTraCantidad,nTraPrecioTotal) values(?,?,?,?,?)");
        ps.setInt(1, t.getVenta().getnVenCodigo());
        ps.setInt(2, t.getProducto().getnProCodigo());
        ps.setDouble(3, t.getnTraPrecio());
        ps.setInt(4, t.getNtraCantidad());
        ps.setDouble(5, t.getnTraPrecioTotal());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    /*public static boolean eliminarTransaccion(int idcomp,int idprod) throws SQLException {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps = cnn.prepareStatement("delete from TRANSACCION WHERE idcompra=? and idProducto=?");
    ps.setInt(1, idcomp);
    ps.setInt(2, idprod);
    int rowsUpdated = ps.executeUpdate();
    cnn.close();
    ps.close();
    if (rowsUpdated > 0) {
    return true;
    } else {
    return false;
    }
    }*/
    public static ArrayList<Transaccion> mostrarTransaccion() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Transaccion> lista = new ArrayList<Transaccion>();

        ps = cnn.prepareStatement("select nTraCodigo,nVenCodigo,nProCodigo,nTraPrecio,nTraCantidad,nTraPrecioTotal from transaccion");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Transaccion t = new Transaccion() {
            };
            t.setnTraCodigo(rs.getInt(1));
            t.setVenta(BDVenta.buscarVenta(rs.getInt(2)));
            t.setProducto(BDProducto.buscarProducto(rs.getInt(3)));
            t.setnTraPrecio(rs.getDouble(4));
            t.setNtraCantidad(rs.getInt(5));
            t.setnTraPrecioTotal(rs.getDouble(6));
            lista.add(t);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    
    public static ArrayList<Transaccion> listarTransaccionVenta(int nventa) {
        return consultarSQL("select nTraCodigo,nVenCodigo,nProCodigo,nTraPrecio,nTraCantidad,nTraPrecioTotal from transaccion where nVenCodigo=" + nventa);
    }

    private static ArrayList<Transaccion> consultarSQL(String sql) {
        ArrayList<Transaccion> list = new ArrayList<Transaccion>();
        Connection cn = BD.getConnection();
        try {
            Transaccion t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                t = new Transaccion() {
                };
                t.setnTraCodigo(rs.getInt(1));
                t.setVenta(BDVenta.buscarVenta(rs.getInt(2)));
                t.setProducto(BDProducto.buscarProducto(rs.getInt(3)));
                t.setnTraPrecio(rs.getDouble(4));
                t.setNtraCantidad(rs.getInt(5));
                t.setnTraPrecioTotal(rs.getDouble(6));
                list.add(t);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
}