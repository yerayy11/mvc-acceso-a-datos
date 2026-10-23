package tema02_ejercicio1.Views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tema02_ejercicio1.Controllers.FPagoController;
import tema02_ejercicio1.Controllers.Prestamosv3Controller;
import tema02_ejercicio1.Controllers.Recibosv1Controller;
import tema02_ejercicio1.Models.FPago;
import tema02_ejercicio1.Models.Prestamov3;
import tema02_ejercicio1.Models.Recibov1;

public class PrestamoView extends javax.swing.JDialog {
    
    private final Prestamosv3Controller PV3C;
    private final FPagoController FPC;
    private Prestamov3 p;
    private final DefaultTableModel DTM_PRES;
    private final DefaultTableModel DTM_REC;
    private FPago fp;
    private final Recibosv1Controller RV3C;
    private Recibov1 r;
    private int filaSeleccionada = 0;
    private final Index INDEX;
    private final DateTimeFormatter FORMATTER;
    
    public PrestamoView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        INDEX = (Index) parent;
        jToolBar1.setFloatable(false);
        FPC= new FPagoController(Index.con);
        RV3C= new Recibosv1Controller(Index.con);
        PV3C = new Prestamosv3Controller(Index.con);
        DTM_PRES = (DefaultTableModel) jtPrestamos.getModel();
        DTM_REC = (DefaultTableModel) jtRecibos.getModel();
        rellenarComboBox();
        actualizarTabla();
        this.jtPrestamos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila= jtPrestamos.getSelectedRow();
                if(fila != -1){
                    filaSeleccionada = fila;
                    txtNPrestamo.setText(jtPrestamos.getValueAt(fila, 0).toString());
                    txtFechaPrestamo.setText(jtPrestamos.getValueAt(fila, 1).toString());
                    txtImpPrestamo.setText(jtPrestamos.getValueAt(fila, 2).toString());
                    txtImpRecibido.setText(jtPrestamos.getValueAt(fila, 3).toString());
                    jcFormasPago.setSelectedItem(jtPrestamos.getValueAt(fila, 4));
                    actualizarTablaRecibos();
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
        bCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Prestamos");

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

        txtFechaPrestamo.setEditable(false);

        txtImpRecibido.setEditable(false);

        jcFormasPago.setModel(new javax.swing.DefaultComboBoxModel<>());

        bBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        bBuscar.setToolTipText("Buscar");
        bBuscar.addActionListener(this::bBuscarActionPerformed);

        bPagarRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pagarRecibo.png"))); // NOI18N
        bPagarRecibo.setText("Pagar Recibo");
        bPagarRecibo.addActionListener(this::bPagarReciboActionPerformed);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFechaPrestamo)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtNPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bBuscar))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtImpPrestamo, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtImpRecibido, javax.swing.GroupLayout.Alignment.TRAILING)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jcFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(114, 114, 114))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bCerrar)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
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
                        .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                actualizarTablaRecibos();
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
        int borrado= PV3C.delete(prestamo);
        if(borrado <=0){
            JOptionPane.showMessageDialog(this, "No se ha podido eliminar el registro seleccionado");
        }else{
            JOptionPane.showMessageDialog(this, "Prestamo borrado");
            actualizarTabla();
            DTM_REC.setRowCount(0);
        }
        
    }//GEN-LAST:event_bBajaActionPerformed

    private void bAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaActionPerformed
        p= new Prestamov3();
        switch (this.INDEX.comprobarCampo(txtNPrestamo.getText())) {
            case 0:
                p.setnNumPrest(Integer.parseInt(txtNPrestamo.getText()));
                break;
            case -1:
                JOptionPane.showMessageDialog(this, "El Número de préstamo solo acepta números.");
                return;
            case -2:
                JOptionPane.showMessageDialog(this, "El Número de préstamo tiene que ser mayor o igual que 0.");
                return;
            default:
                JOptionPane.showMessageDialog(this, "Error con el campo Número de préstamo.");
                return;
        }
        p.setdFecPrest(LocalDate.now());
        switch (this.INDEX.comprobarCampo(txtImpPrestamo.getText())) {
            case 0:
                p.setnImpPrest(Integer.parseInt(txtImpPrestamo.getText()));
                break;
            case -1:
                JOptionPane.showMessageDialog(this, "El Importe del préstamo solo acepta números.");
                return;
            case -2:
                JOptionPane.showMessageDialog(this, "El Importe del préstamo tiene que ser mayor o igual que 0.");
                return;
            default:
                JOptionPane.showMessageDialog(this, "Error con el campo Importe del préstamo.");
                return;
        }       
        p.setnImpReci(0);
        fp= new FPago();
        fp= (FPago) jcFormasPago.getSelectedItem();
        p.setcCodFPago(fp);
        int hecho= PV3C.create(p);
        
        if(hecho <=0){
            JOptionPane.showMessageDialog(this, "Registro no se ha podido añadir, comprueba sus campos");
        }else{
            JOptionPane.showMessageDialog(this, "Registro añadido");
            actualizarTabla();
        }
        
    }//GEN-LAST:event_bAltaActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        int seleccionadas= jtPrestamos.getSelectedRow();
        if(seleccionadas== -1){
            JOptionPane.showMessageDialog(this, "Selecciona un registro de la tabla");
            return; 
        }
        /*Al modificar no se podra cambiar ni la clave principal ni el importe recibido,
        lo demas se podra cambiar si todavia no se ha empezado a paggar nada
        */
        p= new Prestamov3();
        p.setnNumPrest(Integer.parseInt(jtPrestamos.getValueAt(jtPrestamos.getSelectedRow(), 0).toString()));
        p.setdFecPrest(LocalDate.now());
        p.setnImpPrest(Integer.parseInt(txtImpPrestamo.getText()));
        p.setnImpReci(Integer.parseInt(jtPrestamos.getValueAt(jtPrestamos.getSelectedRow(), 3).toString()));
        fp= new FPago();
        fp= (FPago) jcFormasPago.getSelectedItem();
        p.setcCodFPago(fp);
        int hecho= PV3C.update(p);
        if(hecho <=0){
            JOptionPane.showMessageDialog(this, "No se ha podido modificar el prestamo seleccionado");
        }else{
            JOptionPane.showMessageDialog(this, "Registro modificado");
            actualizarTabla();
            actualizarTablaRecibos();
        }
    }//GEN-LAST:event_bModificarActionPerformed

    private void bPagarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPagarReciboActionPerformed
        r= new Recibov1();
        int seleccionado= jtRecibos.getSelectedRow();
        if(seleccionado==-1){
            JOptionPane.showMessageDialog(this, "Selecciona un recibo");
            return;
        }
        int numRecibo= Integer.parseInt(jtRecibos.getValueAt(jtRecibos.getSelectedRow(), 1).toString());
        int numPrestamo = Integer.parseInt(jtRecibos.getValueAt(jtRecibos.getSelectedRow(), 0).toString());
        r.setnNumRecib(numRecibo);
        r.setnNumPrest(numPrestamo);
        int pagado= RV3C.pagarRecibo(r);
        if(pagado <=0){
            JOptionPane.showMessageDialog(this, "No se ha podido pagar el recibo");
            return; 
        }
        JOptionPane.showMessageDialog(this, "Recibo pagado");
        actualizarTabla();
        actualizarTablaRecibos();

    }//GEN-LAST:event_bPagarReciboActionPerformed

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
    DTM_PRES.setRowCount(0);
    ArrayList<Prestamov3> lista;
    lista= PV3C.findAllLazy();
        for (Prestamov3 prestamo : lista) {
            DTM_PRES.addRow(new Object[]{
            prestamo.getnNumPrest(),
            FORMATTER.format(prestamo.getdFecPrest()),
            prestamo.getnImpPrest(),
            prestamo.getnImpReci(),
            prestamo.getcCodFPago()    
            });
            
        }
    }

    private void rellenarComboBox() {
        ArrayList<FPago> lista= FPC.findAll();
        for (FPago fpago : lista) {
            jcFormasPago.addItem(fpago);
        }
    }
    
    private void actualizarTablaRecibos() {
        int fila = jtPrestamos.getSelectedRow();
        if(fila != -1) filaSeleccionada = fila;
        p=new Prestamov3();
        p.setnNumPrest((int) jtPrestamos.getValueAt(filaSeleccionada, 0));
        ArrayList<Recibov1> lista= RV3C.findByPrestamoId(p);
        DTM_REC.setRowCount(0);
        for (Recibov1 rec : lista) {
            String fechaPago;
            if(rec.getdFecPago() == null) fechaPago = null;
            else fechaPago = FORMATTER.format(rec.getdFecPago());
            DTM_REC.addRow(new Object[]{
            rec.getnNumPrest(),
            rec.getnNumRecib(),
            FORMATTER.format(rec.getdFecRec()),
            rec.getnImpRec(),
            fechaPago
            });
        }
    }
    
}
