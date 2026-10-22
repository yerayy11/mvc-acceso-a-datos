package tema02_ejercicio1.Views;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tema02_ejercicio1.Controllers.FPagoController;
import tema02_ejercicio1.Controllers.Prestamosv3Controller;
import tema02_ejercicio1.Models.FPago;
import tema02_ejercicio1.Models.Prestamov3;

public class PrestamoView extends javax.swing.JDialog {
    
    private final Prestamosv3Controller CONTR;
    private Prestamosv3Controller pv3c;
    private FPagoController fpc;
    private Prestamov3 p;
    DefaultTableModel dtm;
    FPago fp;
    //private Recibosv3Controller rv3c;
    
    public PrestamoView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jToolBar1.setFloatable(false);
        fpc= new FPagoController(Index.contr.getCon());
        rellenarComboBox();
        this.CONTR = new Prestamosv3Controller(Index.contr.getCon());
        actualizarTabla();
        this.jtPrestamos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila= jtPrestamos.getSelectedRow();
                if(fila != -1){
                txtNPrestamo.setText(jtPrestamos.getValueAt(fila, 0).toString());
                txtFechaPrestamo.setText(jtPrestamos.getValueAt(fila, 1).toString());
                txtImpPrestamo.setText(jtPrestamos.getValueAt(fila, 2).toString());
                txtImpRecibido.setText(jtPrestamos.getValueAt(fila, 3).toString());
                jcFormasPago.setSelectedItem(jtPrestamos.getValueAt(fila, 4));
                p=new Prestamov3();
                p.setnNumPrest((int) jtPrestamos.getValueAt(fila, 0));
                
                }
                
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        bAlta = new javax.swing.JButton();
        bBaja = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPrestamos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtRecibos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNPrestamo = new javax.swing.JTextField();
        txtFechaPrestamo = new javax.swing.JTextField();
        txtImpPrestamo = new javax.swing.JTextField();
        txtImpRecibido = new javax.swing.JTextField();
        jcFormasPago = new javax.swing.JComboBox<>();
        bBuscar = new javax.swing.JButton();
        bPagarRecibo = new javax.swing.JButton();
        bRellenar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jtPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número de préstamo", "Fecha de préstamo", "Importe del préstamo", "Importe recibido", "Forma de Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPrestamos.setColumnSelectionAllowed(true);
        jtPrestamos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtPrestamos);
        jtPrestamos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jtRecibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número del préstamo", "Número de recibo", "Fecha del recibo", "Importe", "Fecha de pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtRecibos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtRecibos);

        jLabel1.setText("Número de Préstamo");

        jLabel2.setText("Fecha del préstamo");

        jLabel3.setText("Importe del préstamo");

        jLabel4.setText("Importe recibido");

        jLabel5.setText("Forma de Pago");

        jcFormasPago.setModel(new javax.swing.DefaultComboBoxModel<>());

        bBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        bBuscar.setToolTipText("Buscar");
        bBuscar.addActionListener(this::bBuscarActionPerformed);

        bPagarRecibo.setText("Pagar Recibo");
        bPagarRecibo.addActionListener(this::bPagarReciboActionPerformed);

        bRellenar.setText("Regresar");
        bRellenar.addActionListener(this::bRellenarActionPerformed);

        bCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cerrar.png"))); // NOI18N
        bCerrar.addActionListener(this::bCerrarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bPagarRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(44, 44, 44)
                                .addComponent(txtNPrestamo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(txtImpPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtImpRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(bBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bRellenar)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addComponent(bRellenar)
                            .addComponent(bBuscar))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtImpPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtImpRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jcFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addComponent(bPagarRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarActionPerformed

        String codigo= txtNPrestamo.getText();
        if(codigo.isBlank()){
        JOptionPane.showMessageDialog(this, "Escribe un codigo para buscar");
        return;
        }
        for(int i=0; i<jtPrestamos.getRowCount(); i++){
        String valor= jtPrestamos.getValueAt(i, 0).toString();
        if(valor.equalsIgnoreCase(codigo)){
        jtPrestamos.setRowSelectionInterval(i, i);
        jtPrestamos.scrollRectToVisible(jtPrestamos.getCellRect(i, 0, true));
        return;
        }
        }
        JOptionPane.showMessageDialog(this, "Codigo no encontrado");
        
        
    }//GEN-LAST:event_bBuscarActionPerformed

    private void bBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBajaActionPerformed
        Prestamov3 prestamo = new Prestamov3();
        if(jtPrestamos.getSelectedRow() == -1){
        JOptionPane.showMessageDialog(null, "Selecciona un registro de la tabla");
        return;    
        }
        prestamo.setnNumPrest((int) jtPrestamos.getValueAt(jtPrestamos.getSelectedRow(), 0));
        int eleccion=JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres borrar este prestamo?");
        if(eleccion!=JOptionPane.OK_OPTION){
        JOptionPane.showMessageDialog(this, "Operación cancelada");
        return;       
        }
        int borrado= pv3c.delete(prestamo);
        if(borrado <=0){
        JOptionPane.showMessageDialog(this, "No se ha podido eliminar el registro seleccionado");
        }
        
    }//GEN-LAST:event_bBajaActionPerformed

    private void bAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaActionPerformed
        
    }//GEN-LAST:event_bAltaActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        p= new Prestamov3();
        p.setnNumPrest(Integer.parseInt(txtNPrestamo.getText()));
        p.setdFecPrest(LocalDate.parse(txtFechaPrestamo.getText()));
        p.setnImpPrest(Integer.parseInt(txtImpPrestamo.getText()));
        p.setnImpReci(Integer.parseInt(txtImpRecibido.getText()));
        fp= new FPago();
        fp= (FPago) jcFormasPago.getSelectedItem();
        p.setcCodFPago(fp);
        int hecho= pv3c.update(p);
        if(hecho <=0){
        JOptionPane.showMessageDialog(this, "No se ha conseguido modificar el prestamo seleccionado");
        return;   
        }else{
        JOptionPane.showMessageDialog(this, "Registro modificado");
        actualizarTabla();
        }
    }//GEN-LAST:event_bModificarActionPerformed

    private void bPagarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPagarReciboActionPerformed
       
    }//GEN-LAST:event_bPagarReciboActionPerformed

    private void bRellenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRellenarActionPerformed
    actualizarTabla();
    }//GEN-LAST:event_bRellenarActionPerformed

    private void bCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCerrarActionPerformed
                this.dispose();
    }//GEN-LAST:event_bCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlta;
    private javax.swing.JButton bBaja;
    private javax.swing.JButton bBuscar;
    private javax.swing.JButton bCerrar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bPagarRecibo;
    private javax.swing.JButton bRellenar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox<FPago> jcFormasPago;
    private javax.swing.JTable jtPrestamos;
    private javax.swing.JTable jtRecibos;
    private javax.swing.JTextField txtFechaPrestamo;
    private javax.swing.JTextField txtImpPrestamo;
    private javax.swing.JTextField txtImpRecibido;
    private javax.swing.JTextField txtNPrestamo;
    // End of variables declaration//GEN-END:variables

    private void actualizarTabla() {
    dtm= new DefaultTableModel();
    dtm=(DefaultTableModel) this.jtPrestamos.getModel();
    dtm.setRowCount(0);
    pv3c= new Prestamosv3Controller(Index.contr.getCon());
    ArrayList<Prestamov3> lista= new ArrayList<>();
    lista= pv3c.findAllLazy();
        for (Prestamov3 prestamo : lista) {
            dtm.addRow(new Object[]{
            prestamo.getnNumPrest(),
            prestamo.getdFecPrest(),
            prestamo.getnImpPrest(),
            prestamo.getnImpReci(),
            prestamo.getcCodFPago()    
            });
            
        }
    }

    private void rellenarComboBox() {
        ArrayList<FPago> lista= fpc.findAll();
        for (FPago fp : lista) {
            jcFormasPago.addItem(fp);
        }
    }
    
    
}
