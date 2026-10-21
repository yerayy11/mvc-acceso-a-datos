package tema02_ejercicio1.Controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import tema02_ejercicio1.Models.Prestamov3;

public class Prestamosv3Controller {

    private Connection con;
    private String sentencia;
    private PreparedStatement ps;
    private ResultSet rs;
    private int fila;
    public Prestamosv3Controller(Connection con) {
        if(this.con == null) this.con = con;    
    }
      //findAllLazy buscar prestamos sin atributos de tipo collecion (arraylist, list)
    public ArrayList<Prestamov3> findAllLazy(){
        
        
        return null;
    }
        //findAllEager busca con todo
    public ArrayList<Prestamov3> findAllEager(){
        
        return null;
    }
    
    public Prestamov3 findById(Prestamov3 pId){
        
        
        return null;
    }
    
    public int create(Prestamov3 prestamo){
        
        try {
            sentencia = "INSERT INTO PRESTAMOS VALUES (?,?,?,?,?)";
            
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, prestamo.getnNumPrest());
            ps.setDate(2, Date.valueOf(prestamo.getdFecPrest()));
            ps.setInt(3, prestamo.getnImpPrest());
            ps.setInt(4, prestamo.getnImpReci());
            ps.setString(5, prestamo.getcCodFPago().getcCodFPago());
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //el metodo ha fallado
        return 0;
    }
    
    public int update(Prestamov3 prestamo){
        
        sentencia = "UPDATE PRESTAMOS SET dFecPrest= ?, nImpPrest = ?, nImpReci = ?, cCodFPago = ? WHERE nNumPrest = ?";
        
        try {
            ps = con.prepareStatement(sentencia);
            ps.setDate(1, Date.valueOf(prestamo.getdFecPrest()));
            ps.setInt(2, prestamo.getnImpPrest());
            ps.setInt(3, prestamo.getnImpReci());
            ps.setString(4, prestamo.getcCodFPago().getcCodFPago());
            ps.setInt(5, prestamo.getnNumPrest());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return 0;
    }
    
    public int delete(Prestamov3 prestamo){
        
        sentencia = "DELETE FROM PRESTAMOS WHERE nNumPrest = ? ";
        try {
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, prestamo.getnNumPrest());
            return ps.executeUpdate();
        } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
        }
        
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
