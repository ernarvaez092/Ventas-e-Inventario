
package ComponenteMantenimiento;
import ComponenteClase.Venta;
import ComponenteClase.Producto;
import ComponenteClase.*;
import ComponenteConsulta.*;
import ComponenteDatos.*;
import ComponentePrincipal.FrmMenuPrincipal;
import java.awt.Toolkit;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Efren Narvaez
 */
public class FrmMantenimientoVenta extends javax.swing.JFrame {

    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    Date fecha = null;
    String Serie = "", Codigo = "";
    double subTotal = 0.0, importe = 0.0, vuelto = 0.0, totalIGV = 0.0, montoTotal = 0.0, montoCuota = 0.0;
    int nTipoDocumento = 0, nVenta = 0,nCliente=0;

    /** Creates new form FrmMantenimientoVenta */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FrmMantenimientoVenta() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        Serie = generarSerie();
        Codigo = generarCodigo();
        txtNroVenta.setText(Serie + " - " + Codigo);
        /*
         * Sentencia que permitira Mostrar los elmentos en un JTABLE
         */
        String titulos[] = {"CODIGO", "PRODUCTO","DESCRIPCION", "CANTIDAD", "PRECIO UND.", "IMPORTE"};
        ListaProductoV.setColumnIdentifiers(titulos);
        //
        this.setLocationRelativeTo(null);
        activarBotones(false);
    }

    public String generarCodigo() {
        Connection cn = BD.getConnection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            String sql = "select max(cTipDocCodigo) as Codigo from tipodocumento";
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.next();
            if (rs.getString("Codigo") != null) {
                Scanner s = new Scanner(rs.getString("Codigo"));
                int c = s.useDelimiter("C").nextInt() + 1;

                if (c < 10) {
                    return "C0000" + c;
                }
                if (c < 100) {
                    return "C000" + c;
                }
                if (c < 1000) {
                    return "C00" + c;
                }
                if (c < 10000) {
                    return "C0" + c;
                } else {
                    return "C" + c;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "C00001";
    }

    public String generarSerie() {
        Connection cn = BD.getConnection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            String sql = "select max(cTipDocSerie) as Serie from tipodocumento";
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.next();
            if (rs.getString("Serie") != null) {
                Scanner sc = new Scanner(rs.getString("Serie"));
                int s = sc.useDelimiter("S").nextInt() + 1;

                /*if (s < 10) {
                return "S000" + s;
                }
                if (s < 100) {
                return "S00" + s;
                }
                if (s < 1000) {
                return "S0" + s;
                } else {
                return "S" + s;
                }*/
                if (s < 10) {
                    return "S00" + s;
                }
                if (s < 100) {
                    return "S0" + s;
                }
                if (s < 1000) {
                    return "S" + s;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //return "S0001";
        return "S001";
    }

    public int getCodigopro() {
        return codigopro;
    }

    public void setCodigopro(int codigopro) {
        this.codigopro = codigopro;
    }

    public String getDescripcionpro() {
        return descripcionpro;
    }

    public void setDescripcionpro(String descripcionpro) {
        this.descripcionpro = descripcionpro;
    }

    public double getPreciounitprov() {
        return preciounitprov;
    }

    public void setPreciounitprov(double preciounitprov) {
        this.preciounitprov = preciounitprov;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void limpiarCajaTexto() {
        txtCodCliente.setText("");
        txtClienteCi.setText("");
        txtClienteNit.setText("");
        txtNombreCli.setText("");
        txtNroCuota.setText("");
        txtMotoCuota.setText("");
        txtCantidadPro.setText("");
        txtCodigoPro.setText("");
        txtImporte.setText("");
        txtSubTotal.setText("");
        txtVuelto.setText("");
        txtStockPro.setText("");
    }

    public void activarCajaTexto() {
        txtCantidadPro.setEditable(true);
        txtImporte.setText("0.0");
        txtSubTotal.setText("0.0");
        txtMontoApagar.setText("0.0");
        txtIGV.setText("0.0");
        txtVuelto.setText("0.0");
    }

    public void activarBotones(boolean b) {
        btnNuevaV.setEnabled(!b);
        btnImporteV.setEnabled(b);
        btnGenerarVenta.setEnabled(b);
       
    }

    public void actualizarPago() {
        int fila = 0;
        if (String.valueOf(cboTipoDocumento.getSelectedItem()).equalsIgnoreCase("NOTA VENTA")) {
            totalIGV = 0.0;
            montoTotal = 0.0;
            txtIGV.setText("0.0");
            //fila = ListaProductoV.getRowCount();
            fila = jtListaProducto.getRowCount();
            for (int f = 0; f < fila; f++) {
                subTotal += Double.parseDouble(String.valueOf(jtListaProducto.getModel().getValueAt(f, 5)));
                txtSubTotal.setText(String.valueOf(subTotal));
                montoTotal = Double.parseDouble(txtIGV.getText()) + Double.parseDouble(txtSubTotal.getText());
                txtMontoApagar.setText(String.valueOf(montoTotal));
            }
            subTotal = 0.0;
        } else {
            totalIGV = 0.0;
            montoTotal = 0.0;
            fila = jtListaProducto.getRowCount();
            //fila = ListaProductoV.getRowCount();
            for (int f = 0; f < fila; f++) {
                subTotal += Double.parseDouble(String.valueOf(jtListaProducto.getModel().getValueAt(f, 5)));
                txtSubTotal.setText(String.valueOf(subTotal));
                totalIGV += (Double.parseDouble(txtSubTotal.getText()) * 0.12); // FIJACION DEL IVA
                txtIGV.setText(String.valueOf(totalIGV));
                montoTotal = Double.parseDouble(txtIGV.getText()) + Double.parseDouble(txtSubTotal.getText());
                txtMontoApagar.setText(String.valueOf(montoTotal));
            }
            subTotal = 0.0;
        }
    }

    public boolean verificarRegistrado() {
        boolean valor = false;
        int codigoproVerificacion = Integer.parseInt(txtCodigoPro.getText());
        int fila = ListaProductoV.getRowCount();
        for (int f = 0; f < fila; f++) {
            if (codigoproVerificacion == Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 0)))) {
                valor = true;
            } else {
                valor = false;
            }
        }
        return valor;
    }

    public void obtenerUltimoIdTipoDocumento() {
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(nTipDocumento) as Id from tipodocumento");
            while (rs.next()) {
                nTipoDocumento = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    public void obtenerUltimoIdVenta() {
        try {
            Connection con1 = BD.getConnection();
            Statement stmt1 = con1.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select max(nVenCodigo) as Id from venta");
            while (rs1.next()) {
                nVenta = rs1.getInt(1);
            }
            rs1.close();
            stmt1.close();
            con1.close();
        } catch (SQLException error) {
            System.out.print(error);
        }
    }
    
    public void obtenerUltimoIdCliente() {
        try {
            Connection con2 = BD.getConnection();
            Statement stmt2 = con2.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select max(nCliCodigo) from cliente");
            while (rs2.next()) {
                nCliente = rs2.getInt(1);
            }
            rs2.close();
            stmt2.close();
            con2.close();
        } catch (SQLException error) {
            System.out.print(error);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNroVenta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtVendedorV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCantidadPro = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        txtClienteCi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtClienteNit = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNombreCli = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cboTipoPago = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtMotoCuota = new javax.swing.JTextField();
        txtNroCuota = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtStockPro = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListaProducto = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtMontoApagar = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnGenerarVenta = new javax.swing.JButton();
        btnNuevaV = new javax.swing.JButton();
        btnImporteV = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(70, 99, 138));

        jPanel3.setBackground(new java.awt.Color(247, 254, 255));

        jPanel2.setBackground(new java.awt.Color(247, 254, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GENERAR NUEVA VENTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(247, 254, 255));

        jLabel1.setText("FECHA :");

        txtFecha.setEditable(false);
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        jLabel2.setText("Nro VENTA :");

        txtNroVenta.setEditable(false);
        txtNroVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("VENDEDOR :");

        txtVendedorV.setEditable(false);

        jLabel7.setText("PRODUCTO :");

        txtCodigoPro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProKeyReleased(evt);
            }
        });

        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/View.png"))); // NOI18N
        btnBuscarProducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarProducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        jLabel8.setText("CANTIDAD :");

        txtCantidadPro.setEditable(false);
        txtCantidadPro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProKeyTyped(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Create.png"))); // NOI18N
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(247, 254, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("CLIENTE :");

        txtCodCliente.setEditable(false);
        txtCodCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/View.png"))); // NOI18N
        btnBuscarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        txtClienteCi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setText("DNI :");

        jLabel11.setText("APELLIDOS :");

        txtClienteNit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setText("NOMBRE :");

        jLabel14.setText("TIPO PAGO :");

        cboTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CONTADO", "CREDITO" }));
        cboTipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoPagoActionPerformed(evt);
            }
        });

        jLabel16.setText("Nro Cuota :");

        jLabel17.setText("Monto x Cuota :");

        txtMotoCuota.setEditable(false);
        txtMotoCuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNroCuota.setEditable(false);
        txtNroCuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroCuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroCuotaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroCuotaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtClienteCi, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtClienteNit, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreCli)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNroCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMotoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtClienteCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtClienteNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(cboTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNroCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMotoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel13.setText("TIPO DOCUMENTO :");

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOTA VENTA", "FACTURA" }));
        cboTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoDocumentoActionPerformed(evt);
            }
        });

        jLabel4.setText("STOCK :");

        txtStockPro.setEditable(false);
        txtStockPro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStockPro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadPro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVendedorV, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtVendedorV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtStockPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtCantidadPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(247, 254, 255));

        jtListaProducto.setModel(ListaProductoV);
        jtListaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtListaProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtListaProducto);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Remove.png"))); // NOI18N
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(247, 254, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("IMPORTE :");

        txtSubTotal.setEditable(false);
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel15.setText("SUB. TOTAL:");

        jLabel18.setText("VUELTO :");

        txtVuelto.setEditable(false);
        txtVuelto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtImporte.setEditable(false);
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setText("IVA");

        txtIGV.setEditable(false);
        txtIGV.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel19.setText("TOTAL A PAGAR :");

        txtMontoApagar.setEditable(false);
        txtMontoApagar.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtImporte)
                    .addComponent(txtMontoApagar, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtMontoApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(247, 254, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Save.png"))); // NOI18N
        btnGenerarVenta.setText("GENERAR VENTA");
        btnGenerarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGenerarVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        btnNuevaV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/New document.png"))); // NOI18N
        btnNuevaV.setText("NUEVA");
        btnNuevaV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevaV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevaV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVActionPerformed(evt);
            }
        });

        btnImporteV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Accounting.png"))); // NOI18N
        btnImporteV.setText("IMPORTE");
        btnImporteV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImporteV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImporteV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImporteVActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Exit.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(btnNuevaV, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImporteV)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarVenta)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(327, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImporteV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevaV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevaVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVActionPerformed
        // TODO add your handling code here:
        limpiarCajaTexto();
        activarCajaTexto();
        btnNuevaV.setEnabled(false);
        btnGenerarVenta.setEnabled(true);
    }//GEN-LAST:event_btnNuevaVActionPerformed

    private void cboTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoPagoActionPerformed
        // TODO add your handling code here:
        if (String.valueOf(cboTipoPago.getSelectedItem()).equalsIgnoreCase("CONTADO")) {
            txtNroCuota.setEditable(false);
        } else {
            txtNroCuota.setEditable(true);
            btnImporteV.setEnabled(false);
            txtImporte.setText("0.0");
            txtVuelto.setText("0.0");
        }
    }//GEN-LAST:event_cboTipoPagoActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
        JDBuscarClienteCompra JDBCCom = new JDBuscarClienteCompra(this, true, this);
        JDBCCom.setVisible(true);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        // TODO add your handling code here:
        JDBuscarProductoCompra JDBPCom = new JDBuscarProductoCompra(this, true, this);
        JDBPCom.setVisible(true);
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

     private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    
    public void buscarProd() {


           Producto c = null;
            if (isNumeric(txtCodigoPro.getText())) {
                try {
                    c = BDProducto.buscarProductoCodigo1(Integer.parseInt(txtCodigoPro.getText()));
                } catch (SQLException e) {
                    System.out.println("" + e.getMessage());
                }
                if (c != null) {
                    txtStockPro.setText(String.valueOf(c.getnProCantidad()));
                   
                } else {
                    JOptionPane.showMessageDialog(null, "No existe elemento buscado");
                }
            }
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        int cantidad = Integer.parseInt(txtCantidadPro.getText());
        int fila = 0;
        if (!verificarRegistrado()) {
            if (txtCantidadPro.getText().compareTo("") != 0) {
                if (cantidad <= getStock()) {
                    String Datos[] = {txtCodigoPro.getText(),getNombre(),getDescripcionpro(), txtCantidadPro.getText(), String.valueOf(getPreciounitprov()),
                        String.valueOf(Math.round(Integer.parseInt(txtCantidadPro.getText()) * getPreciounitprov()))};
                    ListaProductoV.addRow(Datos);
                    //hacer un recorrido-para sumar la cantidad
                    fila = ListaProductoV.getRowCount();
                    for (int f = 0; f < fila; f++) {
                        subTotal += Double.parseDouble(String.valueOf(jtListaProducto.getModel().getValueAt(f, 5)));
                        //System.out.print(jtListaProducto.getModel().getValueAt(f,4));
                        txtSubTotal.setText(String.valueOf(subTotal));
                        if (String.valueOf(cboTipoDocumento.getSelectedItem()).equalsIgnoreCase("NOTA VENTA")) {
                            txtIGV.setText("0.0");
                            montoTotal = Double.parseDouble(txtIGV.getText()) + Double.parseDouble(txtSubTotal.getText());
                            txtMontoApagar.setText(String.valueOf(montoTotal));
                        } else {
                            totalIGV += (Double.parseDouble(txtSubTotal.getText()) * 0.19);
                            txtIGV.setText(String.valueOf(totalIGV));
                            montoTotal = Double.parseDouble(txtIGV.getText()) + Double.parseDouble(txtSubTotal.getText());
                            txtMontoApagar.setText(String.valueOf(montoTotal));
                        }
                    }
                    txtCantidadPro.setText("");
                    if (cboTipoPago.getSelectedItem().toString().equalsIgnoreCase("CONTADO")) {
                        btnImporteV.setEnabled(true);
                    } else {
                        btnImporteV.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene el Stock Suficiente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
            }
        }
        subTotal = 0;
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:        
        int fila = jtListaProducto.getSelectedRow();
        if (fila > 0) {
            ListaProductoV.removeRow(fila);
            actualizarPago();
        } else if (fila == 0) {
            ListaProductoV.removeRow(fila);
            actualizarPago();
            txtSubTotal.setText("0.0");
            txtIGV.setText("0.0");
            txtMontoApagar.setText("0.0");
        }
        actualizarPago();
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void btnImporteVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImporteVActionPerformed
        // TODO add your handling code here:
        String ingreso = JOptionPane.showInputDialog(null, "Ingrese Importe a Cancelar", "0.0");
        if (ingreso.compareTo("") != 0) {
            importe = Double.parseDouble(ingreso);
            txtImporte.setText(String.valueOf(importe));
            vuelto = Double.parseDouble(txtImporte.getText()) - Double.parseDouble(txtSubTotal.getText());
            txtVuelto.setText(String.valueOf(vuelto));
        } else {
            txtImporte.setText("0.0");
        }
    }//GEN-LAST:event_btnImporteVActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        // TODO add your handling code here:
        int nroFilas;
        int result = JOptionPane.showConfirmDialog(this, "Deseas Ejecutar la Compra?", "Mensaje..!!", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            //aca verificamos que tipo de pago se realizo se fue al contado o a credito
            if (cboTipoPago.getSelectedItem().toString().equalsIgnoreCase("CONTADO")) {
                if (txtCodigoPro.getText().compareTo("") != 0 & txtFecha.getText().compareTo("") != 0
                        & txtMontoApagar.getText().compareTo("") != 0 & Serie.compareTo("") != 0 & Codigo.compareTo("") != 0) {
                    //Primero Insertamos TipoDocumento            
                    TipoDocumento td = new TipoDocumento();
                    try {
                        td.setcTipoDocDescripcion((String) cboTipoDocumento.getSelectedItem());
                        td.setcTipDocSerie(Serie);
                        td.setcTipDocCodigo(Codigo);
                        BDTipoDocumento.insertarTipoDocumento(td);
                        //Segundo hacemos una consulta para obtener el ultimo documento registrado
                        obtenerUltimoIdTipoDocumento();
                        System.out.println("nDocumento:" + nTipoDocumento);
                        fecha = formatoDelTexto.parse(txtFecha.getText());
                        //Ahora registramos la venta
                        Venta v = new Venta();
                        if (txtCodCliente.getText().compareTo("") != 0) {
                            v.setCliente(BDCliente.buscarClienteCodigo(Integer.parseInt(txtCodCliente.getText())));
                        }else{
                            Cliente c= new Cliente();
                            c.setcCliNombre(txtNombreCli.getText());
                            if(txtClienteCi.getText().compareTo("")!=0 & txtClienteNit.getText().compareTo("")==0){
                                c.setcCliCi(txtClienteCi.getText());
                            }else{
                                c.setcCliNit("--");
                            }
                            if(txtClienteCi.getText().compareTo("")==0 & txtClienteNit.getText().compareTo("")!=0){
                                c.setcCliNit(txtClienteNit.getText());
                            }else{
                                c.setcCliCi("--");
                            }
                            if(txtClienteCi.getText().compareTo("")!=0 & txtClienteNit.getText().compareTo("")!=0){
                                c.setcCliCi(txtClienteCi.getText());
                                c.setcCliNit(txtClienteNit.getText());
                            }
                            if(txtClienteCi.getText().compareTo("")==0 & txtClienteNit.getText().compareTo("")==0){
                                c.setcCliNit("--");
                                c.setcCliCi("--");
                            }
                            BDCliente.insertarCliente(c);
                            obtenerUltimoIdCliente();
                            v.setCliente(BDCliente.buscarClienteCodigo(nCliente));
                        }
                        //v.setPersonal(BDPersonal.buscarPersonalCodigo(Integer.parseInt(txtCodigoPro.getText())));
                        v.setPersonal(BDPersonal.buscarPersonalNombre(txtVendedorV.getText()));
                        v.setcVenFecha(fecha);
                        v.setnVenMontoTotal(Double.parseDouble(txtMontoApagar.getText()));
                        v.setTipoDocumento(BDTipoDocumento.buscarTipoDocumento(nTipoDocumento));
                        v.setcVenEstado("REALIZADA");
                        v.setcFormapago((String) cboTipoPago.getSelectedItem());
                        BDVenta.insertarVenta(v);
                        //necesito obtener la ultima venta registrada
                        obtenerUltimoIdVenta();
                        //Ahora registramos la transaccion
                        Transaccion t = new Transaccion();
                        nroFilas = ListaProductoV.getRowCount();
                        for (int f = 0; f < nroFilas; f++) {
                            try {
                                t.setVenta(BDVenta.buscarVenta(nVenta));
                                t.setProducto(BDProducto.buscarProducto(Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 0)))));
                            } catch (SQLException ta) {
                                System.out.println("Error BD: " + ta.getMessage());
                            }
                            t.setNtraCantidad(Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 3))));
                            t.setnTraPrecio(Double.parseDouble(String.valueOf(jtListaProducto.getModel().getValueAt(f, 4))));
                            t.setnTraPrecioTotal(Double.parseDouble(String.valueOf(jtListaProducto.getModel().getValueAt(f, 5))));
                            BDTransaccion.insertarTransaccion(t);
                            //por producto se tiene que hacer una actualizacion del stock
                            //actualizar Producto
                            try {
                                Producto p = BDProducto.buscarProducto(Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 0))));
                                p.setnProCantidad(p.getnProCantidad() - Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 3))));
                                BDProducto.actualizarProducto(p);
                            } catch (SQLException pr) {
                                System.out.println("Error BD: " + pr.getMessage());
                            }
                        }
                        Contado cont = new Contado();
                        cont.setVenta(BDVenta.buscarVenta(nVenta));
                        cont.setcContEstado("PagoCancelado");
                        cont.setnContMontoPago(Double.parseDouble(txtMontoApagar.getText()));
                        BDContado.insertarPagoContado(cont);
                    } catch (SQLException e) {
                        System.out.println("Error BD: " + e.getMessage());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "[ Datos Agregados ]");
                    btnGenerarVenta.setEnabled(false);                
                    btnImporteV.setEnabled(false);
                    cboTipoDocumento.setEnabled(false);
                    btnAgregar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Llena los campos requeridos");
                }
            } else {
                if (txtCodigoPro.getText().compareTo("") != 0 & txtFecha.getText().compareTo("") != 0
                        & txtMontoApagar.getText().compareTo("") != 0 & Serie.compareTo("") != 0 & Codigo.compareTo("") != 0
                        & txtNroCuota.getText().compareTo("") != 0 & txtMotoCuota.getText().compareTo("") != 0) {
                    //Primero Insertamos TipoDocumento            
                    TipoDocumento td = new TipoDocumento();
                    try {
                        td.setcTipoDocDescripcion((String) cboTipoDocumento.getSelectedItem());
                        td.setcTipDocSerie(Serie);
                        td.setcTipDocCodigo(Codigo);
                        BDTipoDocumento.insertarTipoDocumento(td);
                        //Segundo hacemos una consulta para obtener el ultimo documento registrado
                        obtenerUltimoIdTipoDocumento();
                        fecha = formatoDelTexto.parse(txtFecha.getText());
                        //Ahora registramos la venta
                        Venta v = new Venta();
                        if (txtCodCliente.getText().compareTo("") != 0) {
                            v.setCliente(BDCliente.buscarClienteCodigo(Integer.parseInt(txtCodCliente.getText())));
                        }else{
                            Cliente c= new Cliente();
                            c.setcCliNombre(txtNombreCli.getText());
                            if(txtClienteCi.getText().compareTo("")!=0 & txtClienteNit.getText().compareTo("")==0){
                                c.setcCliCi(txtClienteCi.getText());
                            }else{
                                c.setcCliNit("--");
                            }
                            if(txtClienteCi.getText().compareTo("")==0 & txtClienteNit.getText().compareTo("")!=0){
                                c.setcCliNit(txtClienteNit.getText());
                            }else{
                                c.setcCliCi("--");
                            }
                            if(txtClienteCi.getText().compareTo("")!=0 & txtClienteNit.getText().compareTo("")!=0){
                                c.setcCliCi(txtClienteCi.getText());
                                c.setcCliNit(txtClienteNit.getText());
                            }
                            if(txtClienteCi.getText().compareTo("")==0 & txtClienteNit.getText().compareTo("")==0){
                                c.setcCliNit("--");
                                c.setcCliCi("--");
                            }
                            BDCliente.insertarCliente(c);
                            obtenerUltimoIdCliente();
                            v.setCliente(BDCliente.buscarClienteCodigo(nCliente));
                        }
                        v.setPersonal(BDPersonal.buscarPersonalNombre(txtVendedorV.getText()));
                        v.setcVenFecha(fecha);
                        v.setnVenMontoTotal(Double.parseDouble(txtMontoApagar.getText()));
                        v.setTipoDocumento(BDTipoDocumento.buscarTipoDocumento(nTipoDocumento));
                        v.setcVenEstado("REALIZADA");
                        v.setcFormapago((String) cboTipoPago.getSelectedItem());
                        BDVenta.insertarVenta(v);
                        //necesito obtener la ultima venta registrada
                        obtenerUltimoIdVenta();
                        System.out.println("venta:" + nVenta);
                        //Ahora registramos la transaccion
                        Transaccion t = new Transaccion();
                        nroFilas = ListaProductoV.getRowCount();
                        for (int f = 0; f < nroFilas; f++) {
                            try {
                                t.setVenta(BDVenta.buscarVenta(nVenta));
                                t.setProducto(BDProducto.buscarProducto(Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 0)))));
                            } catch (SQLException ta) {
                                System.out.println("Error BD: " + ta.getMessage());
                            }
                            t.setNtraCantidad(Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 3))));
                            t.setnTraPrecio(Double.parseDouble(String.valueOf(jtListaProducto.getModel().getValueAt(f, 4))));
                            t.setnTraPrecioTotal(Double.parseDouble(String.valueOf(jtListaProducto.getModel().getValueAt(f, 5))));
                            BDTransaccion.insertarTransaccion(t);
                            //por producto se tiene que hacer una actualizacion del stock
                            //actualizar Producto
                            try {
                                Producto p = BDProducto.buscarProducto(Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 0))));
                                p.setnProCantidad(p.getnProCantidad() - Integer.parseInt(String.valueOf(jtListaProducto.getModel().getValueAt(f, 3))));
                                BDProducto.actualizarProducto(p);
                            } catch (SQLException pr) {
                                System.out.println("Error BD: " + pr.getMessage());
                            }
                        }
                        Credito cre = new Credito();
                        cre.setVenta(BDVenta.buscarVenta(nVenta));
                        cre.setcCreEstado("Debito");
                        cre.setnCreNroCuotas(Integer.parseInt(txtNroCuota.getText()));
                        cre.setNcreMontoPorCuota(Double.parseDouble(txtMotoCuota.getText()));
                        cre.setnCreMontoDebito(Double.parseDouble(txtMontoApagar.getText()));
                        BDCredito.insertarPagoCredito(cre);
                    } catch (SQLException e) {
                        System.out.println("Error BD: " + e.getMessage());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "[ Venta Realizada Correctamente ]");
                    btnGenerarVenta.setEnabled(false);               
                    btnImporteV.setEnabled(false);
                    cboTipoDocumento.setEnabled(false);
                    btnAgregar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    FrmMenuPrincipal.miCronogramaPago.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Llena los campos requeridos");
                }
            }
        }
        if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Venta Detenida xD!");
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void cboTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoDocumentoActionPerformed
        // TODO add your handling code here:
        actualizarPago();
    }//GEN-LAST:event_cboTipoDocumentoActionPerformed

    private void jtListaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtListaProductoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtListaProductoMouseClicked

    private void txtNroCuotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroCuotaKeyReleased
        // TODO add your handling code here:
        if (txtNroCuota.getText().compareTo("") != 0) {
            montoCuota = Double.parseDouble(txtMontoApagar.getText()) / Double.parseDouble(txtNroCuota.getText());
            txtMotoCuota.setText(String.valueOf(Math.round(montoCuota)));
        } else {
            txtMotoCuota.setText("0.0");
        }
    }//GEN-LAST:event_txtNroCuotaKeyReleased

    private void txtNroCuotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroCuotaKeyTyped
        // TODO add your handling code here:
        if (String.valueOf(evt.getKeyChar()).matches("[a-zA-Z]|\\s")) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNroCuotaKeyTyped

    private void txtCantidadProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProKeyTyped
        // TODO add your handling code here:
        if (String.valueOf(evt.getKeyChar()).matches("[a-zA-Z]|\\s")) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadProKeyTyped

    private void txtCodigoProKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProKeyReleased
buscarProd();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;






                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmMantenimientoVenta().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnImporteV;
    private javax.swing.JButton btnNuevaV;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JComboBox cboTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtListaProducto;
    private javax.swing.JTextField txtCantidadPro;
    public javax.swing.JTextField txtClienteCi;
    public javax.swing.JTextField txtClienteNit;
    public javax.swing.JTextField txtCodCliente;
    public javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtMontoApagar;
    private javax.swing.JTextField txtMotoCuota;
    public javax.swing.JTextField txtNombreCli;
    private javax.swing.JTextField txtNroCuota;
    private javax.swing.JTextField txtNroVenta;
    public javax.swing.JTextField txtStockPro;
    private javax.swing.JTextField txtSubTotal;
    public javax.swing.JTextField txtVendedorV;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
    public int codigopro;
    public String nombre;
    public String descripcionpro;
    public double preciounitprov;
    public int stock;
    DefaultTableModel ListaProductoV = new DefaultTableModel();

}