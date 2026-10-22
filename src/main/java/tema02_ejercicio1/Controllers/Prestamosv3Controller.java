package tema02_ejercicio1.Controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import tema02_ejercicio1.Models.FPago;
import tema02_ejercicio1.Models.Prestamov3;
import tema02_ejercicio1.Models.Recibov1;

public class Prestamosv3Controller {

    private Connection con;
    private String sentencia;
 
    private int fila;
    private ArrayList<Prestamov3> lista;
    private Prestamov3 prestamo; 
    private Recibosv3Controller rc;
    private FPagoController fpc;
    
    
    public Prestamosv3Controller(Connection con) {
        if(this.con == null) this.con = con;
        rc = new Recibosv3Controller(con);
        fpc = new FPagoController(con);
    }
      //findAllLazy buscar prestamos sin atributos de tipo collecion (arraylist, list)
            public ArrayList<Prestamov3> findAllLazy() {

            lista = new ArrayList<>();

            sentencia = "SELECT * FROM PRESTAMOS";

            try (PreparedStatement ps = con.prepareStatement(sentencia);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    prestamo = new Prestamov3();

                    prestamo.setnNumPrest(rs.getInt(1));
                    prestamo.setdFecPrest(rs.getDate("dFecPrest").toLocalDate());
                    prestamo.setnImpPrest(rs.getInt(3));
                    prestamo.setnImpReci(rs.getInt(4));

                    FPago fpago = fpc.findById(new FPago(rs.getString(5),null,0));

                    prestamo.setcCodFPago(fpago);

                    lista.add(prestamo);
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

            return lista;
        }
        //findAllEager busca con todo incluyendo atributos tipo collection
            
            ///
            ///A TERMINAR
            ///
            ///
            ///
            ///
    public ArrayList<Prestamov3> findAllEager(){
        
        lista = new ArrayList<>();
        
        sentencia = "SELECT * FROM PRESTAMOS";
        
         try (PreparedStatement ps = con.prepareStatement(sentencia);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    prestamo = new Prestamov3();

                    prestamo.setnNumPrest(rs.getInt(1));
                    prestamo.setdFecPrest(rs.getDate("dFecPrest").toLocalDate());
                    prestamo.setnImpPrest(rs.getInt(3));
                    prestamo.setnImpReci(rs.getInt(4));

                    FPago fpago = fpc.findById(new FPago(rs.getString(5),null,0));

                    prestamo.setcCodFPago(fpago);
                    
                    ArrayList<Recibov1> recibos = rc.findByPrestamoId(new Prestamov3(prestamo.getnNumPrest(), null, 0,0, null, null));
                    
                    prestamo.setListaRecibos(recibos);
                    
                    lista.add(prestamo);
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        return lista;
    }
    
public Prestamov3 findById(Prestamov3 pId) {

    prestamo = null;

    sentencia = "SELECT * FROM PRESTAMOS WHERE nNumPrest = ?";

    try (PreparedStatement ps = con.prepareStatement(sentencia)) {

        ps.setInt(1, pId.getnNumPrest());

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                prestamo = new Prestamov3();

                prestamo.setnNumPrest(rs.getInt(1));
                prestamo.setdFecPrest(rs.getDate("dFecPrest").toLocalDate());
                prestamo.setnImpPrest(rs.getInt(3));
                prestamo.setnImpReci(rs.getInt(4));

                FPago fpago = new FPago();
                fpago.setcCodFPago(rs.getString(5));

                prestamo.setcCodFPago(fpago);
            }
        }

    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return prestamo;
}

    public int create(Prestamov3 prestamo) {

    sentencia = "INSERT INTO PRESTAMOS VALUES (?,?,?,?,?)";

    try (PreparedStatement ps = con.prepareStatement(sentencia)) {

        ps.setInt(1, prestamo.getnNumPrest());
        ps.setDate(2, Date.valueOf(prestamo.getdFecPrest()));
        ps.setInt(3, prestamo.getnImpPrest());
        ps.setInt(4, prestamo.getnImpReci());
        ps.setString(5, prestamo.getcCodFPago().getcCodFPago());

        return ps.executeUpdate();

    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return 0;
}
    
    public int update(Prestamov3 prestamo) {

    sentencia = """
                UPDATE PRESTAMOS
                SET dFecPrest = ?,
                    nImpPrest = ?,
                    nImpReci = ?,
                    cCodFPago = ?
                WHERE nNumPrest = ?
                """;

    try (PreparedStatement ps = con.prepareStatement(sentencia)) {

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
public int delete(Prestamov3 prestamo) {

    double pagado = calcularDeudaPrestamo(prestamo.getnNumPrest());
    
    if(pagado<prestamo.getnImpPrest()){
        System.out.println("el prestamo tiene una deuda que todavía le falta por pagar o que tiene recibos pagados");
        return 0;
    }
    
    int recibosBorrados = rc.deleteByPrestamoId(prestamo);
    
    if(recibosBorrados < 0) return 0;
    
    sentencia = "DELETE FROM PRESTAMOS WHERE nNumPrest = ?";

    try (PreparedStatement ps = con.prepareStatement(sentencia)) {

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

            try (CallableStatement cs = con.prepareCall("{CALL sp_GENERARRECIBOS(?, ?)}")) {
                // Parámetro de entrada
                cs.setInt(1, numPrestamo);
                
                // Parámetro de salida
                cs.registerOutParameter(2, Types.INTEGER);
                
                cs.execute();
                
                recibosGenerados = cs.getInt(2);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return recibosGenerados;

    }

    //Metodo para calcular la deuda de los prestamos. Usa la funcion fn_calcular_deuda_prestamo que recibe el numero de prestamo.

    public double calcularDeudaPrestamo(int numPrestamo) {

        double deuda = 0;

        try (CallableStatement cs = con.prepareCall("{? = call fn_calcular_deuda_prestamo(?)}")) {

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
