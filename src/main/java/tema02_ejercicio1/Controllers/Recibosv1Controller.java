package tema02_ejercicio1.Controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import tema02_ejercicio1.Models.*;

public class Recibosv1Controller {
    
    private Connection con;
    private  Prestamosv3Controller pv3c;
    
    public Recibosv1Controller(Connection con) {
        if(this.con == null) this.con = con;    
    }
    
    public int generarRecibos(Prestamov3 prestamo) {
        int recibosGenerados = -1;

        String sql = "{call sp_GENERARRECIBOS(?, ?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {

            // pasamos el xnNumPrest de primer parámetro
            cs.setInt(1, prestamo.getnNumPrest());

            // recogemos un int
            cs.registerOutParameter(2, Types.INTEGER);

            // Ejecutamos el procedimiento
            cs.execute();

            // Obtener el resultado del parámetro de salida (OUT)
            recibosGenerados = cs.getInt(2);

        } catch (SQLException ex) {
            System.err.println("Error al generar recibos: " + ex.getMessage());
        }
        return recibosGenerados;
    }

    public ArrayList<Recibov1> findByPrestamoId(Prestamov3 prestamo){
        ArrayList<Recibov1> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM RECIBOS WHERE NNUMPREST = ?";
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            
            //Pasamos el id del prestamo al primer y único parámetro
            ps.setInt(1, prestamo.getnNumPrest());
            
            try(ResultSet rs = ps.executeQuery()){
                
                //Recorremos el resultado de la consulta
                while(rs.next()){
                    
                    LocalDate fechaPago;
                    if(rs.getDate("DFECPAGO") == null){
                        fechaPago = null;
                    }else{
                        fechaPago = rs.getDate("DFECPAGO").toLocalDate();
                    }
                    
                    //Añadimos cada Revibo a la lista
                    lista.add(new Recibov1(rs.getInt("NNUMPREST"),
                                           rs.getInt("NNUMRECIB"), 
                                           rs.getDate("DFECREC").toLocalDate(), 
                                           rs.getInt("NIMPREC"),
                                           fechaPago));
                    
                }                
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error al buscar los recibos: " + ex.getMessage());
        }
        
        return lista;
    }
    
    public int pagarRecibo(Recibov1 recibo){
    int recibosPagados = -1;
    
    String sql = "UPDATE RECIBOS SET DFECPAGO = CURDATE() WHERE NNUMRECIB = ? AND NNUMPREST = ? AND DFECPAGO IS NULL";
    
    try(PreparedStatement ps = con.prepareStatement(sql)){
        
        ps.setInt(1, recibo.getnNumRecib());
        ps.setInt(2, recibo.getnNumPrest());
        
        recibosPagados = ps.executeUpdate();
        
    } catch (SQLException ex) {
        System.err.println("Error al pagar el recibo: " + ex.getMessage());
    }
    
    //Si se ha pagado el recibo, actualizamos el importe recibido del préstamo
    if(recibosPagados > 0){
        pv3c = new Prestamosv3Controller(con);
        pv3c.actualizarImporteRecibido(new Prestamov3(recibo.getnNumPrest(), null, 0, 0, null, null));
    }
    
    return recibosPagados;
}
    
    public int deleteByPrestamoId(Prestamov3 prestamo){
        int filasBorradas = -1;
        
        String sql = "DELETE FROM RECIBOS WHERE NNUMPREST = ?";
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            
            //Añadimos el campo id del recibo correspondiente
            ps.setInt(1, prestamo.getnNumPrest());
            
            //recogemos el número de filas afectadas
            filasBorradas = ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Error al borrar los recibos: " + ex.getMessage());
        }
        
        return filasBorradas;
    }
    
}
