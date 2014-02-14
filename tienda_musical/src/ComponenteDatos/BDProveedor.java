/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Proveedor;
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
public abstract class BDProveedor {

    public static Proveedor insertarProveedor(Proveedor pv) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into proveedor (nProvNit,cProvNombre,cProvDireccion,cProvNroFax,cProvPaginaWeb,cProvEmail,cProvTipoTelefono,cProvNumTelefono,cProvEstado,cProvObservacion) values (?,?,?,?,?,?,?,?,?,?)");

        ps.setInt(1, pv.getnPRovNit());
        ps.setString(2, pv.getcProvNombre());
        ps.setString(3, pv.getcProvDireccion());
        ps.setString(4, pv.getcProvNroFax());
        ps.setString(5, pv.getcProvPaginaWeb());
        ps.setString(6, pv.getcProvEmail());
        ps.setString(7, pv.getcProvTipoTelefono());
        ps.setString(8, pv.getcProvNumTelefono());
        ps.setString(9, pv.getcProvEstado());
        ps.setString(10,pv.getcProvObservacion());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(nProvCodigo) from proveedor");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            int lastID = r.getInt(1);
            pv.setnProvCodigo(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return pv;
    }

    public static Proveedor buscarProveedor(int codigo) throws SQLException {
        return buscarProveedor("select nProvCodigo,nProvNit,cProvNombre,cProvDireccion,cProvNroFax,cProvPaginaWeb,cProvEmail,cProvTipoTelefono,cProvNumTelefono,cProvEstado,cProvObservacion from proveedor where nProvCodigo=" + codigo, null);
    }

    public static Proveedor buscarProveedorNombre(String nombre) throws SQLException {
        return buscarProveedor("select nProvCodigo,nProvNit,cProvNombre,cProvDireccion,cProvNroFax,cProvPaginaWeb,cProvEmail,cProvTipoTelefono,cProvNumTelefono,cProvEstado,cProvObservacion from proveedor where cProvNombre='" + nombre + "'", null);
    }

    public static Proveedor buscarProveedorNit(int nit) throws SQLException {
        return buscarProveedor("select nProvCodigo,nProvNit,cProvNombre,cProvDireccion,cProvNroFax,cProvPaginaWeb,cProvEmail,cProvTipoTelefono,cProvNumTelefono,cProvEstado,cProvObservacion from proveedor where nProvNit=" + nit, null);
    }

