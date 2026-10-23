package tema02_ejercicio1.Views;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Index extends javax.swing.JFrame {
    public static Connection con;
    
    public Index(Connection con) {
        Index.con = con;
        initComponents();
        jToolBar1.setFloatable(false);
        setLocationRelativeTo(null);
        rellenarJComboBox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        bFPago = new javax.swing.JButton();
        bPrestamo = new javax.swing.JButton();
        bCerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtConexion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcRoles = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMiembros = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("App Bancos");

        jToolBar1.setRollover(true);

        bFPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/formasPago.png"))); // NOI18N
        bFPago.setText("Formas de Pago");
        bFPago.setFocusable(false);
        bFPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bFPago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bFPago.addActionListener(this::bFPagoActionPerformed);
        jToolBar1.add(bFPago);

        bPrestamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/prestamos.png"))); // NOI18N
        bPrestamo.setText("Prestamos");
        bPrestamo.setFocusable(false);
        bPrestamo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPrestamo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPrestamo.addActionListener(this::bPrestamoActionPerformed);
        jToolBar1.add(bPrestamo);

        bCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/puerta.png"))); // NOI18N
        bCerrar.addActionListener(this::bCerrarActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Estado conexión MySQL:");

        txtConexion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtConexion.setText("Conexión establecida correctamente ");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/tickConexion.png"))); // NOI18N

        jcRoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcRoles.addItemListener(this::jcRolesItemStateChanged);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Roles");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/miembros.png"))); // NOI18N
        jLabel5.setText("Miembros");

        txtAreaMiembros.setEditable(false);
        txtAreaMiembros.setColumns(20);
        txtAreaMiembros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaMiembros.setRows(5);
        jScrollPane1.setViewportView(txtAreaMiembros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtConexion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCerrar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(133, 133, 133))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jcRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtConexion)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bCerrar)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bFPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFPagoActionPerformed
        FPagoView ventanaFPago=   new FPagoView(this, true);
        ventanaFPago.setLocationRelativeTo(this);
        ventanaFPago.setVisible(true);
        
    }//GEN-LAST:event_bFPagoActionPerformed

    private void bPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrestamoActionPerformed
        PrestamoView ventanaPrestamo = new PrestamoView(this, true);
        ventanaPrestamo.setLocationRelativeTo(this);
        ventanaPrestamo.setVisible(true);
    }//GEN-LAST:event_bPrestamoActionPerformed

    private void bCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCerrarActionPerformed
       
        try {
            con.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ha habido un problema al cerrar la conexión");
            System.err.println("Error: "+ex.getMessage());
        }
        this.dispose();
    }//GEN-LAST:event_bCerrarActionPerformed

    private void jcRolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcRolesItemStateChanged
        
        //if para verificar que hay un objeto seleccionado
        if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
        rellenarTxtArea();
    }
    }//GEN-LAST:event_jcRolesItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCerrar;
    private javax.swing.JButton bFPago;
    private javax.swing.JButton bPrestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox<String> jcRoles;
    private javax.swing.JTextArea txtAreaMiembros;
    private javax.swing.JLabel txtConexion;
    // End of variables declaration//GEN-END:variables

    private void rellenarJComboBox() {
        this.jcRoles.removeAllItems();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Todos");
        roles.add("Jefazo");
        roles.add("Informáticos");
        roles.add("Técnicos");
        
        for (String rol : roles) {
            this.jcRoles.addItem(rol);
        }
        
    }
    
    public int comprobarCampo(String txt) {
        try {
            int comprobacion = Integer.parseInt(txt);
            if (comprobacion < 0) {
                return -2;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return 0;
    }

    private void rellenarTxtArea() {
        
        this.txtAreaMiembros.setText("");
        String rol = (String) this.jcRoles.getSelectedItem();
       
        if(rol == null){
            return;
        }
        switch(rol){
            
            case "Jefazo":
                     txtAreaMiembros.append("- Sergio Alcaide Ortiz\n");
                break;
                
            case "Informáticos":
                     txtAreaMiembros.append("- Miguel Ángel López Molina\n");
                     txtAreaMiembros.append("- Ángel López Franco \n");
                   

                break;
                
            case "Técnicos":
                     txtAreaMiembros.append("- Luis Hueltes Ortega\n");
                     txtAreaMiembros.append("- Francisco Javier Castellano Soto\n");
                     txtAreaMiembros.append("- Yeray Martínez Mochón\n");
                    txtAreaMiembros.append("- Marcos Carrión López\n");

                break;
                
            case "Todos":
                     txtAreaMiembros.append("- Sergio Alcaide Ortiz\n");
                     txtAreaMiembros.append("- Miguel Ángel López Molina\n");
                     txtAreaMiembros.append("- Ángel López Franco \n");
                     txtAreaMiembros.append("- Luis Hueltes Ortega\n");
                     txtAreaMiembros.append("- Francisco Javier Castellano Soto\n");
                     txtAreaMiembros.append("- Yeray Martínez Mochón\n");
                    txtAreaMiembros.append("- Marcos Carrión López\n");
                break;
                
                
                
        }
            
    }
}
