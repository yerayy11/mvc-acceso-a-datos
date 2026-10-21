package tema02_ejercicio1.Controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import tema02_ejercicio1.Models.Prestamov3;

public class Prestamosv3Controller {

    private Connection con;

    public Prestamosv3Controller(Connection con) {
        if(this.con == null) this.con = con;    
    }
    
    public ArrayList<Prestamov3> findAllLazy(){
        
        return null;
    }
    
    public ArrayList<Prestamov3> findAllEager(){
        
        return null;
    }
    
    public Prestamov3 findById(Prestamov3 pId){
        
        return null;
    }
    
    public int create(Prestamov3 prestamo){
        
        return 0;
    }
    
    public int update(Prestamov3 prestamo){
        
        return 0;
    }
    
    public int delete(Prestamov3 prestamo){
        
        return 0;
    }
    
    //metodo para obtener los recibos generados. Usa procedimiento propio de la base de datos llamado sp_GENEREARRECIBOS el cual tiene dos parámetros: uno de entrada y otro de salida

    public int generarRecibos(int numPrestamo) {

        int recibosGenerados = -1;

        try {

            CallableStatement cs =
                    con.prepareCall("{CALL sp_GENERARRECIBOS(?, ?)}");

            // Parámetro de entrada
            cs.setInt(1, numPrestamo);

            // Parámetro de salida
            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            recibosGenerados = cs.getInt(2);

            cs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return recibosGenerados;

    }

    //Metodo para calcular la deuda de los prestamos. Usa la funcion fn_calcular_deuda_prestamo que recibe el numero de prestamo.

    public double calcularDeudaPrestamo(int numPrestamo) {

        double deuda = 0;

        try (CallableStatement cs = con.prepareCall("{? = CALL fn_calcular_deuda_prestamo(?)}")) {

            cs.registerOutParameter(1, Types.DECIMAL);

            //Parametro de entrada
            cs.setInt(2, numPrestamo); 

            cs.execute();

            deuda = cs.getDouble(1);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return deuda;
        }

    }
