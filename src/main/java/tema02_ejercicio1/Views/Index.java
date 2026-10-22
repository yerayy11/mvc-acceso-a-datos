package tema02_ejercicio1.Views;

import javax.swing.JOptionPane;
import tema02_ejercicio1.Controllers.DBController;

public class Index extends javax.swing.JFrame {
    public static DBController contr;
    
    public Index() {
        contr = new DBController();
        if(contr.getCon() == null){
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            return;
        }
        initComponents();
        jToolBar1.setFloatable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        bFPago = new javax.swing.JButton();
        bPrestamo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bancos");

        jToolBar1.setRollover(true);

        bFPago.setText("Formas de Pago");
        bFPago.setFocusable(false);
        bFPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bFPago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bFPago.addActionListener(this::bFPagoActionPerformed);
        jToolBar1.add(bFPago);

        bPrestamo.setText("Prestamos");
        bPrestamo.setFocusable(false);
        bPrestamo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPrestamo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPrestamo.addActionListener(this::bPrestamoActionPerformed);
        jToolBar1.add(bPrestamo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(510, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bFPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFPagoActionPerformed
        new FPagoView(this, true).setVisible(true);
    }//GEN-LAST:event_bFPagoActionPerformed

    private void bPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrestamoActionPerformed
        new PrestamoView(this, true).setVisible(true);
    }//GEN-LAST:event_bPrestamoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bFPago;
    private javax.swing.JButton bPrestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
