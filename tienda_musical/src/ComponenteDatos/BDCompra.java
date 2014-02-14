/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Compra;
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
public class BDCompra {

    public static void insertarCompra(Compra c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into compra (nProvCodigo,nProCodigo,cComFecha,nComCantidad,nComPrecio,nComPrecioTotal,cComSerie,cComCodigo) values (?,?,?,?,?,?,?,?)");
        ps.setInt(1, c.getProveedor().getnProvCodigo());
        ps.setInt(2, c.getProducto().getnProCodigo());
        ps.setDate(3, new java.sql.Date(c.getcComFecha().getTime()));
        ps.setInt(4, c.getnComCantidad());
        ps.setDouble(5, c.getnComPrecio());
        ps.setDouble(6, c.getnComPrecioTotal());
        ps.setString(7, c.getcComSerie());
        ps.setString(8, c.getcComCodigo());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static Compra buscarCompra(int codigo) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Compra c = null;
        ps = cnn.prepareStatement("select nComCodigo,nProvCodigo,cComFecha,nProCodigo,nComCantidad,nComPrecio,nComPrecioTotal,cComSerie,cComCodigo from compra where nComCodigo=?");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            c = new Compra() {
            };
            c.setnComCodigo(rs.getInt("nComCodigo"));
            c.setProveedor(BDProveedor.buscarProveedor(rs.getInt("nProvCodigo")));
            c.setcComFecha((java.util.Date) rs.getDate("cComFecha"));
            c.setProducto(BDProducto.buscarProducto(rs.getInt("nProCodigo")));
            c.setnComCantidad(rs.getInt("nComCantidad"));
            c.setnComPrecio(rs.getDouble("nComPrecio"));
            c.setnComPrecioTotal(rs.getDouble("nComPrecioTotal"));
            c.setcComSerie(rs.getString("cComSerie"));
            c.setcComCodigo(rs.getString("cComCodigo"));
        }
        cnn.close();
        ps.close();
        return c;
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
    public static ArrayList<Compra> mostrarCompra() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Compra> lista = new ArrayList<Compra>();

        ps = cnn.prepareStatement("select nComCodigo,nProvCodigo,cComFecha,nProCodigo,nComCantidad,nComPrecio,nComPrecioTotal,cComSerie,cComCodigo from compra");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Compra c = new Compra() {
            };
            c.setnComCodigo(rs.getInt("nComCodigo"));
            c.setProveedor(BDProveedor.buscarProveedor(rs.getInt("nProvCodigo")));
            c.setcComFecha((java.util.Date) rs.getDate("cComFecha"));
            c.setProducto(BDProducto.buscarProducto(rs.getInt("nProCodigo")));
            c.setnComCantidad(rs.getInt("nComCantidad"));
            c.setnComPrecio(rs.getDouble("nComPrecio"));
            c.setnComPrecioTotal(rs.getDouble("nComPrecioTotal"));
            c.setcComSerie(rs.getString("cComSerie"));
            c.setcComCodigo(rs.getString("cComCodigo"));
            lista.add(c);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    public static ArrayList<Compra> listarCompraPorCodigo(String codigo) {
        return consultarSQL("select c.nComCodigo,c.nProvCodigo,c.cComFecha from compra c inner join proveedor p on p.nProvCodigo = c.nProvcodigo where c.nComCodigo like '%"+codigo+"%' group by nProvCodigo,c.cComFecha order by c.cComFecha");
    }

    public static ArrayList<Compra> listarCompraPorProveedor(String prov) {
        //return listar("p.cProvNombre", prov + "%", "like");
        return consultarSQL("select c.nComCodigo,c.nProvCodigo,c.cComFecha from compra c inner join proveedor p on p.nProvCodigo = c.nProvcodigo where p.cProvNombre like '%"+prov+"%' group by nProvCodigo,c.cComFecha order by c.cComFecha");
    }

    public static ArrayList<Compra> listarCompraProProveedor(int codprov,String fecha) {
      return consultarSQLProv("select nProCodigo,nComPrecio,nComPrecioTotal,nComCantidad from compra where nProvCodigo=" + codprov+" and DATE_FORMAT(cComFecha, '%Y-%m-%d')='"+fecha+"'");
    }

    /*private static ArrayList<Compra> listar(String atributo, String parametro, String comparador) {
        return consultarSQL("select c.nComCodigo,c.cComFecha,c.nProvCodigo,sum(c.nComCantidad)as Cantidad from compra c inner join proveedor p on p.nProvCodigo = c.nProvcodigo  where " + atributo + " " + comparador + " '" + parametro + "' group by c.nProvCodigo,c.nComCodigo,c.cComFecha");
    }*/

    private static ArrayList<Compra> consultarSQL(String sql) {
        ArrayList<Compra> list = new ArrayList<Compra>();
        Connection cn = BD.getConnection();
        try {
            Compra c;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = new Compra() {
                };
                c.setnComCodigo(rs.getInt("nComCodigo"));
                c.setProveedor(BDProveedor.buscarProveedor(rs.getInt("nProvCodigo")));
                c.setcComFecha((java.util.Date) rs.getDate("cComFecha"));               
                list.add(c);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
    
    private static ArrayList<Compra> consultarSQLProv(String sql) {
        ArrayList<Compra> list = new ArrayList<Compra>();
        Connection cn = BD.getConnection();
        try {
            Compra c;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = new Compra() {
                };
                c.setProducto(BDProducto.buscarProducto(rs.getInt("nProCodigo")));
                c.setnComCantidad(rs.getInt("nComCantidad"));
                c.setnComPrecio(rs.getDouble("nComPrecio"));
                c.setnComPrecioTotal(rs.getDouble("nComPrecioTotal"));
                list.add(c);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
}
