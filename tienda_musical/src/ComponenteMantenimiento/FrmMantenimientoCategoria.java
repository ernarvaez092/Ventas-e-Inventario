
package ComponenteMantenimiento;

import ComponenteClase.Categoria;
import ComponenteConsulta.JDListaCategorias;
import ComponenteDatos.BD;
import ComponenteDatos.BDCategoria;
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
public class FrmMantenimientoCategoria extends javax.swing.JFrame {

    String accion = "";
    
    /** Creates new form FrmMantenimientoCategoria */
    public FrmMantenimientoCategoria() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        obtenerUltimoId();
        this.setLocationRelativeTo(null);
        activarBotones(true);
    }

    public void obtenerUltimoId() {
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(nCatCodigo) as 'Codigo' from categoria");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                txtCodigoCat.setText(String.valueOf(lastID + 1));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error);
        }
    }

    public void buscarCategoria() {

        if (cboParametroCat.getSelectedItem().toString().equalsIgnoreCase("Codigo")) {
            Categoria c = null;
            if (isNumeric(txtBuscarCat.getText())) {
                try {
                    c = BDCategoria.buscarCategoriaCodigo(Integer.parseInt(txtBuscarCat.getText()));
                } catch (SQLException e) {
                    System.out.println("" + e.getMessage());
                }
                if (c != null) {
                    txtCodigoCat.setText(String.valueOf(c.getnCatCodigo()));
                    txtDescripcionCat.setText(c.getcCatDescripcion());
                } else {
                    JOptionPane.showMessageDialog(null, "No existe elemento buscado");
                }
            }
        }
        if (cboParametroCat.getSelectedItem().toString().equalsIgnoreCase("Descripcion")) {
            Categoria c = null;
            if (!isNumeric(txtBuscarCat.getText())) {
                try {
                    c = BDCategoria.buscarCategoriaDescripcion(txtBuscarCat.getText());
                } catch (SQLException e) {
                    System.out.println("" + e.getMessage());
                }
                if (c != null) {
                    txtCodigoCat.setText(String.valueOf(c.getnCatCodigo()));
                    txtDescripcionCat.setText(c.getcCatDescripcion());
                } else {
                    JOptionPane.showMessageDialog(null, "No existe elemento buscado");
                }
            }
        }
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void limpiarCajaTexto() {
        txtCodigoCat.setText("");
        txtDescripcionCat.setText("");
    }

    public void activarCajaTexto(boolean b) {
        txtCodigoCat.setEditable(!b);
        txtDescripcionCat.setEditable(b);
    }
    
    public void activarBotones(boolean b){
        btnNuevoCat.setEnabled(b);
        btnGuardarCat.setEnabled(!b);
        btnModificarCat.setEnabled(b);
        btnCancelarCat.setEnabled(!b);
        btnMostrarCat.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtBuscarCat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboParametroCat = new javax.swing.JComboBox();
        btnBuscarCat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoCat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcionCat = new javax.swing.JTextField();
        btnNuevoCat = new javax.swing.JButton();
        btnGuardarCat = new javax.swing.JButton();
        btnCancelarCat = new javax.swing.JButton();
        btnSalirCat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnMostrarCat = new javax.swing.JButton();
        btnModificarCat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(70, 99, 138));

        jPanel2.setBackground(new java.awt.Color(247, 254, 255));

        txtBuscarCat.setForeground(new java.awt.Color(255, 0, 0));
        txtBuscarCat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarCat.setText("Ingrese los datos a buscar");
        txtBuscarCat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarCatMouseClicked(evt);
            }
        });
        txtBuscarCat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarCatKeyReleased(evt);
            }
        });

        jLabel3.setText("BUSCAR :");

        cboParametroCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Descripcion" }));

        btnBuscarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Zoom.png"))); // NOI18N
        btnBuscarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboParametroCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cboParametroCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscarCat, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(247, 254, 255));

        jLabel1.setText("Descripcion:");

        txtCodigoCat.setEditable(false);
        txtCodigoCat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Codigo :");

        txtDescripcionCat.setEditable(false);
        txtDescripcionCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionCatActionPerformed(evt);
            }
        });

        btnNuevoCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/New document.png"))); // NOI18N
        btnNuevoCat.setText("NUEVO");
        btnNuevoCat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoCat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevoCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCatActionPerformed(evt);
            }
        });

        btnGuardarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Save.png"))); // NOI18N
        btnGuardarCat.setText("GUARDAR");
        btnGuardarCat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarCat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCatActionPerformed(evt);
            }
        });

        btnCancelarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Erase.png"))); // NOI18N
        btnCancelarCat.setText("CANCELAR");
        btnCancelarCat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelarCat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCatActionPerformed(evt);
            }
        });

        btnSalirCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Exit.png"))); // NOI18N
        btnSalirCat.setText("SALIR");
        btnSalirCat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalirCat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalirCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirCatActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Garamond", 1, 20)); // NOI18N
        jLabel4.setText("REGISTRAR CATEGORIA");

        btnMostrarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/List.png"))); // NOI18N
        btnMostrarCat.setText("MOSTRAR");
        btnMostrarCat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMostrarCat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMostrarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarCatActionPerformed(evt);
            }
        });

        btnModificarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Modify.png"))); // NOI18N
        btnModificarCat.setText("MODIFICAR");
        btnModificarCat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificarCat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoCat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcionCat, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevoCat, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarCat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalirCat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigoCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescripcionCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalirCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnMostrarCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelarCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificarCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardarCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevoCat, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionCatActionPerformed

    private void btnMostrarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarCatActionPerformed
        // TODO add your handling code here:
        JDListaCategorias jdListaCategoria = new JDListaCategorias(this,true);
        jdListaCategoria.setVisible(true);
    }//GEN-LAST:event_btnMostrarCatActionPerformed

    private void txtBuscarCatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarCatMouseClicked
        // TODO add your handling code here:
        txtBuscarCat.setText("");
    }//GEN-LAST:event_txtBuscarCatMouseClicked

    private void btnNuevoCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCatActionPerformed
        // TODO add your handling code here:
        limpiarCajaTexto();
        activarCajaTexto(true);
        activarBotones(false);
        /*btnNuevoCat.setEnabled(false);
        btnGuardarCat.setEnabled(true);
        btnModificarCat.setEnabled(false);
        btnCancelarCat.setEnabled(true);
        btnMostrarCat.setEnabled(false);*/
        obtenerUltimoId();
        accion = "Guardar";
    }//GEN-LAST:event_btnNuevoCatActionPerformed

    private void btnModificarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCatActionPerformed
        // TODO add your handling code here:
        activarCajaTexto(true);
        btnNuevoCat.setEnabled(false);
        btnGuardarCat.setEnabled(true);
        btnModificarCat.setEnabled(false);
        btnCancelarCat.setEnabled(true);
        btnMostrarCat.setEnabled(false);
        accion = "Actualizar";
    }//GEN-LAST:event_btnModificarCatActionPerformed

    private void btnGuardarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCatActionPerformed
        // TODO add your handling code here:
        if (accion.equalsIgnoreCase("Guardar")) {
            if (txtCodigoCat.getText().compareTo("") != 0 && txtDescripcionCat.getText().compareTo("") != 0) {
                try {
                    Categoria c = new Categoria();
                    c.setcCatDescripcion(txtDescripcionCat.getText().toUpperCase());
                    BDCategoria.insertarCategoria(c);
                    JOptionPane.showMessageDialog(null, "[ Datos Agregados ]");
                } catch (SQLException e) {
                    System.out.println("Error BD: " + e.getMessage());
                }
                limpiarCajaTexto();
                obtenerUltimoId();
            } else {
                JOptionPane.showMessageDialog(null, "Llene Todos Los Campos..!!");
            }
        }
        if (accion.equalsIgnoreCase("Actualizar")) {
            Categoria cat;
            try {
                cat = BDCategoria.buscarCategoriaCodigo(Integer.parseInt(txtCodigoCat.getText()));
                cat.setcCatDescripcion(txtDescripcionCat.getText());
                BDCategoria.actualizarCategoria(cat);
                JOptionPane.showMessageDialog(null, " [ Datos Actualizados ]");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error BD: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarCatActionPerformed

    private void btnCancelarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCatActionPerformed
        // TODO add your handling code here:
        limpiarCajaTexto();
        obtenerUltimoId();
        activarBotones(true);
    }//GEN-LAST:event_btnCancelarCatActionPerformed

    private void btnSalirCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirCatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirCatActionPerformed

    private void btnBuscarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCatActionPerformed
        // TODO add your handling code here:
        buscarCategoria();
    }//GEN-LAST:event_btnBuscarCatActionPerformed

    private void txtBuscarCatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCatKeyReleased
 buscarCategoria();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCatKeyReleased

    /**
     * @param args the command line argume
     * nts
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
            java.util.logging.Logger.getLogger(FrmMantenimientoCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmMantenimientoCategoria().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCat;
    private javax.swing.JButton btnCancelarCat;
    private javax.swing.JButton btnGuardarCat;
    private javax.swing.JButton btnModificarCat;
    private javax.swing.JButton btnMostrarCat;
    private javax.swing.JButton btnNuevoCat;
    private javax.swing.JButton btnSalirCat;
    private javax.swing.JComboBox cboParametroCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtBuscarCat;
    private javax.swing.JTextField txtCodigoCat;
    private javax.swing.JTextField txtDescripcionCat;
    // End of variables declaration//GEN-END:variables
}