    public static Proveedor buscarProveedor(String sql, Proveedor pv) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement(sql);
        //ps.setString(1, Ruc);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (pv == null) {
                pv = new Proveedor() {
                };
            }
            pv.setnProvCodigo(rs.getInt("nProvCodigo"));
            pv.setnPRovNit(rs.getInt("nPRovNit"));
            pv.setcProvNombre(rs.getString("cProvNombre"));
            pv.setcProvDireccion(rs.getString("cProvDireccion"));
            pv.setcProvNroFax(rs.getString("cProvNroFax"));
            pv.setcProvPaginaWeb(rs.getString("cProvPaginaWeb"));
            pv.setcProvEmail(rs.getString("cProvEmail"));
            pv.setcProvTipoTelefono(rs.getString("cProvTipoTelefono"));
            pv.setcProvNumTelefono(rs.getString("cProvNumTelefono"));
            pv.setcProvEstado(rs.getString("cProvEstado"));
            pv.setcProvObservacion(rs.getString("cProvObservacion"));
        }
        cnn.close();
        ps.close();
        return pv;
    }

    /*public static boolean eliminarProveedor(String ruc) throws SQLException {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    
    ps = cnn.prepareStatement("delete from PROVEEDOR WHERE ruc=?");
    ps.setString(1, ruc);
    int rowsUpdated = ps.executeUpdate();
    cnn.close();
    ps.close();
    if (rowsUpdated > 0) {
    return true;
    } else {
    return false;
    }
    }*/
    public static boolean actualizarProveedor(Proveedor p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("update proveedor set nProvNit=?,cProvNombre=?,cProvDireccion=?,cProvNroFax=?,cProvPaginaWeb=?,cProvEmail=?,cProvTipoTelefono=?,cProvNumTelefono=?,cProvEstado=?,cProvObservacion=? where nProvCodigo=" + p.getnProvCodigo());
        ps.setInt(1, p.getnPRovNit());
        ps.setString(2, p.getcProvNombre());
        ps.setString(3, p.getcProvDireccion());
        ps.setString(4, p.getcProvNroFax());
        ps.setString(5, p.getcProvPaginaWeb());
        ps.setString(6, p.getcProvEmail());
        ps.setString(7, p.getcProvTipoTelefono());
        ps.setString(8, p.getcProvNumTelefono());
        ps.setString(9, p.getcProvEstado());
        ps.setString(10, p.getcProvObservacion());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Proveedor> mostrarProveedor() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();

        ps = cnn.prepareStatement("select nProvCodigo,nProvNit,cProvNombre,cProvDireccion,cProvNroFax,cProvPaginaWeb,cProvEmail,cProvTipoTelefono, cProvNumTelefono,cProvEstado,cProvObservacion from proveedor");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Proveedor pv = new Proveedor() {
            };
            pv.setnProvCodigo(rs.getInt(1));
            pv.setnPRovNit(rs.getInt(2));
            pv.setcProvNombre(rs.getString(3));
            pv.setcProvDireccion(rs.getString(4));
            pv.setcProvNroFax(rs.getString(5));
            pv.setcProvPaginaWeb(rs.getString(6));
            pv.setcProvEmail(rs.getString(7));
            pv.setcProvTipoTelefono(rs.getString(8));
            pv.setcProvNumTelefono(rs.getString(9));
            pv.setcProvEstado(rs.getString(10));
            pv.setcProvObservacion(rs.getString(11));
            lista.add(pv);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    public static ArrayList<Proveedor> listarProveedorPorNombre(String nombre) {
        return listar("cProvNombre", nombre + "%", "like");
    }
    public static ArrayList<Proveedor> listarProveedorPorNit(String nit) {
        return listar("nProvNit", nit+ "%", "like");
    }
    public static ArrayList<Proveedor> listarProveedorPorCodigo(String codigo) {
        return listar("nProvCodigo", codigo + "%", "like");
    }

    private static ArrayList<Proveedor> listar(String atributo, String parametro, String comparador) {
        return consultarSQL("select nProvCodigo,nProvNit,cProvNombre,cProvDireccion,cProvNroFax,cProvPaginaWeb,cProvEmail,cProvTipoTelefono,cProvNumTelefono,cProvEstado,cProvObservacion from proveedor where " + atributo + " " + comparador + " '" + parametro + "'");
    }

    private static ArrayList<Proveedor> consultarSQL(String sql) {
        ArrayList<Proveedor> list = new ArrayList<Proveedor>();
        Connection cn = BD.getConnection();
        try {
            Proveedor p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new Proveedor();
                p.setnProvCodigo(rs.getInt("nProvCodigo"));
                p.setnPRovNit(rs.getInt("nProvNit"));
                p.setcProvNombre(rs.getString("cProvNombre"));
                p.setcProvDireccion(rs.getString("cProvDireccion"));
                p.setcProvNroFax(rs.getString("cProvNroFax"));
                p.setcProvPaginaWeb(rs.getString("cProvPaginaWeb"));
                p.setcProvEmail(rs.getString("cProvEmail"));
                p.setcProvTipoTelefono(rs.getString("cProvTipoTelefono"));
                p.setcProvNumTelefono(rs.getString("cProvNumTelefono"));
                p.setcProvEstado(rs.getString("cProvEstado"));
                p.setcProvObservacion(rs.getString("cProvObservacion"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
}