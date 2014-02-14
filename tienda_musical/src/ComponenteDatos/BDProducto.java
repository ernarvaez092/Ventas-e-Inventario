/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Producto;
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
public abstract class BDProducto {

    public static Producto insertarProducto(Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        //ps = cnn.prepareStatement("insert into producto (cProNombre,nProvCodigo,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cProEstado) values(?,?,?,?,?,?,?,?,?,?)");
        ps = cnn.prepareStatement("insert into producto (cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado) values(?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, p.getcProNombre());
        ps.setInt(2, p.getnProCantidad());
        ps.setDouble(3, p.getnPropRecioCompra());
            ps.setDouble(4, p.getnProPRecioVenta());
        ps.setString(5, p.getnProUtilidad());
        ps.setString(6, p.getcProDescripcion());
        ps.setInt(7, p.getCategoria().getnCatCodigo());
        ps.setString(8, p.getcProMarca());
        ps.setString(9, p.getcCodProProv());
        ps.setString(10, p.getcProEstado());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(nProCodigo) from producto");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            int lastID = r.getInt(1);
            p.setnProCodigo(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return p;
    }

        public static Producto buscarProductoCodigo1(int codigo) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Producto cat = null;
        ps = cnn.prepareStatement("select nProCantidad from producto where nProCodigo=?");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cat = new Producto() {
            };
            cat.setnProCantidad(rs.getInt("nProCantidad"));
        }
        cnn.close();
        ps.close();
        return cat;
    }

    
    
    
    
    
    
    
    
    
    public static Producto buscarProducto(int codigo) throws SQLException {
        return buscarProducto(codigo, null);
    }

