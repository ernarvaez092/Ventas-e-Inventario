
package ComponentePrincipal;

import ComponenteConsulta.*;
import ComponenteMantenimiento.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.UIManager;

/**
 *
 * @author Efren Narvaez
 */
public class FrmMenuPrincipal extends javax.swing.JFrame {

    /** Creates new form FrmMenuPrincipal */
    public FrmMenuPrincipal() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public String getAnual() {
        return anual;
    }

    public void setAnual(String anual) {
        this.anual = anual;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String retornarMes(int numero) {
        String descmes = "";
        switch (numero) {
            case 1:
                descmes = "ENERO";
                break;
            case 2:
                descmes = "FEBRERO";
                break;
            case 3:
                descmes = "MARZO";
                break;
            case 4:
                descmes = "ABRIL";
                break;
            case 5:
                descmes = "MAYO";
                break;
            case 6:
                descmes = "JUNIO";
                break;
            case 7:
                descmes = "JULIO";
                break;
            case 8:
                descmes = "AGOSTO";
                break;
            case 9:
                descmes = "SEPTEMBRE";
                break;
            case 10:
                descmes = "OCTOBRE";
                break;
            case 11:
                descmes = "NOVIEMBRE";
                break;
            case 12:
                descmes = "DICIEMBRE";
                break;
        }
        return descmes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNombreVendedor = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        mOpciones = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        miCategoria = new javax.swing.JMenuItem();
        miCliente = new javax.swing.JMenuItem();
        miPersonal = new javax.swing.JMenuItem();
        miProducto = new javax.swing.JMenuItem();
        miProveedor = new javax.swing.JMenuItem();
        miUsuario = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        miGenerarVenta = new javax.swing.JMenuItem();
        miCancelarVenta = new javax.swing.JMenuItem();
        miCronogramaPago = new javax.swing.JMenuItem();
        miListaReq = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        miRegistrarCompra = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        miConsultaV = new javax.swing.JMenuItem();
        mConsultaC = new javax.swing.JMenuItem();
        miConsultaProv = new javax.swing.JMenuItem();
        miConsultaPro = new javax.swing.JMenuItem();
        miPagoPendiente = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE VENTAS HERRA - SUR V 0.1");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CAJERO:");

        lblNombreVendedor.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lblNombreVendedor.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreVendedor.setText("NOMBRE DE VENDEDOR");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("FECHA ACTUAL :");

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(lblNombreVendedor)
                .addGap(241, 241, 241)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreVendedor)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(255, 0, 9));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/SALIR.png"))); // NOI18N
        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mOpciones.setFont(new java.awt.Font("Garamond", 0, 14)); // NOI18N
        mOpciones.setPreferredSize(new java.awt.Dimension(0, 90));
        mOpciones.setRequestFocusEnabled(false);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/mantenimiento1.png"))); // NOI18N
        jMenu2.setText("MANTENIMIENTO    ");
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu2.setPreferredSize(new java.awt.Dimension(110, 31));
        jMenu2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        miCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/List.png"))); // NOI18N
        miCategoria.setText("Categoria");
        miCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCategoriaActionPerformed(evt);
            }
        });
        jMenu2.add(miCategoria);
        miCategoria.getAccessibleContext().setAccessibleDescription("ctrl+c");

        miCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/User group.png"))); // NOI18N
        miCliente.setText("Cliente");
        miCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClienteActionPerformed(evt);
            }
        });
        jMenu2.add(miCliente);

        miPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/People.png"))); // NOI18N
        miPersonal.setText("Personal");
        miPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPersonalActionPerformed(evt);
            }
        });
        jMenu2.add(miPersonal);

        miProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Favourites.png"))); // NOI18N
        miProducto.setText("Producto");
        miProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProductoActionPerformed(evt);
            }
        });
        jMenu2.add(miProducto);

        miProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Delivery.png"))); // NOI18N
        miProveedor.setText("Proveedor");
        miProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProveedorActionPerformed(evt);
            }
        });
        jMenu2.add(miProveedor);

        miUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Person.png"))); // NOI18N
        miUsuario.setText("Usuario");
        miUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUsuarioActionPerformed(evt);
            }
        });
        jMenu2.add(miUsuario);

        mOpciones.add(jMenu2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/VENTASS.png"))); // NOI18N
        jMenu1.setText("VENTA");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        miGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Buy.png"))); // NOI18N
        miGenerarVenta.setText("Generar Venta");
        miGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGenerarVentaActionPerformed(evt);
            }
        });
        jMenu1.add(miGenerarVenta);

        miCancelarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Cancel.png"))); // NOI18N
        miCancelarVenta.setText("Cancelar Venta");
        miCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCancelarVentaActionPerformed(evt);
            }
        });
        jMenu1.add(miCancelarVenta);

        miCronogramaPago.setBackground(new java.awt.Color(0, 0, 0));
        miCronogramaPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Calendar.png"))); // NOI18N
        miCronogramaPago.setText("Cronograma Pago");
        miCronogramaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCronogramaPagoActionPerformed(evt);
            }
        });
        jMenu1.add(miCronogramaPago);

        miListaReq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Download.png"))); // NOI18N
        miListaReq.setText("Lista Requerimiento");
        miListaReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miListaReqActionPerformed(evt);
            }
        });
        jMenu1.add(miListaReq);

        mOpciones.add(jMenu1);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/COMPRAS.png"))); // NOI18N
        jMenu4.setText("COMPRA");
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        miRegistrarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Buy.png"))); // NOI18N
        miRegistrarCompra.setText("Registrar Compras");
        miRegistrarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarCompraActionPerformed(evt);
            }
        });
        jMenu4.add(miRegistrarCompra);

        mOpciones.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/BUSCAR2.png"))); // NOI18N
        jMenu5.setText("CONSULTAS");
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu5.setPreferredSize(new java.awt.Dimension(100, 30));
        jMenu5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        miConsultaV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Find.png"))); // NOI18N
        miConsultaV.setText("Consulta de Ventas");
        miConsultaV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultaVActionPerformed(evt);
            }
        });
        jMenu5.add(miConsultaV);

        mConsultaC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Find.png"))); // NOI18N
        mConsultaC.setText("Consulta de Compras");
        mConsultaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mConsultaCActionPerformed(evt);
            }
        });
        jMenu5.add(mConsultaC);

        miConsultaProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Find.png"))); // NOI18N
        miConsultaProv.setText("Consulta de Proveedor");
        miConsultaProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultaProvActionPerformed(evt);
            }
        });
        jMenu5.add(miConsultaProv);

        miConsultaPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Find.png"))); // NOI18N
        miConsultaPro.setText("Consulta de Producto");
        miConsultaPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultaProActionPerformed(evt);
            }
        });
        jMenu5.add(miConsultaPro);

        miPagoPendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Find.png"))); // NOI18N
        miPagoPendiente.setText("Consulta de PagoPendientes");
        miPagoPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPagoPendienteActionPerformed(evt);
            }
        });
        jMenu5.add(miPagoPendiente);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Find.png"))); // NOI18N
        jMenuItem6.setText("Consulta Estado de Cuentas");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        mOpciones.add(jMenu5);

        setJMenuBar(mOpciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGenerarVentaActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoVenta frmMV = new FrmMantenimientoVenta();
        frmMV.txtVendedorV.setText(lblNombreVendedor.getText().trim());
        frmMV.setVisible(true);
    }//GEN-LAST:event_miGenerarVentaActionPerformed

    private void miCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCancelarVentaActionPerformed
        // TODO add your handling code here:
        FrmCancelarVenta frmCV = new FrmCancelarVenta();
        frmCV.setVisible(true);
    }//GEN-LAST:event_miCancelarVentaActionPerformed

    private void miCronogramaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCronogramaPagoActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoCronogramaPago frmMCP = new FrmMantenimientoCronogramaPago();
        frmMCP.setVisible(true);
    }//GEN-LAST:event_miCronogramaPagoActionPerformed

    private void miRegistrarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarCompraActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoCompra frmMC = new FrmMantenimientoCompra();
        frmMC.setVisible(true);
    }//GEN-LAST:event_miRegistrarCompraActionPerformed

    private void miCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCategoriaActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoCategoria frmMCat = new FrmMantenimientoCategoria();
        frmMCat.setVisible(true);
    }//GEN-LAST:event_miCategoriaActionPerformed

    private void miClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClienteActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoCliente frmMCli = new FrmMantenimientoCliente();
        frmMCli.setVisible(true);
    }//GEN-LAST:event_miClienteActionPerformed

    private void miPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPersonalActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoPersonal frmMP = new FrmMantenimientoPersonal();
        frmMP.setVisible(true);
    }//GEN-LAST:event_miPersonalActionPerformed

    private void miProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProductoActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoProducto frmMP = new FrmMantenimientoProducto();
        frmMP.setVisible(true);
    }//GEN-LAST:event_miProductoActionPerformed

    private void miProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProveedorActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoProveedor frmMProv = new FrmMantenimientoProveedor();
        frmMProv.setVisible(true);
    }//GEN-LAST:event_miProveedorActionPerformed

    private void miUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUsuarioActionPerformed
        // TODO add your handling code here:
        FrmMantenimientoUsuario frmMU = new FrmMantenimientoUsuario();
        frmMU.setVisible(true);
    }//GEN-LAST:event_miUsuarioActionPerformed

    private void miListaReqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListaReqActionPerformed
        // TODO add your handling code here:
        FrmListaRequerimiento frmListaReq = new FrmListaRequerimiento();
        frmListaReq.setVisible(true);
    }//GEN-LAST:event_miListaReqActionPerformed

    private void miConsultaVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultaVActionPerformed
        // TODO add your handling code here:
        FrmConsultaVentas frmCV = new FrmConsultaVentas();
        frmCV.setVisible(true);
    }//GEN-LAST:event_miConsultaVActionPerformed

    private void mConsultaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mConsultaCActionPerformed
        // TODO add your handling code here:
        FrmConsultaCompra frmCC = new FrmConsultaCompra();
        frmCC.setVisible(true);
    }//GEN-LAST:event_mConsultaCActionPerformed

    private void miConsultaProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultaProActionPerformed
        // TODO add your handling code here:
        FrmConsultaProducto frmCP = new FrmConsultaProducto();
        frmCP.setVisible(true);
    }//GEN-LAST:event_miConsultaProActionPerformed

    private void miConsultaProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultaProvActionPerformed
        // TODO add your handling code here:
        FrmConsultaProveedor frmCProv = new FrmConsultaProveedor();
        frmCProv.setVisible(true);
    }//GEN-LAST:event_miConsultaProvActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void miPagoPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPagoPendienteActionPerformed
        // TODO add your handling code here:
        FrmPagosPendientes frmPP = new FrmPagosPendientes();
        frmPP.setVisible(true);
    }//GEN-LAST:event_miPagoPendienteActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        FrmEstadoCuentaCliente frmECCli = new FrmEstadoCuentaCliente();
        frmECCli.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmMenuPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblNombreVendedor;
    private javax.swing.JMenuItem mConsultaC;
    private javax.swing.JMenuBar mOpciones;
    private javax.swing.JMenuItem miCancelarVenta;
    private javax.swing.JMenuItem miCategoria;
    private javax.swing.JMenuItem miCliente;
    private javax.swing.JMenuItem miConsultaPro;
    private javax.swing.JMenuItem miConsultaProv;
    private javax.swing.JMenuItem miConsultaV;
    public static javax.swing.JMenuItem miCronogramaPago;
    private javax.swing.JMenuItem miGenerarVenta;
    private javax.swing.JMenuItem miListaReq;
    private javax.swing.JMenuItem miPagoPendiente;
    private javax.swing.JMenuItem miPersonal;
    private javax.swing.JMenuItem miProducto;
    private javax.swing.JMenuItem miProveedor;
    private javax.swing.JMenuItem miRegistrarCompra;
    private javax.swing.JMenuItem miUsuario;
    // End of variables declaration//GEN-END:variables
    
    public String fecha;
    public String anual;
    public int mes;
}