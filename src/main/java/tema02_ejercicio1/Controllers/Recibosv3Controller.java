package tema02_ejercicio1.Controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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

    

    
}
