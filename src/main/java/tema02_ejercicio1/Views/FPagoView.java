package tema02_ejercicio1.Views;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tema02_ejercicio1.Models.FPago;
import tema02_ejercicio1.Controllers.FPagoController;
import javax.swing.ImageIcon;


public class FPagoView extends javax.swing.JDialog {
    
    private final ListSelectionModel LSM;
    private final FPagoController CONTR;
    
    public FPagoView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
        this.CONTR = new FPagoController(Index.contr.getCon());
        LSM = jTable1.getSelectionModel();
        rellenarTabla();
        LSM.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        LSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indice = LSM.getSelectedIndices();
                if(indice.length == 0) return;
                tCodigo.setText(jTable1.getValueAt(indice[0], 0).toString());
                tDescripcion.setText(jTable1.getValueAt(indice[0], 1).toString());
                tNRecivos.setText(jTable1.getValueAt(indice[0], 2).toString());
            }
        });
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        bAlta = new javax.swing.JButton();
        bBaja = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tCodigo = new javax.swing.JTextField();
        tDescripcion = new javax.swing.JTextField();
        bBuscar = new javax.swing.JButton();
        tNRecivos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Número de Recivos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jToolBar1.setRollover(true);

        bAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/alta.png"))); // NOI18N
        bAlta.setToolTipText("Alta");
        bAlta.setFocusable(false);
        bAlta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bAlta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bAlta.addActionListener(this::bAltaActionPerformed);
        jToolBar1.add(bAlta);

        bBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        bBaja.setToolTipText("Baja");
        bBaja.setFocusable(false);
        bBaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bBaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bBaja.addActionListener(this::bBajaActionPerformed);
        jToolBar1.add(bBaja);

        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar.png"))); // NOI18N
        bModificar.setToolTipText("Modificar");
        bModificar.setFocusable(false);
        bModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bModificar.addActionListener(this::bModificarActionPerformed);
        jToolBar1.add(bModificar);

        jLabel1.setText("Código");

        jLabel2.setText("Descripción");

        jLabel3.setText("N Recivos");

        bBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        bBuscar.setToolTipText("Buscar");
        bBuscar.addActionListener(this::bBuscarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(tNRecivos)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bBuscar)))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bBuscar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tNRecivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaActionPerformed
        try{
            if(CONTR.create(new FPago(tCodigo.getText().trim(), tDescripcion.getText().trim(), Integer.parseInt(tNRecivos.getText().trim()))) == 0){
                JOptionPane.showMessageDialog(this, "Error al insertar la nueva Forma de Pago");
                return;
            }
            rellenarTabla();
            JOptionPane.showMessageDialog(this, "Insertado");
        }catch (NumberFormatException ex){JOptionPane.showMessageDialog(this, "Error en el formato del número de recibos");}
    }//GEN-LAST:event_bAltaActionPerformed

    private void bBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBajaActionPerformed
        if(!isSeleccionado())return;
        if(JOptionPane.showConfirmDialog(this, "Borrar la Forma de Pago seleccionada?") != JOptionPane.OK_OPTION)return;
        if(CONTR.delete(new FPago(jTable1.getValueAt(LSM.getSelectedIndices()[0], 0).toString(), null, 0)) == 0){
                JOptionPane.showMessageDialog(this, "Error al borrar la Forma de Pago");            
        }
        rellenarTabla();
        JOptionPane.showMessageDialog(this, "Borrado");
    }//GEN-LAST:event_bBajaActionPerformed

    private boolean isSeleccionado() throws HeadlessException {
        if (LSM.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ninguna forma seleccionada");
            return false;
        }
        return true;
    }

    private void bBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarActionPerformed
        FPago encontrado;
        String codigo;
        if((encontrado = CONTR.findById(new FPago( (codigo = tCodigo.getText().trim()), null, 0))) == null){
            JOptionPane.showMessageDialog(this, "Error al buscar la forma de pago con código: "+ codigo);
            return;
        }
        tDescripcion.setText(encontrado.getcDesFPago());
        tNRecivos.setText(String.valueOf(encontrado.getnRecivos()));
        JOptionPane.showMessageDialog(this, "Encontrado");
    }//GEN-LAST:event_bBuscarActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        if(!isSeleccionado())return;
        try{
            if(CONTR.update(new FPago(jTable1.getValueAt(LSM.getSelectedIndices()[0], 0).toString(),
                                      tDescripcion.getText().trim(), 
                                      Integer.parseInt(tNRecivos.getText().trim()))) == 0 )
            {
                JOptionPane.showMessageDialog(this, "Error al modificar la Forma de Pago");
                return;
            }
            rellenarTabla();
            JOptionPane.showMessageDialog(this, "Modificado");
        }catch(NumberFormatException ex){JOptionPane.showMessageDialog(this, "Error en el formato del número de recibos");}
    }//GEN-LAST:event_bModificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlta;
    private javax.swing.JButton bBaja;
    private javax.swing.JButton bBuscar;
    private javax.swing.JButton bModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tDescripcion;
    private javax.swing.JTextField tNRecivos;
    // End of variables declaration//GEN-END:variables

    private void rellenarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTable1.getModel();
        dtm.setRowCount(0);
        ArrayList<FPago> fPagos = CONTR.findAll();
        if(fPagos == null){
            JOptionPane.showMessageDialog(this, "Error al cargar los datos");
            return;
        }
        for (FPago fp : fPagos) {
            dtm.addRow(new Object[]{fp.getcCodFPago(),fp.getcDesFPago(),fp.getnRecivos()});
        }
    }
}
