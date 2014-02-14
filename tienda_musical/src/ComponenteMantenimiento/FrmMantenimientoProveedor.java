
package ComponenteMantenimiento;

import ComponenteClase.Proveedor;
import ComponenteConsulta.JDListaProveedor;
import ComponenteDatos.BD;
import ComponenteDatos.BDProveedor;
import java.awt.Toolkit;
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
public class FrmMantenimientoProveedor extends javax.swing.JFrame {

    String accion;

    /** Creates new form FrmMantenimientoProveedor */
    public FrmMantenimientoProveedor() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        actualizarBusqueda();
        obtenerUltimoId();
        activarBotones(true);
        this.setLocationRelativeTo(null);
    }

    public void obtenerUltimoId() {
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(nProvCodigo) from proveedor");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                txtCodigoProv.setText(String.valueOf(lastID + 1));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error);
        }
    }

    public void limpiarCajaTexto() {
        txtCodigoProv.setText("");
        txtNitProv.setText("");
        txtNombreProv.setText("");
        txtDireccionProv.setText("");
        txtNroFaxProv.setText("");
        cboTipoTelefonoProv.setSelectedItem("Seleccionar...");
        txtNroTelefonoProv.setText("");
        txtPaginaWebProv.setText("");
        txtEmailProv.setText("");
        cboEstadoProv.setSelectedItem("Seleccionar...");
        txtObservacionesRep.setText("");
    }

    public void activarCajaTexto(boolean b) {
        txtCodigoProv.setEditable(!b);
        txtNombreProv.setEditable(b);
        txtDireccionProv.setEditable(b);
        txtEmailProv.setEditable(b);
        txtNitProv.setEditable(b);
        txtNroFaxProv.setEditable(b);
        txtNroTelefonoProv.setEditable(b);
        txtObservacionesRep.setEditable(b);
        txtPaginaWebProv.setEditable(b);
    }

    public void activarBotones(boolean b) {
        btnNuevoProv.setEnabled(b);
        btnGuardarProv.setEnabled(!b);
        btnModificarProv.setEnabled(b);
        btnCancelarProv.setEnabled(!b);
        btnMostrarProv.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarProv = new javax.swing.JTextField();
        cboParametroProv = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProveedor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTipoTelefonoProv = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodigoProv = new javax.swing.JTextField();
        txtNitProv = new javax.swing.JTextField();
        txtNombreProv = new javax.swing.JTextField();
        txtDireccionProv = new javax.swing.JTextField();
        txtNroFaxProv = new javax.swing.JTextField();
        txtNroTelefonoProv = new javax.swing.JTextField();
        txtPaginaWebProv = new javax.swing.JTextField();
        txtEmailProv = new javax.swing.JTextField();
        cboEstadoProv = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacionesRep = new javax.swing.JTextArea();
        btnNuevoProv = new javax.swing.JButton();
        btnGuardarProv = new javax.swing.JButton();
        btnModificarProv = new javax.swing.JButton();
        btnCancelarProv = new javax.swing.JButton();
        btnMostrarProv = new javax.swing.JButton();
        btnSalirProv = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(70, 99, 138));

        jPanel2.setBackground(new java.awt.Color(247, 254, 255));

        jLabel1.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jLabel1.setText("BUSCAR PROVEEDOR");

        txtBuscarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProvActionPerformed(evt);
            }
        });
        txtBuscarProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProvKeyReleased(evt);
            }
        });

        cboParametroProv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RUC", "Nombre", "Codigo" }));
        cboParametroProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboParametroProvActionPerformed(evt);
            }
        });

        jtProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "RUC", "Nombre"
            }
        ));
        jtProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtProveedor);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtBuscarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboParametroProv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboParametroProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(247, 254, 255));

        jPanel4.setBackground(new java.awt.Color(247, 254, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(247, 254, 255));

        jLabel2.setText("Codigo (*):");

        jLabel3.setText("RUC (*):");

        jLabel4.setText("Nombre (*):");

        jLabel5.setText("Direccion :");

        jLabel6.setText("NroFax :");

        jLabel7.setText("Tipo Telefono (*):");

        cboTipoTelefonoProv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Casa", "Celular" }));

        jLabel8.setText("Pagina Web :");

        jLabel9.setText("Email :");

        jLabel10.setText("Estado :");

        txtCodigoProv.setEditable(false);
        txtCodigoProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNitProv.setEditable(false);
        txtNitProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNitProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNitProvKeyTyped(evt);
            }
        });

        txtNombreProv.setEditable(false);

        txtDireccionProv.setEditable(false);

        txtNroFaxProv.setEditable(false);
        txtNroFaxProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNroTelefonoProv.setEditable(false);
        txtNroTelefonoProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroTelefonoProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroTelefonoProvKeyTyped(evt);
            }
        });

        txtPaginaWebProv.setEditable(false);

        txtEmailProv.setEditable(false);

        cboEstadoProv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Activo", "Inactivo" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroTelefonoProv, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailProv, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(txtPaginaWebProv, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(cboEstadoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtNroFaxProv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtDireccionProv, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreProv)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNitProv)
                            .addComponent(txtCodigoProv, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNitProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccionProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtNroFaxProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboTipoTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNroTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPaginaWebProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmailProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboEstadoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(247, 254, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        txtObservacionesRep.setColumns(20);
        txtObservacionesRep.setEditable(false);
        txtObservacionesRep.setRows(5);
        jScrollPane2.setViewportView(txtObservacionesRep);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnNuevoProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/New document.png"))); // NOI18N
        btnNuevoProv.setText("NUEVO");
        btnNuevoProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevoProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProvActionPerformed(evt);
            }
        });

        btnGuardarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Save.png"))); // NOI18N
        btnGuardarProv.setText("GUARDAR");
        btnGuardarProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProvActionPerformed(evt);
            }
        });

        btnModificarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Modify.png"))); // NOI18N
        btnModificarProv.setText("MODIFICAR");
        btnModificarProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificarProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProvActionPerformed(evt);
            }
        });

        btnCancelarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Erase.png"))); // NOI18N
        btnCancelarProv.setText("CANCELAR");
        btnCancelarProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelarProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProvActionPerformed(evt);
            }
        });

        btnMostrarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/List.png"))); // NOI18N
        btnMostrarProv.setText("MOSTRAR");
        btnMostrarProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMostrarProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMostrarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarProvActionPerformed(evt);
            }
        });

        btnSalirProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComponenteImagenes/Exit.png"))); // NOI18N
        btnSalirProv.setText("SALIR");
        btnSalirProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalirProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalirProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirProvActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Garamond", 1, 18)); // NOI18N
        jLabel13.setText("REGISTRAR PROVEEDOR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnNuevoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnGuardarProv)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarProv)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarProv)
                        .addGap(18, 18, 18)
                        .addComponent(btnMostrarProv)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalirProv, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnSalirProv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnGuardarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnModificarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnCancelarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnMostrarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE))
                .addGap(21, 21, 21))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnSalirProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirProvActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirProvActionPerformed

    private void btnNuevoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProvActionPerformed
        // TODO add your handling code here:
        limpiarCajaTexto();
        activarCajaTexto(true);
        activarBotones(false);
        accion = "Guardar";
        obtenerUltimoId();
    }//GEN-LAST:event_btnNuevoProvActionPerformed

    private void cboParametroProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboParametroProvActionPerformed
        // TODO add your handling code here:
        actualizarBusqueda();
    }//GEN-LAST:event_cboParametroProvActionPerformed

    private void txtBuscarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProvActionPerformed

    private void txtBuscarProvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProvKeyReleased
        // TODO add your handling code here:
        actualizarBusqueda();
    }//GEN-LAST:event_txtBuscarProvKeyReleased

    private void btnGuardarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProvActionPerformed
        // TODO add your handling code here:
        if (accion.equalsIgnoreCase("Guardar")) {
            if (txtNitProv.getText().compareTo("") != 0 && txtNombreProv.getText().compareTo("") != 0 && txtDireccionProv.getText().compareTo("") != 0
                    && txtNroTelefonoProv.getText().compareTo("") != 0) {
                Proveedor pv = new Proveedor();
                try {
                    pv.setnPRovNit(Integer.parseInt(txtNitProv.getText()));
                    pv.setcProvNombre(txtNombreProv.getText());
                    pv.setcProvDireccion(txtDireccionProv.getText());
                    pv.setcProvNroFax(txtNroFaxProv.getText());
                    pv.setcProvEmail(txtEmailProv.getText());
                    pv.setcProvPaginaWeb(txtPaginaWebProv.getText());
                    pv.setcProvTipoTelefono((String) cboTipoTelefonoProv.getSelectedItem());
                    pv.setcProvNumTelefono(txtNroTelefonoProv.getText());
                    pv.setcProvEstado((String) cboEstadoProv.getSelectedItem());
                    pv.setcProvObservacion(txtObservacionesRep.getText());
                    BDProveedor.insertarProveedor(pv);
                    JOptionPane.showMessageDialog(null, "[ Datos Agregados ]");
                } catch (SQLException e) {
                    System.out.println("Error BD: " + e.getMessage());
                }
                limpiarCajaTexto();
                obtenerUltimoId();
            } else {
                JOptionPane.showMessageDialog(null, "Llene Todos Los Campos Requeridos..!!");
            }
        }
        if (accion.equalsIgnoreCase("Actualizar")) {
            Proveedor pv;
            try {
                pv = BDProveedor.buscarProveedor(Integer.parseInt(txtCodigoProv.getText()));
                pv.setnPRovNit(Integer.parseInt(txtNitProv.getText()));
                pv.setcProvNombre(txtNombreProv.getText());
                pv.setcProvDireccion(txtDireccionProv.getText());
                pv.setcProvNroFax(txtNroFaxProv.getText());
                pv.setcProvEmail(txtEmailProv.getText());
                pv.setcProvPaginaWeb(txtPaginaWebProv.getText());
                pv.setcProvTipoTelefono((String)cboTipoTelefonoProv.getSelectedItem());
                pv.setcProvNumTelefono(txtNroTelefonoProv.getText());
                pv.setcProvEstado((String)cboEstadoProv.getSelectedItem());
                pv.setcProvObservacion(txtObservacionesRep.getText());
                BDProveedor.actualizarProveedor(pv);
                JOptionPane.showMessageDialog(null, " [ Datos Actualizados ]");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error BD: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarProvActionPerformed

    private void btnModificarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProvActionPerformed
        // TODO add your handling code here:
        accion = "Actualizar";
        activarCajaTexto(true);
        btnNuevoProv.setEnabled(false);
        btnGuardarProv.setEnabled(true);
        btnModificarProv.setEnabled(false);
        btnCancelarProv.setEnabled(true);
        btnMostrarProv.setEnabled(false);
    }//GEN-LAST:event_btnModificarProvActionPerformed

    private void btnCancelarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProvActionPerformed
        // TODO add your handling code here:
        limpiarCajaTexto();
        activarCajaTexto(false);
        obtenerUltimoId();
        activarBotones(true);
    }//GEN-LAST:event_btnCancelarProvActionPerformed

    private void txtNitProvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitProvKeyTyped
        // TODO add your handling code here:
        if (String.valueOf(evt.getKeyChar()).matches("[a-zA-Z]|\\s")) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNitProvKeyTyped

    private void txtNroTelefonoProvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroTelefonoProvKeyTyped
        // TODO add your handling code here:
        if (String.valueOf(evt.getKeyChar()).matches("[a-zA-Z]|\\s")) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNroTelefonoProvKeyTyped

    private void jtProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProveedorMouseClicked
        // TODO add your handling code here:
        try {
            Proveedor prov = BDProveedor.buscarProveedor(Integer.parseInt(String.valueOf(jtProveedor.getModel().getValueAt(jtProveedor.getSelectedRow(),0))));
            txtCodigoProv.setText(String.valueOf(prov.getnProvCodigo()));
            txtNombreProv.setText(prov.getcProvNombre());
            txtNitProv.setText(String.valueOf(prov.getnPRovNit()));
            txtDireccionProv.setText(prov.getcProvDireccion());
            txtNroFaxProv.setText(prov.getcProvNroFax());
            txtEmailProv.setText(prov.getcProvEmail());
            txtPaginaWebProv.setText(prov.getcProvPaginaWeb());
            cboTipoTelefonoProv.setSelectedItem((String)prov.getcProvTipoTelefono());
            txtNroTelefonoProv.setText(prov.getcProvNumTelefono());
            cboEstadoProv.setSelectedItem((String)prov.getcProvEstado());
            txtObservacionesRep.setText(prov.getcProvObservacion());
            if (evt.getClickCount() == 2){
                JOptionPane.showMessageDialog(null, "Solo seleccione el elemento");
            }
        } catch (SQLException ex) {
            System.out.println("Error Al Seleccionar Elemento:" + ex.getMessage());
        }
    }//GEN-LAST:event_jtProveedorMouseClicked

    private void btnMostrarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarProvActionPerformed
        // TODO add your handling code here:
        JDListaProveedor jdListaP = new JDListaProveedor(this,true);
        jdListaP.setVisible(true);
    }//GEN-LAST:event_btnMostrarProvActionPerformed

    private void actualizarBusqueda() {
        ArrayList<Proveedor> result = null;
        if (String.valueOf(cboParametroProv.getSelectedItem()).equalsIgnoreCase("RUC")) {
            result = BDProveedor.listarProveedorPorNit(txtBuscarProv.getText());
        } else if (String.valueOf(cboParametroProv.getSelectedItem()).equalsIgnoreCase("Nombre")) {
            result = BDProveedor.listarProveedorPorNombre(txtBuscarProv.getText());
        } else if (String.valueOf(cboParametroProv.getSelectedItem()).equalsIgnoreCase("Codigo")) {
            result = BDProveedor.listarProveedorPorCodigo(txtBuscarProv.getText());
        }
        recargarTable(result);
    }

    public void recargarTable(ArrayList<Proveedor> list) {
        Object[][] datos = new Object[list.size()][3];
        int i = 0;
        for (Proveedor pv : list) {
            datos[i][0] = pv.getnProvCodigo();
            datos[i][1] = pv.getnPRovNit();
            datos[i][2] = pv.getcProvNombre();
            i++;
        }
        jtProveedor.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "CODIGO", "RUC", "NOMBRE"
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
            java.util.logging.Logger.getLogger(FrmMantenimientoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmMantenimientoProveedor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarProv;
    private javax.swing.JButton btnGuardarProv;
    private javax.swing.JButton btnModificarProv;
    private javax.swing.JButton btnMostrarProv;
    private javax.swing.JButton btnNuevoProv;
    private javax.swing.JButton btnSalirProv;
    private javax.swing.JComboBox cboEstadoProv;
    private javax.swing.JComboBox cboParametroProv;
    private javax.swing.JComboBox cboTipoTelefonoProv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtProveedor;
    private javax.swing.JTextField txtBuscarProv;
    private javax.swing.JTextField txtCodigoProv;
    private javax.swing.JTextField txtDireccionProv;
    private javax.swing.JTextField txtEmailProv;
    private javax.swing.JTextField txtNitProv;
    private javax.swing.JTextField txtNombreProv;
    private javax.swing.JTextField txtNroFaxProv;
    private javax.swing.JTextField txtNroTelefonoProv;
    private javax.swing.JTextArea txtObservacionesRep;
    private javax.swing.JTextField txtPaginaWebProv;
    // End of variables declaration//GEN-END:variables

}