    public static Producto buscarProducto(int codigo, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        //ps = cnn.prepareStatement("select cProNombre,nProvCodigo,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cProEstado from producto where nProCodigo=?");
        ps = cnn.prepareStatement("select cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where nProCodigo=?");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new Producto() {
                };
            }
            p.setnProCodigo(codigo);
            p.setcProNombre(rs.getString("cProNombre"));
            //p.setProveedor(BDProveedor.buscarProveedor(rs.getInt("nProvCodigo")));
            p.setnProCantidad(rs.getInt("nProCantidad"));
            p.setnPropRecioCompra(rs.getDouble("nProPrecioCompra"));
            p.setnProPRecioVenta(rs.getDouble("nProPrecioVenta"));
            p.setnProUtilidad(rs.getString("nProUtilidad"));
            p.setcProDescripcion(rs.getString("cProDescripcion"));
            p.setCategoria(BDCategoria.buscarCategoriaCodigo(rs.getInt("nCatCodigo")));
            p.setcProMarca(rs.getString("cProMarca"));
            p.setcCodProProv(rs.getString("cCodProProv"));
            p.setcProEstado(rs.getString("cProEstado"));
        }
        cnn.close();
        ps.close();
        return p;
    }

    public static Producto buscarProductoNombre(String nom) throws SQLException {
        return buscarProductoNombre(nom, null);
    }

    public static Producto buscarProductoNombre(String nom, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        //ps = cnn.prepareStatement("select nProCodigo,nProvCodigo,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cProEstado from producto where cProNombre=?");
        ps = cnn.prepareStatement("select nProCodigo,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where cProNombre=?");
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new Producto() {
                };
            }
            p.setcProNombre(nom);
            p.setnProCodigo(rs.getInt("nProCodigo"));
            //p.setProveedor(BDProveedor.buscarProveedor(rs.getInt("nProvCodigo")));
            p.setnProCantidad(rs.getInt("nProCantidad"));
            p.setnPropRecioCompra(rs.getDouble("nProPrecioCompra"));
            p.setnProPRecioVenta(rs.getDouble("nProPrecioVenta"));
            p.setnProUtilidad(rs.getString("nProUtilidad"));
            p.setcProDescripcion(rs.getString("cProDescripcion"));
            p.setCategoria(BDCategoria.buscarCategoriaCodigo(rs.getInt("nCatCodigo")));
            p.setcProMarca(rs.getString("cProMarca"));
            p.setcCodProProv(rs.getString("cCodProProv"));
            p.setcProEstado(rs.getString("cProEstado"));
        }
        cnn.close();
        ps.close();
        return p;
    }

    
    public static Producto buscarProductoProvNombre(String propv,String nombre) throws SQLException {
        return buscarProductoProvNombre(propv,nombre, null);
    }

    public static Producto buscarProductoProvNombre(String propv,String nombre, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select nProCodigo,cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where cProNombre='"+nombre+"' and cCodProProv='"+propv+"'");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = p == null ? new Producto() {
            } : p;
            p.setCategoria(BDCategoria.buscarCategoriaCodigo(rs.getInt("nCatCodigo")));
            p.setcProNombre(rs.getString("cProNombre"));
            p.setnProCodigo(rs.getInt("nProCodigo"));
            p.setnProCantidad(rs.getInt("nProCantidad"));
            p.setnPropRecioCompra(rs.getDouble("nProPrecioCompra"));
            p.setnProPRecioVenta(rs.getDouble("nProPrecioVenta"));
            p.setnProUtilidad(rs.getString("nProUtilidad"));
            p.setcProDescripcion(rs.getString("cProDescripcion"));
            p.setcProMarca(rs.getString("cProMarca"));
            p.setcCodProProv(rs.getString("cCodProProv"));
            p.setcProEstado(rs.getString("cProEstado"));
        } else {
            p = null;
        }
        cnn.close();
        ps.close();
        return p;
    }
    
    public static Producto buscarProductoCateg(int CatCodigo) throws SQLException {
        return buscarProductoCateg(CatCodigo, null);
    }

    public static Producto buscarProductoCateg(int CatCodigo, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        //ps = cnn.prepareStatement("select nProCodigo,cProNombre,nProCantidad,nProvCodigo,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cProEstado from producto where nCatCodigo=?");
        ps = cnn.prepareStatement("select nProCodigo,cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where nCatCodigo=?");
        ps.setInt(1, CatCodigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = p == null ? new Producto() {
            } : p;
            p.setCategoria(BDCategoria.buscarCategoriaCodigo(rs.getInt(CatCodigo)));
            //p.setProveedor(BDProveedor.buscarProveedor(rs.getInt("nProvCodigo")));
            p.setcProNombre(rs.getString("cProNombre"));
            p.setnProCodigo(rs.getInt("nProCodigo"));
            p.setnProCantidad(rs.getInt("nProCantidad"));
            p.setnPropRecioCompra(rs.getDouble("nProPrecioCompra"));
            p.setnProPRecioVenta(rs.getDouble("nProPrecioVenta"));
            p.setnProUtilidad(rs.getString("nProUtilidad"));
            p.setcProDescripcion(rs.getString("cProDescripcion"));
            p.setcProMarca(rs.getString("cProMarca"));
            p.setcCodProProv(rs.getString("cCodProProv"));
            p.setcProEstado(rs.getString("cProEstado"));
        } else {
            p = null;
        }
        cnn.close();
        ps.close();
        return p;
    }

    public static boolean actualizarProducto(Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        //ps = cnn.prepareStatement("update producto set cProNombre=?,nProvCodigo=?,nProCantidad=?,nProPrecioCompra=?,nProPrecioVenta=?,nProUtilidad=?,cProDescripcion=?,nCatCodigo=?,cProMarca=?,cProEstado=? where nProCodigo=" + p.getnProCodigo());
        ps = cnn.prepareStatement("update producto set cProNombre=?,nProCantidad=?,nProPrecioCompra=?,nProPrecioVenta=?,nProUtilidad=?,cProDescripcion=?,nCatCodigo=?,cProMarca=?,cCodProProv=?,cProEstado=? where nProCodigo=" + p.getnProCodigo());
        ps.setString(1, p.getcProNombre());
        //ps.setInt(2, p.getProveedor().getnProvCodigo());
        ps.setInt(2, p.getnProCantidad());
        ps.setDouble(3, p.getnPropRecioCompra());
        ps.setDouble(4, p.getnProPRecioVenta());
        ps.setString(5, p.getnProUtilidad());
        ps.setString(6, p.getcProDescripcion());
        ps.setInt(7, p.getCategoria().getnCatCodigo());
        ps.setString(8, p.getcProMarca());
        ps.setString(9, p.getcCodProProv());
        ps.setString(10, p.getcProEstado());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Producto> mostrarProducto() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Producto> lista = new ArrayList<Producto>();

        //ps = cnn.prepareStatement("select nProCodigo,cProNombre,nProCantidad,nProvCodigo,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cProEstado from producto");
        ps = cnn.prepareStatement("select nProCodigo,cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Producto p = new Producto() {
            };
            p.setcProNombre(rs.getString("cProNombre"));
            p.setnProCodigo(rs.getInt("nProCodigo"));
            p.setnProCantidad(rs.getInt("nProCantidad"));
            p.setnPropRecioCompra(rs.getDouble("nProPrecioCompra"));
            p.setnProPRecioVenta(rs.getDouble("nProPrecioVenta"));
            p.setnProUtilidad(rs.getString("nProUtilidad"));
            p.setcProDescripcion(rs.getString("cProDescripcion"));
            p.setCategoria(BDCategoria.buscarCategoriaCodigo(rs.getInt("nCatCodigo")));
            p.setcProMarca(rs.getString("cProMarca"));
            p.setcCodProProv(rs.getString("cCodProProv"));
            p.setcProEstado(rs.getString("cProEstado"));
            lista.add(p);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    public static ArrayList<Producto> listarProductoPorNombre(String nombre) {
        return listar("cProNombre", nombre + "%", "like");
    }
    
    public static ArrayList<Producto> listarProductoPorNombreEstado(String codigo) {
        return consultarSQL("select nProCodigo,cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where cProNombre like '" + codigo + "%' and cProEstado='Activo'");
    }
    
    public static ArrayList<Producto> listarProductoPorCodProveedor(String nombre) {
        return listar("cCodProProv", nombre + "%", "like");
    }
    
    public static ArrayList<Producto> listarProductoPorCodProveedorEstado(String codigo) {
        return consultarSQL("select nProCodigo,cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where cCodProProv like '" + codigo + "%' and cProEstado='Activo'");
    }

    public static ArrayList<Producto> listarProductoPorCodigo(String codigo) {
        return listar("nProCodigo", codigo + "%", "like");
    }
    
    public static ArrayList<Producto> listarProductoPorCodigoEstado(String codigo) {
        return consultarSQL("select nProCodigo,cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where nProCodigo like '" + codigo + "%' and cProEstado='Activo'");
    }

    private static ArrayList<Producto> listar(String atributo, String parametro, String comparador) {
        return consultarSQL("select nProCodigo,cProNombre,nProCantidad,nProPrecioCompra,nProPrecioVenta,nProUtilidad,cProDescripcion,nCatCodigo,cProMarca,cCodProProv,cProEstado from producto where " + atributo + " " + comparador + " '" + parametro + "'");
    }

    private static ArrayList<Producto> consultarSQL(String sql) {
        ArrayList<Producto> list = new ArrayList<Producto>();
        Connection cn = BD.getConnection();
        try {
            Producto p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new Producto();
                p.setnProCodigo(rs.getInt("nProCodigo"));
                p.setcProNombre(rs.getString("cProNombre"));
                p.setnProCantidad(rs.getInt("nProCantidad"));
                p.setnPropRecioCompra(rs.getDouble("nProPrecioCompra"));
                p.setnProPRecioVenta(rs.getDouble("nProPrecioVenta"));
                p.setnProUtilidad(rs.getString("nProUtilidad"));
                p.setcProDescripcion(rs.getString("cProDescripcion"));
                p.setCategoria(BDCategoria.buscarCategoriaCodigo(rs.getInt("nCatCodigo")));
                p.setcProMarca(rs.getString("cProMarca"));
                p.setcCodProProv(rs.getString("cCodProProv"));
                p.setcProEstado(rs.getString("cProEstado"));
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