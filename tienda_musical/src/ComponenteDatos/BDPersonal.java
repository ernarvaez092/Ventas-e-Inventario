/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Personal;
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
//cPerEstado
public abstract class BDPersonal {

    public static Personal insertarPersonal(Personal p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into personal (cPerNombre,cPerDireccion,cPerCi,cPerTipoTelefono,cPerNumTelefono,cPerEstado) values (?,?,?,?,?,?)");
        ps.setString(1, p.getcPerNombre());
        ps.setString(2, p.getcPerDirceccion());
        ps.setString(3, p.getcPerCi());
        ps.setString(4, p.getcPerTipoTelefono());
        ps.setString(5, p.getcPerNumTelefono());
        ps.setString(6, p.getcPerEstado());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(nPerCodigo) from personal");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            int lastID = r.getInt(1);
            p.setnPerCodigo(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return p;
    }

    public static Personal buscarPersonalCodigo(int codigo) throws SQLException {
        return buscarPersonal("select nPerCodigo,cPerCi,cPerNombre,cPerDireccion,cPerTipoTelefono,cPerNumTelefono,cPerEstado from personal where nPerCodigo=" + codigo, null);
    }

    public static Personal buscarPersonalNombre(String nombre) throws SQLException {
        return buscarPersonal("select nPerCodigo,cPerCi,cPerNombre,cPerDireccion,cPerTipoTelefono,cPerNumTelefono,cPerEstado from personal where cPerNombre='" + nombre+"'", null);
    }

    public static Personal buscarPersonalEstado(String estado) throws SQLException {
        return buscarPersonal("select nPerCodigo,cPerCi,cPerNombre,cPerDireccion,cPerTipoTelefono,cPerNumTelefono,cPerEstado from personal where cPerEstado='" + estado+"'", null);
    }

    public static Personal buscarPersonal(String sql, Personal p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new Personal() {
                };
            }
            p.setnPerCodigo(rs.getInt("nPerCodigo"));
            p.setcPerNombre(rs.getString("cPerNombre"));
            p.setcPerDirceccion(rs.getString("cPerDireccion"));
            p.setcPerCi(rs.getString("cPerCi"));
            p.setcPerTipoTelefono(rs.getString("cPerTipoTelefono"));
            p.setcPerNumTelefono(rs.getString("cPerNumTelefono"));
            p.setcPerEstado(rs.getString("cPerEstado"));
        }
        cnn.close();
        ps.close();
        return p;
    }

    /*public static boolean eliminarEmpleado(int nroemp) throws SQLException {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    
    ps = cnn.prepareStatement("delete from EMPLEADO WHERE nroemp=?");
    ps.setInt(1, nroemp);
    int rowsUpdated = ps.executeUpdate();
    cnn.close();
    ps.close();
    if (rowsUpdated > 0) {
    return true;
    } else {
    return false;
    }
    }*/

    /*public static boolean eliminarEmpleadoDNI(int dni) throws SQLException {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    
    ps = cnn.prepareStatement("delete from EMPLEADO WHERE dni=?");
    ps.setInt(1, dni);
    int rowsUpdated = ps.executeUpdate();
    cnn.close();
    ps.close();
    if (rowsUpdated > 0) {
    return true;
    } else {
    return false;
    }
    }*/
    public static boolean actualizarPersonal(Personal p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("update personal set cPerNombre=?,cPerDireccion=?,cPerCi=?,cPerTipoTelefono=?,cPerNumTelefono=?,cPerEstado=?  where nPerCodigo=" + p.getnPerCodigo());
        ps.setString(1, p.getcPerNombre());
        ps.setString(2, p.getcPerDirceccion());
        ps.setString(3, p.getcPerCi());
        ps.setString(4, p.getcPerTipoTelefono());
        ps.setString(5, p.getcPerNumTelefono());
        ps.setString(6, p.getcPerEstado());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Personal> mostrarPersonal() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Personal> lista = new ArrayList<Personal>();
        ps = cnn.prepareStatement("select nPerCodigo,cPerCi,cPerNombre,cPerDireccion,cPerTipoTelefono,cPerNumTelefono,cPerEstado from personal");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Personal p = new Personal() {
            };
            p.setnPerCodigo(rs.getInt("nPerCodigo"));
            p.setcPerNombre(rs.getString("cPerNombre"));
            p.setcPerDirceccion(rs.getString("cPerDireccion"));
            p.setcPerCi(rs.getString("cPerCi"));
            p.setcPerTipoTelefono(rs.getString("cPerTipoTelefono"));
            p.setcPerNumTelefono(rs.getString("cPerNumTelefono"));
            p.setcPerEstado(rs.getString("cPerEstado"));
            lista.add(p);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    public static ArrayList<Personal> listarPersonalPorNombre(String nombre) {
        return listar("cPerNombre", nombre + "%", "like");
    }

    public static ArrayList<Personal> listarPersonalPorCi(String ci) {
        return listar("cPerCi", ci + "%", "like");
    }

    private static ArrayList<Personal> listar(String atributo, String parametro, String comparador) {
        return consultarSQL("select nPerCodigo,cPerCi,cPerNombre,cPerDireccion,cPerTipoTelefono,cPerNumTelefono,cPerEstado from personal where " + atributo + " " + comparador + " '" + parametro + "'");
    }

    private static ArrayList<Personal> consultarSQL(String sql) {
        ArrayList<Personal> list = new ArrayList<Personal>();
        Connection cn = BD.getConnection();
        try {
            Personal p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new Personal();
                p.setnPerCodigo(rs.getInt("nPerCodigo"));
                p.setcPerNombre(rs.getString("cPerNombre"));
                p.setcPerDirceccion(rs.getString("cPerDireccion"));
                p.setcPerCi(rs.getString("cPerCi"));
                p.setcPerTipoTelefono(rs.getString("cPerTipoTelefono"));
                p.setcPerNumTelefono(rs.getString("cPerNumTelefono"));
                p.setcPerEstado(rs.getString("cPerEstado"));
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