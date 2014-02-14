/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Efren Narvaez
 */
public abstract class BDCategoria {
    
    public static void insertarCategoria(Categoria cat) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into categoria (cCatDescripcion) values (?)");
        ps.setString(1, cat.getcCatDescripcion());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }
    
    public static Categoria buscarCategoriaCodigo(int codigo) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Categoria cat = null;
        ps = cnn.prepareStatement("select nCatCodigo,cCatDescripcion from categoria where nCatCodigo=?");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cat = new Categoria() {
            };
            cat.setnCatCodigo(codigo);
            cat.setcCatDescripcion(rs.getString("cCatDescripcion"));
        }
        cnn.close();
        ps.close();
        return cat;
    }

    public static Categoria buscarCategoriaDescripcion(String descripcion) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Categoria cat = null;
        ps = cnn.prepareStatement("select nCatCodigo,cCatDescripcion from categoria where cCatDescripcion='"+descripcion+"'");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cat = new Categoria() {
            };
            cat.setcCatDescripcion(descripcion);
            cat.setnCatCodigo(rs.getInt("nCatCodigo"));
        }
        cnn.close();
        ps.close();
        return cat;
    }

    /*public static boolean eliminarCategoria(int codigo) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("delete from categoria where nCatCodigo=?");
        ps.setInt(1, codigo);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }*/

    public static boolean actualizarCategoria(Categoria cat) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("update categoria set cCatDescripcion=? where nCatCodigo=" + cat.getnCatCodigo());
        ps.setString(1, cat.getcCatDescripcion());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Categoria> mostrarCategoria() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Categoria> lista = new ArrayList<Categoria>();

        ps = cnn.prepareStatement("select nCatCodigo,cCatDescripcion from categoria");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Categoria cat = new Categoria() {
            };
            cat.setnCatCodigo(rs.getInt("nCatCodigo"));
            cat.setcCatDescripcion(rs.getString("cCatDescripcion"));
            lista.add(cat);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}