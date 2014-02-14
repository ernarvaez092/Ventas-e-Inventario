/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Usuario;
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
public abstract class BDUsuario {

    public static void insertarUsuario(Usuario u) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into usuario (cUsuId,cUsuClave,nPerCodigo,cUsuEstado) values (?,?,?,?)");
        ps.setString(1, u.getcUsuId());
        ps.setString(2, u.getcUsuClave());
        ps.setInt(3, u.getPersonal().getnPerCodigo());
        ps.setString(4, u.getcUsuEstado());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }
    
    public static Usuario buscarUsuario(int codigo) throws SQLException {
        return buscarUsuario("select nUsuCodigo,cUsuId,cUsuClave,nPerCodigo,cUsuEstado from usuario where nUsuCodigo=" + codigo, null);
    }
    
    public static Usuario buscarUsuario(String id) throws SQLException {
        return buscarUsuario("select nUsuCodigo,cUsuId,cUsuClave,nPerCodigo,cUsuEstado from usuario where cUsuId=" + id, null);
    }

    public static Usuario buscarUsuario(String sql, Usuario u) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (u == null) {
                u = new Usuario() {
                };
            }
            u.setnUsuCodigo(rs.getInt("nUsuCodigo"));
            u.setcUsuId(rs.getString("cUsuId"));
            u.setcUsuClave(rs.getString("cUsuClave"));
            u.setPersonal(BDPersonal.buscarPersonalCodigo(rs.getInt("nPerCodigo")));
            u.setcUsuEstado(rs.getString("cUsuEstado"));
        }
        cnn.close();
        ps.close();
        return u;
    }

    /*public static boolean eliminarUsuario(String iduser) throws SQLException {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps = cnn.prepareStatement("delete from USUARIO WHERE id=?");
    ps.setString(1, iduser);
    int rowsUpdated = ps.executeUpdate();
    cnn.close();
    ps.close();
    if (rowsUpdated > 0) {
    return true;
    } else {
    return false;
    }
    }*/
    public static boolean actualizarUsuario(Usuario u) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update usuario set cUsuId=?,cUsuClave=?,nPerCodigo=?,cUsuEstado=? where nUsuCodigo=" + u.getnUsuCodigo());
        ps.setString(1, u.getcUsuId());
        ps.setString(2, u.getcUsuClave());
        ps.setInt(3, u.getPersonal().getnPerCodigo());
        ps.setString(4, u.getcUsuEstado());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static Usuario login(String usuario, String clave) throws SQLException {
        return realizarbusqueda(null,"select cUsuId,cUsuClave,nPerCodigo from usuario where cUsuId='"+usuario+"' and cUsuClave='"+clave+"'");
    }

    public static Usuario realizarbusqueda(Usuario u, String sql) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (u == null) {
                u = new Usuario(){};
            }
            u.setcUsuId(rs.getString("cUsuId"));
            u.setcUsuClave(rs.getString("cUsuClave"));
            u.setPersonal(BDPersonal.buscarPersonalCodigo(rs.getInt("nPerCodigo")));
        }
        cnn.close();
        ps.close();
        return u;
    }

    public static ArrayList<Usuario> mostrarUsuario() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        ps = cnn.prepareStatement("select nUsuCodigo,cUsuId,cUsuClave,nPerCodigo,cUsuEstado from usuario");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario u = new Usuario() {
            };
            u.setnUsuCodigo(rs.getInt("nUsuCodigo"));
            u.setcUsuId(rs.getString("cUsuId"));
            u.setcUsuClave(rs.getString("cUsuClave"));
            u.setPersonal(BDPersonal.buscarPersonalCodigo(rs.getInt("nPerCodigo")));
            u.setcUsuEstado(rs.getString("cUsuEstado"));
            lista.add(u);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    public static ArrayList<Usuario> listarUsuarioPorId(String nombre) {
        return listar("cUsuId", nombre + "%", "like");
    }

    public static ArrayList<Usuario> listarUsuarioPorCodigo(String codigo) {
        return listar("nUsuCodigo", codigo + "%", "like");
    }

    private static ArrayList<Usuario> listar(String atributo, String parametro, String comparador) {
        return consultarSQL("select nUsuCodigo,cUsuId,cUsuClave,nPerCodigo,cUsuEstado from usuario where " + atributo + " " + comparador + " '" + parametro + "'");
    }

    private static ArrayList<Usuario> consultarSQL(String sql) {
        ArrayList<Usuario> list = new ArrayList<Usuario>();
        Connection cn = BD.getConnection();
        try {
            Usuario u;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                u = new Usuario();
                u.setnUsuCodigo(rs.getInt("nUsuCodigo"));
                u.setcUsuId(rs.getString("cUsuId"));
                u.setcUsuClave(rs.getString("cUsuClave"));
                u.setPersonal(BDPersonal.buscarPersonalCodigo(rs.getInt("nPerCodigo")));
                u.setcUsuEstado(rs.getString("cUsuEstado"));
                list.add(u);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
}