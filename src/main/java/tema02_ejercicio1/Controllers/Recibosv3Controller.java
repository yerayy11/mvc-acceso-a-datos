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

public class Recibosv3Controller {
    
    private Connection con;
    private String sentencia;
    private PreparedStatement ps;
    private ResultSet rs;
    private int fila;
    
    public Recibosv3Controller(Connection con) {
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

    public ArrayList<Recibov1> findAllById(Prestamov3 prestamo){
        ArrayList<Recibov1> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM RECIBOS WHERE NNUMPREST = ?";
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            
            //Pasamos el id del prestamo al primer y único parámetro
            ps.setInt(1, prestamo.getnNumPrest());
            
            try(ResultSet rs = ps.executeQuery()){
                
                //Recorremos el resultado de la consulta
                while(rs.next()){
                    
                    //Añadimos cada Revibo a la lista
                    lista.add(new Recibov1(rs.getInt("NNUMPREST"),
                                           rs.getInt("NNUMRECIB"), 
                                           rs.getDate("DFECREC").toLocalDate(), 
                                           rs.getInt("NIMPRERC"),
                                           rs.getDate("DFECPAGO").toLocalDate()));
                    
                }                
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error al buscar los recibos: " + ex.getMessage());
        }
        
        return lista;
    }
    
}
