
package ComponenteMantenimiento;

import ComponenteClase.Usuario;
import ComponenteConsulta.JDListaUsuario;
import ComponenteDatos.BD;
import ComponenteDatos.BDPersonal;
import ComponenteDatos.BDUsuario;
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
public class FrmMantenimientoUsuario extends javax.swing.JFrame {

    String accion = "";

    /** Creates new form FrmMantenimientoUsuario */
    public FrmMantenimientoUsuario() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        obtenerUltimoId();
        actualizarBusqueda();
        activarBotones(true);
        this.setLocationRelativeTo(null);
        /*
         * Para carbar la lista de Personal al combobox
         */
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select cPerNombre from personal");
            while (rs.next()) {
                cboPersonalUsu.addItem(rs.getObject(1));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    public void obtenerUltimoId() {
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(nUsuCodigo) from usuario");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                txtCodigoUsu.setText(String.valueOf(lastID + 1));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error);
        }
    }

    public void limpiarCajaTexto() {
        txtCodigoUsu.setText("");
        txtIdUsuario.setText("");
        txtClaveUsu.setText("");
        cboPersonalUsu.setSelectedItem("Seleccionar...");
        cboEstadoUsu.setSelectedItem("Seleccionar...");
    }

    public void activarCajaTexto(boolean b) {
        txtCodigoUsu.setEditable(!b);
        txtIdUsuario.setEditable(b);
        txtClaveUsu.setEditable(b);
    }

    public void activarBotones(boolean b) {
        btnNuevoUsu.setEnabled(b);
        btnGuardarUsu.setEnabled(!b);
        btnModificarUsu.setEnabled(b);
        btnCancelarUsu.setEnabled(!b);
        btnMostrarUsu.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarUsu = new javax.swing.JTextField();
        cboParametroUsu = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtUsuario = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboPersonalUsu = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoUsu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboEstadoUsu = new javax.swing.JComboBox();
        txtClaveUsu = new javax.swing.JPasswordField();
        btnNuevoUsu = new javax.swing.JButton();
        btnGuardarUsu = new javax.swing.JButton();
        btnModificarUsu = new javax.swing.JButton();
        btnCancelarUsu = new javax.swing.JButton();
        btnMostrarUsu = new javax.swing.JButton();
        btnSalirUsu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(70, 99, 138));

        jPanel2.setBackground(new java.awt.Color(247, 254, 255));

        jLabel1.setFont(new java.awt.Font("Garamond", 1, 12));
        jLabel1.setText("BUSCAR USUARIO");

        txtBuscarUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarUsuKeyReleased(evt);
            }
        });

        cboParametroUsu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IdUsuario", "Codigo" }));
        cboParametroUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboParametroUsuActionPerformed(evt);
            }
        });

        jtUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Codigo", "IdUsuario"
            }
        ));
        jtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtUsuario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtBuscarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboParametroUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboParametroUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(247, 254, 255));

        jLabel2.setFont(new java.awt.Font("Garamond", 1, 18));
        jLabel2.setText("REGISTRAR USUARIO");

        jPanel4.setBackground(new java.awt.Color(247, 254, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Dato de Usuarios"));

        jLabel4.setText("Id :");

        jLabel6.setText("Personal :");

        txtIdUsuario.setEditable(false);

        jLabel5.setText("Clave :");

        cboPersonalUsu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        jLabel3.setText("Codigo :");

        txtCodigoUsu.setEditable(false);
        txtCodigoUsu.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setText("Estado :");

        cboEstadoUsu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Activo", "Inactivo" }));

        txtClaveUsu.setEditable(false);
        txtClaveUsu.setText("jPasswordField1");
        txtClaveUsu.setSelectionEnd(6);
        txtClaveUsu.setSelectionStart(18);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(txtCodigoUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(35, 35, 35)
                        .addComponent(txtIdUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtClaveUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboPersonalUsu, 0, 169, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboEstadoUsu, 0, 171, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodigoUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtClaveUsu))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboPersonalUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboEstadoUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        btnNuevoUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/New document.png"))); // NOI18N
        btnNuevoUsu.setText("NUEVO");
        btnNuevoUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevoUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuActionPerformed(evt);
            }
        });

        btnGuardarUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Save.png"))); // NOI18N
        btnGuardarUsu.setText("GUARDAR");
        btnGuardarUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuActionPerformed(evt);
            }
        });

        btnModificarUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Modify.png"))); // NOI18N
        btnModificarUsu.setText("MODIFICAR");
        btnModificarUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificarUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUsuActionPerformed(evt);
            }
        });

        btnCancelarUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Erase.png"))); // NOI18N
        btnCancelarUsu.setText("CANCELAR");
        btnCancelarUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelarUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUsuActionPerformed(evt);
            }
        });

        btnMostrarUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/List.png"))); // NOI18N
        btnMostrarUsu.setText("MOSTRAR");
        btnMostrarUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMostrarUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMostrarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarUsuActionPerformed(evt);
            }
        });

        btnSalirUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Exit.png"))); // NOI18N
        btnSalirUsu.setText("SALIR");
        btnSalirUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalirUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalirUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirUsuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalirUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(btnMostrarUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(btnCancelarUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnModificarUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardarUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNuevoUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNuevoUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrarUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalirUsu))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnNuevoUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUsuActionPerformed
        // TODO add your handling code here:
        limpiarCajaTexto();
        activarCajaTexto(true);
        activarBotones(false);
        accion = "Guardar";
        obtenerUltimoId();
    }//GEN-LAST:event_btnNuevoUsuActionPerformed

    private void btnGuardarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuActionPerformed
        // TODO add your handling code here:
        if (accion.equalsIgnoreCase("Guardar")) {
            if (txtIdUsuario.getText().compareTo("") != 0) {
                Usuario u = new Usuario();
                try {
                    u.setcUsuId(txtIdUsuario.getText());
                    String pass = new String(txtClaveUsu.getPassword());
                    u.setcUsuClave(pass);
                    u.setPersonal(BDPersonal.buscarPersonalNombre((String) cboPersonalUsu.getSelectedItem()));
                    u.setcUsuEstado((String) cboEstadoUsu.getSelectedItem());
                    BDUsuario.insertarUsuario(u);
                    JOptionPane.showMessageDialog(null, "[ Datos Agregados ]");
                } catch (SQLException e) {
                    System.out.println("Error BD: " + e.getMessage());
                }
            }
        }
        if (accion.equalsIgnoreCase("Actualizar")) {
            Usuario u;
            try {
                u = BDUsuario.buscarUsuario(txtCodigoUsu.getText());
                u.setcUsuId(txtIdUsuario.getText());
                String pass = new String(txtClaveUsu.getPassword());
                u.setcUsuClave(pass);
                u.setcUsuEstado((String) cboEstadoUsu.getSelectedItem());
                u.setPersonal(BDPersonal.buscarPersonalNombre((String) cboPersonalUsu.getSelectedItem()));
                BDUsuario.actualizarUsuario(u);
                JOptionPane.showMessageDialog(null, " [ Datos Actualizados ]");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error BD: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarUsuActionPerformed

    private void btnModificarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUsuActionPerformed
        // TODO add your handling code here:
        accion = "Actualizar";
        activarCajaTexto(true);
        btnNuevoUsu.setEnabled(false);
        btnGuardarUsu.setEnabled(true);
        btnModificarUsu.setEnabled(false);
        btnCancelarUsu.setEnabled(true);
        btnMostrarUsu.setEnabled(false);
    }//GEN-LAST:event_btnModificarUsuActionPerformed

    private void btnCancelarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUsuActionPerformed
        // TODO add your handling code here:
        limpiarCajaTexto();
        activarCajaTexto(false);
        obtenerUltimoId();
        activarBotones(true);
    }//GEN-LAST:event_btnCancelarUsuActionPerformed

    private void jtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtUsuarioMouseClicked
        // TODO add your handling code here:
        try {
            Usuario u = BDUsuario.buscarUsuario(Integer.parseInt(String.valueOf(jtUsuario.getModel().getValueAt(jtUsuario.getSelectedRow(), 0))));
            txtCodigoUsu.setText(String.valueOf(u.getnUsuCodigo()));
            txtIdUsuario.setText(u.getcUsuId());
            txtClaveUsu.setText(u.getcUsuClave());
            cboPersonalUsu.setSelectedItem((String) u.getPersonal().getcPerNombre());
            cboEstadoUsu.setSelectedItem((String) u.getcUsuEstado());
            if (evt.getClickCount() == 2) {
                JOptionPane.showMessageDialog(null, "Solo seleccione el elemento");
            }
        } catch (SQLException ex) {
            System.out.println("Error Al Seleccionar Elemento:" + ex.getMessage());
        }
    }//GEN-LAST:event_jtUsuarioMouseClicked

    private void btnSalirUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirUsuActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirUsuActionPerformed

    private void txtBuscarUsuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUsuKeyReleased
        // TODO add your handling code here:
        actualizarBusqueda();
    }//GEN-LAST:event_txtBuscarUsuKeyReleased

    private void cboParametroUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboParametroUsuActionPerformed
        // TODO add your handling code here:
        actualizarBusqueda();
    }//GEN-LAST:event_cboParametroUsuActionPerformed

    private void btnMostrarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarUsuActionPerformed
        // TODO add your handling code here:
        JDListaUsuario jdListaU = new JDListaUsuario(this, true);
        jdListaU.setVisible(true);
    }//GEN-LAST:event_btnMostrarUsuActionPerformed

    private void actualizarBusqueda() {
        ArrayList<Usuario> result = null;
        if (String.valueOf(cboParametroUsu.getSelectedItem()).equalsIgnoreCase("Codigo")) {
            result = BDUsuario.listarUsuarioPorCodigo(txtBuscarUsu.getText());

        } else if (String.valueOf(cboParametroUsu.getSelectedItem()).equalsIgnoreCase("IdUsuario")) {
            result = BDUsuario.listarUsuarioPorId(txtBuscarUsu.getText());
        }
        recargarTable(result);
    }

    public void recargarTable(ArrayList<Usuario> list) {
        Object[][] datos = new Object[list.size()][2];
        int i = 0;
        for (Usuario u : list) {
            datos[i][0] = u.getnUsuCodigo();
            datos[i][1] = u.getcUsuId();
            i++;
        }
        jtUsuario.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "CODIGO", "ID USUARIO"
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
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmMantenimientoUsuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarUsu;
    private javax.swing.JButton btnGuardarUsu;
    private javax.swing.JButton btnModificarUsu;
    private javax.swing.JButton btnMostrarUsu;
    private javax.swing.JButton btnNuevoUsu;
    private javax.swing.JButton btnSalirUsu;
    private javax.swing.JComboBox cboEstadoUsu;
    private javax.swing.JComboBox cboParametroUsu;
    private javax.swing.JComboBox cboPersonalUsu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtUsuario;
    private javax.swing.JTextField txtBuscarUsu;
    private javax.swing.JPasswordField txtClaveUsu;
    private javax.swing.JTextField txtCodigoUsu;
    private javax.swing.JTextField txtIdUsuario;
    // End of variables declaration//GEN-END:variables
}