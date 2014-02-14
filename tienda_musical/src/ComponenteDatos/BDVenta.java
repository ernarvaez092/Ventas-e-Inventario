/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Venta;
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
public abstract class BDVenta {

    public static void insertarVenta(Venta v) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into venta (nCliCodigo,nPerCodigo,cVenFecha,nVenMontoTotal,nTipDocumento,cVenEstado,cFormapago) values (?,?,?,?,?,?,?)");
        ps.setInt(1, v.getCliente().getnCliCodigo());
        ps.setInt(2, v.getPersonal().getnPerCodigo());
        ps.setDate(3, new java.sql.Date(v.getcVenFecha().getTime()));
        ps.setDouble(4, v.getnVenMontoTotal());
        ps.setInt(5, v.getTipoDocumento().getnTipDocumento());
        ps.setString(6, v.getcVenEstado());
        ps.setString(7, v.getcFormapago());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static Venta buscarVenta(int codigo) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Venta v = null;
        ps = cnn.prepareStatement("select nCliCodigo,nPerCodigo,cVenFecha,nVenMontoTotal,nTipDocumento,cVenEstado,cFormapago from venta where nVenCodigo=?");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            v = new Venta() {
            };
            v.setnVenCodigo(codigo);
            v.setCliente(BDCliente.buscarClienteCodigo(rs.getInt("nCliCodigo")));
            v.setPersonal(BDPersonal.buscarPersonalCodigo(rs.getInt("nPerCodigo")));
            v.setcVenFecha((java.util.Date) rs.getDate("cVenFecha"));
            v.setnVenMontoTotal(rs.getDouble("nVenMontoTotal"));
            v.setTipoDocumento(BDTipoDocumento.buscarTipoDocumento(rs.getInt("nTipDocumento")));
            v.setcVenEstado(rs.getString("cVenEstado"));
            v.setcFormapago(rs.getString("cFormapago"));
        }
        cnn.close();
        ps.close();
        return v;
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
    public static ArrayList<Venta> mostrarVenta() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Venta> lista = new ArrayList<Venta>();

        ps = cnn.prepareStatement("select nVenCodigo,nCliCodigo,nPerCodigo,cVenFecha,nVenMontoTotal,nTipDocumento,cVenEstado,cFormapago from venta");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Venta v = new Venta() {
            };
            v.setnVenCodigo(rs.getInt("nVenCodigo"));
            v.setCliente(BDCliente.buscarClienteCodigo(rs.getInt("nCliCodigo")));
            v.setPersonal(BDPersonal.buscarPersonalCodigo(rs.getInt("nPerCodigo")));
            v.setcVenFecha((java.util.Date) rs.getDate("cVenFecha"));
            v.setnVenMontoTotal(rs.getDouble("nVenMontoTotal"));
            v.setTipoDocumento(BDTipoDocumento.buscarTipoDocumento(rs.getInt("nTipDocumento")));
            v.setcVenEstado(rs.getString("cVenEstado"));
            v.setcFormapago(rs.getString("cFormapago"));
            lista.add(v);
        }
        cnn.close();
        ps.close();
        return lista;
    }

    public static ArrayList<Venta> listarVentaPorCodigo(String codigo) {
        return listar("nVenCodigo", codigo + "%", "like");
    }
    
    public static ArrayList<Venta> listarVentaPorCliente(String cli) {
        return listarCliente("c.cCliNombre", cli + "%", "like");
    }
    
    public static ArrayList<Venta> listarVentaPorEmpleado(String nompersonal) {
        return listarEmpleado("p.cPerNombre", nompersonal + "%", "like");
    }
    
    public static ArrayList<Venta> listarVentaPorEstado(String estado) {
        return listar("cVenEstado", estado + "%", "like");
    }
    
    public static ArrayList<Venta> listarVentaPorTipoDocumento(String td) {
        return listarTipo("tp.cTipDocDescripcion", td + "%", "like");
    }
    
    /**
     filtado para buscr venta en el formulario pago pendiente ...
     */ //select nVenCodigo,nCliCodigo,cVenFecha,nVenMontoTotal,nTipDocumento from venta where nCliCodigo=1 and cFormapago='CREDITO' and cVenFecha like '%2011-11-20%';
    
    public static ArrayList<Venta> listarVentaPorCodigoPagoP(int nCliCodigo,String codigo) {
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,cVenEstado,cFormapago from venta v inner join credito c on c.nVenCodigo=v.nVenCodigo where v.nCliCodigo="+nCliCodigo+" and v.cFormapago='CREDITO' and v.nVenCodigo like '%"+codigo+"%' and c.cCreEstado<>'PagoCancelado'");
    }
    
    public static ArrayList<Venta> listarVentaPorFechaPagoP(int nCliCodigo,String fecha) {        
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,v.cVenEstado,v.cFormapago from venta v inner join credito c on c.nVenCodigo=v.nVenCodigo where v.nCliCodigo="+nCliCodigo+" and v.cFormapago='CREDITO' and v.cVenFecha like '%"+fecha+"%' and c.cCreEstado<>'PagoCancelado'");
    }
    
    //-------------------------------------------------------------
    public static ArrayList<Venta> listarVentaPorRangoFecha(String fechaA,String fechaB){
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,v.cVenEstado,v.cFormapago from venta v where DATE_FORMAT(v.cVenFecha, '%Y-%m-%d') BETWEEN '"+fechaA+"' and '"+fechaB+"'");
    }
    public static ArrayList<Venta> listarVentaPorFecha(String fecha) {        
        return consultarSQL("select nVenCodigo,nCliCodigo,nPerCodigo,cVenFecha,nVenMontoTotal,nTipDocumento,cVenEstado,cFormapago from venta where cVenFecha like '%"+fecha+"%'");
    }
    private static ArrayList<Venta> listarCliente(String atributo, String parametro, String comparador) {        
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,v.cVenEstado,v.cFormapago from venta v inner join cliente c on v.nCliCodigo=c.nCliCodigo where " + atributo + " " + comparador + " '" + parametro + "'");
    }
    private static ArrayList<Venta> listarEmpleado(String atributo, String parametro, String comparador) {        
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,v.cVenEstado,v.cFormapago from venta v inner join personal p on v.nPerCodigo=p.nPerCodigo where " + atributo + " " + comparador + " '" + parametro + "'");
    } 
    private static ArrayList<Venta> listarTipo(String atributo, String parametro, String comparador) {        
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,v.cVenEstado,v.cFormapago from venta v inner join TipoDocumento tp on v.nTipDocumento=tp.nTipDocumento where " + atributo + " " + comparador + " '" + parametro + "'");
    }
    private static ArrayList<Venta> listar(String atributo, String parametro, String comparador) {        
        return consultarSQL("select nVenCodigo,nCliCodigo,nPerCodigo,cVenFecha,nVenMontoTotal,nTipDocumento,cVenEstado,cFormapago from venta where " + atributo + " " + comparador + " '" + parametro + "'");
    }
    //---------------------------------------------------------------------------------------------
    public static ArrayList<Venta> listarVentaNombreCliente(String nomcliente){
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,v.cVenEstado,v.cFormapago from venta v inner join cliente c on c.nCliCodigo=v.nCliCodigo where c.cCliNombre like '%"+nomcliente+"%' and v.cFormapago='CREDITO'");
    }
    public static ArrayList<Venta> listarVentaTipoDocumento(String tipodocumento){
        return consultarSQL("select v.nVenCodigo,v.nCliCodigo,v.nPerCodigo,v.cVenFecha,v.nVenMontoTotal,v.nTipDocumento,v.cVenEstado,v.cFormapago from venta v inner join tipodocumento tp on tp.nTipDocumento=v.nTipDocumento where tp.cTipDocDescripcion like '%"+tipodocumento+"%' and v.cFormapago='CREDITO'");
    }
    
    private static ArrayList<Venta> consultarSQL(String sql) {
        ArrayList<Venta> list = new ArrayList<Venta>();
        Connection cn = BD.getConnection();
        try {
            Venta v;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                v = new Venta();
                v.setnVenCodigo(rs.getInt("nVenCodigo"));
                v.setCliente(BDCliente.buscarClienteCodigo(rs.getInt("nCliCodigo")));
                v.setPersonal(BDPersonal.buscarPersonalCodigo(rs.getInt("nPerCodigo")));
                v.setcVenFecha((java.util.Date) rs.getDate("cVenFecha"));
                v.setnVenMontoTotal(rs.getDouble("nVenMontoTotal"));
                v.setTipoDocumento(BDTipoDocumento.buscarTipoDocumento(rs.getInt("nTipDocumento")));
                v.setcVenEstado(rs.getString("cVenEstado"));
                v.setcFormapago(rs.getString("cFormapago"));
                list.add(v);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
    
    
}