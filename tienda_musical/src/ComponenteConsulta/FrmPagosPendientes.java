
package ComponenteConsulta;

import ComponenteClase.Credito;
import ComponenteClase.CronogramaPago;
import ComponenteClase.Venta;
import ComponenteDatos.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Efren Narvaez
 */
public class FrmPagosPendientes extends javax.swing.JFrame {

    /** Creates new form FrmPagosPendientes */
    public FrmPagosPendientes() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void actualizarEstadoCredito() {
        double montoCredito = 0.0, monto = 0.0;
        monto = obtenerMonto();
        try {
            Credito c = BDCredito.buscarCredito(getCodigoVenta());
            montoCredito = c.getnCreMontoDebito();
            if (montoCredito == monto) {
                c.setcCreEstado("PagoCancelado");
                BDCredito.actualizarCronogramaPago(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public String cantproducto(int codigo){
        String total="";
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select SUM(nTraCantidad) total from transaccion where nVenCodigo=" + codigo);
            while (rs.next()) {
                total = rs.getString("total");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.println();
        }
        return total;
    }

    public double obtenerMonto() {
        double monto = 0.0;
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select SUM(nCPagoRecibido) MontoEsperado from cronogramapago where nVenCodigo=" + getCodigoVenta());
            while (rs.next()) {
                monto = rs.getDouble(1);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
        return monto;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        btnBuscarCli = new javax.swing.JButton();
        txtNombreCli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarVenta = new javax.swing.JTextField();
        cboParametro = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNroCuota = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFechaPago = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMontoCuota = new javax.swing.JTextField();
        txtMontoRecibido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtListaCronogramaP = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListaVentaCredito = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PAGOS PENDIENTES");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(70, 99, 138));

        jPanel2.setBackground(new java.awt.Color(247, 254, 255));

        jPanel3.setBackground(new java.awt.Color(247, 254, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Buscar Cliente :");

        txtCodCliente.setEditable(false);
        txtCodCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuscarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Find.png"))); // NOI18N
        btnBuscarCli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCli.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCliActionPerformed(evt);
            }
        });

        txtNombreCli.setEditable(false);

        jLabel2.setText("Buscar Venta :");

        txtBuscarVenta.setEditable(false);
        txtBuscarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarVentaActionPerformed(evt);
            }
        });
        txtBuscarVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarVentaKeyReleased(evt);
            }
        });

        cboParametro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Fecha" }));
        cboParametro.setEnabled(false);
        cboParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboParametroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarVenta))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCli)
                    .addComponent(cboParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(btnBuscarCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(247, 254, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Nro Cuota :");

        txtNroCuota.setEditable(false);
        txtNroCuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroCuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroCuotaActionPerformed(evt);
            }
        });

        jLabel4.setText("FechaPago :");

        txtFechaPago.setEditable(false);
        txtFechaPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setText("Monto Cuota :");

        txtMontoCuota.setEditable(false);
        txtMontoCuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtMontoRecibido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Monto Recibido :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNroCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontoRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNroCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtMontoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtMontoRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtListaCronogramaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NRO CUOTA", "FECHA PAGO", "MONTO CUOTA", "MONTO RECIBIDO"
            }
        ));
        jtListaCronogramaP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtListaCronogramaPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtListaCronogramaP);
        jtListaCronogramaP.getColumnModel().getColumn(0).setPreferredWidth(5);
        jtListaCronogramaP.getColumnModel().getColumn(1).setPreferredWidth(30);
        jtListaCronogramaP.getColumnModel().getColumn(2).setPreferredWidth(30);
        jtListaCronogramaP.getColumnModel().getColumn(3).setPreferredWidth(25);

        jLabel8.setFont(new java.awt.Font("Garamond", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CRONOGRAMA PAGOS");

        jtListaVentaCredito.setAutoCreateRowSorter(true);
        jtListaVentaCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "VENTA", "CLIENTE", "FECHA", "MONTO DEUDA"
            }
        ));
        jtListaVentaCredito.setRequestFocusEnabled(false);
        jtListaVentaCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtListaVentaCreditoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtListaVentaCredito);

        jLabel7.setFont(new java.awt.Font("Garamond", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("LISTA DE VENTAS A CREDITO");

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Save.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(57, 57, 57)
                                            .addComponent(jLabel7)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(92, 92, 92)))))
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNroCuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroCuotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroCuotaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCliActionPerformed
        // TODO add your handling code here:
        JDBuscarClientePagoPendiente jdCPP = new JDBuscarClientePagoPendiente(this, true, this);
        jdCPP.setVisible(true);
    }//GEN-LAST:event_btnBuscarCliActionPerformed

    private void cboParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboParametroActionPerformed
        // TODO add your handling code here:
        actualizarBusquedaVenta();
    }//GEN-LAST:event_cboParametroActionPerformed

    private void txtBuscarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarVentaActionPerformed

    private void txtBuscarVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarVentaKeyReleased
        // TODO add your handling code here:
        actualizarBusquedaVenta();
    }//GEN-LAST:event_txtBuscarVentaKeyReleased

    private void jtListaVentaCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtListaVentaCreditoMouseClicked
        // TODO add your handling code here:
        try {
            Venta v = BDVenta.buscarVenta(Integer.parseInt(String.valueOf(jtListaVentaCredito.getModel().getValueAt(jtListaVentaCredito.getSelectedRow(), 0))));
            actualizarBusquedaCronogramaP(v.getnVenCodigo());
            this.setCodigoVenta(v.getnVenCodigo());            
            btnGuardar.setEnabled(true);
        } catch (SQLException ex) {
            System.out.println("Error Al Seleccionar Elemento:" + ex.getMessage());
        }
        //
    }//GEN-LAST:event_jtListaVentaCreditoMouseClicked

    private void jtListaCronogramaPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtListaCronogramaPMouseClicked
        // TODO add your handling code here:
        double montoCuota,montoDebito;
        txtNroCuota.setText(String.valueOf(jtListaCronogramaP.getModel().getValueAt(jtListaCronogramaP.getSelectedRow(), 0)));
        txtFechaPago.setText(String.valueOf(jtListaCronogramaP.getModel().getValueAt(jtListaCronogramaP.getSelectedRow(), 1)));
        txtMontoCuota.setText(String.valueOf(jtListaCronogramaP.getModel().getValueAt(jtListaCronogramaP.getSelectedRow(), 2)));
        montoCuota=Double.parseDouble(String.valueOf(jtListaCronogramaP.getModel().getValueAt(jtListaCronogramaP.getSelectedRow(), 2)));
        txtMontoRecibido.setText(String.valueOf(jtListaCronogramaP.getModel().getValueAt(jtListaCronogramaP.getSelectedRow(), 3)));
        montoDebito=Double.parseDouble(String.valueOf(jtListaCronogramaP.getModel().getValueAt(jtListaCronogramaP.getSelectedRow(), 3)));
        btnGuardar.setEnabled(true);
        if(montoCuota==montoDebito){
           txtMontoRecibido.setEditable(false);
        }else{
            txtMontoRecibido.setEditable(true);
        }
    }//GEN-LAST:event_jtListaCronogramaPMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtMontoRecibido.getText().compareTo("") != 0) {
            CronogramaPago cp = new CronogramaPago();
            try {
                cp.setVenta(BDVenta.buscarVenta(getCodigoVenta()));
                cp.setnCPagoRecibido(Double.parseDouble(txtMontoRecibido.getText()));
                BDCronogramaPago.actualizarCronogramaPago(cp, Integer.parseInt(txtNroCuota.getText()));
                JOptionPane.showMessageDialog(null, "[ Pago Guardado Correctamente ]");
                btnGuardar.setEnabled(false);
            } catch (SQLException ex) {
                System.out.println("" + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, " Seleccione un Nro de Cuota a Cancelar");
        }
        actualizarBusquedaCronogramaP(getCodigoVenta());
        actualizarEstadoCredito();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void actualizarBusquedaCronogramaP(int nVenCodigo) {
        ArrayList<CronogramaPago> resultCP = null;
        resultCP = BDCronogramaPago.listarCronogramaPagoPorVenta(nVenCodigo);
        recargarTableCronograma(resultCP);
    }

    public void recargarTableCronograma(ArrayList<CronogramaPago> lista) {
        Object[][] datos = new Object[lista.size()][4];
        int i = 0;
        for (CronogramaPago cp : lista) {
            datos[i][0] = cp.getnNroCuota();
            datos[i][1] = cp.getcCPagoFecha();
            datos[i][2] = cp.getnCPagoCuota();
            datos[i][3] = cp.getnCPagoRecibido();
            i++;
        }
        jtListaCronogramaP.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "NRO CUOTA", "FECHA PAGO", "MONTO CUOTA", "MONTO RECIBIDO"
                }) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private void actualizarBusquedaVenta() {
        ArrayList<Venta> resultV = null;
        if (String.valueOf(cboParametro.getSelectedItem()).equalsIgnoreCase("Codigo")) {
            resultV = BDVenta.listarVentaPorCodigoPagoP(Integer.parseInt(txtCodCliente.getText()), txtBuscarVenta.getText());
        } else if (String.valueOf(cboParametro.getSelectedItem()).equalsIgnoreCase("Fecha")) {
            resultV = BDVenta.listarVentaPorFechaPagoP(Integer.parseInt(txtCodCliente.getText()), txtBuscarVenta.getText());
        }
        recargarTable(resultV);
    }

    public void recargarTable(ArrayList<Venta> list) {
        Object[][] datos = new Object[list.size()][5];
        int i = 0;
        for (Venta v : list) {
            datos[i][0] = v.getnVenCodigo();
            datos[i][1] = v.getCliente().getcCliNombre();
            datos[i][2] = v.getcVenFecha();
            datos[i][3] = v.getnVenMontoTotal();
            datos[i][4] = v.getTipoDocumento().getcTipoDocDescripcion();
            i++;
        }
        jtListaVentaCredito.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "CODIGO", "CLIENTE", "FECHA", "MONTO TOTAL", "DOCUMENTO"
                }) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

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
            java.util.logging.Logger.getLogger(FrmPagosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPagosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPagosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPagosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmPagosPendientes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCli;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    public javax.swing.JComboBox cboParametro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtListaCronogramaP;
    private javax.swing.JTable jtListaVentaCredito;
    public javax.swing.JTextField txtBuscarVenta;
    public javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtFechaPago;
    private javax.swing.JTextField txtMontoCuota;
    private javax.swing.JTextField txtMontoRecibido;
    public javax.swing.JTextField txtNombreCli;
    private javax.swing.JTextField txtNroCuota;
    // End of variables declaration//GEN-END:variables
    private int codigoVenta;

}