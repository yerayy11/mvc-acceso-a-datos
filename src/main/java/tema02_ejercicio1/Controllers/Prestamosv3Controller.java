package tema02_ejercicio1.Controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import tema02_ejercicio1.Models.FPago;
import tema02_ejercicio1.Models.Prestamov3;
import tema02_ejercicio1.Models.Recibov1;

public class Prestamosv3Controller {

    private Connection con;
    private String sentencia;
 
    private ArrayList<Prestamov3> lista;
    private Prestamov3 prestamo; 
    private final Recibosv1Controller RC;
    private final FPagoController FPC;
    
    
    public Prestamosv3Controller(Connection con) {
        if(this.con == null) this.con = con;
        RC = new Recibosv1Controller(con);
        FPC = new FPagoController(con);
    }
      //findAllLazy buscar prestamos sin atributos de tipo collecion (arraylist, list)
            public ArrayList<Prestamov3> findAllLazy() {

            lista = new ArrayList<>();

            sentencia = "SELECT * FROM PRESTAMOS P INNER JOIN FPAGO F ON F.cCodFPago=P.cCodFPago ORDER BY NNUMPREST";

            try (PreparedStatement ps = con.prepareStatement(sentencia);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    prestamo = new Prestamov3();

                    prestamo.setnNumPrest(rs.getInt("nNumPrest"));
                    prestamo.setdFecPrest(rs.getDate("dFecPrest").toLocalDate());
                    prestamo.setnImpPrest(rs.getInt("nImpPrest"));
                    prestamo.setnImpReci(rs.getInt("nImpReci"));

                    FPago fpago = new FPago();
                    fpago.setcCodFPago(rs.getString("cCodFPago"));
                    fpago.setcDesFPago(rs.getString("cDesFPago"));
                    fpago.setnRecibos(rs.getInt("nRecibos"));
                    
                    prestamo.setcCodFPago(fpago);

                    lista.add(prestamo);
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

            return lista;
        }
            
        //findAllEager busca con todo incluyendo atributos tipo collection
         
    public ArrayList<Prestamov3> findAllEager(){
        
        lista = new ArrayList<>();
        
        sentencia = "SELECT * FROM PRESTAMOS P INNER JOIN FPAGO F ON F.cCodFPago=P.cCodFPago ORDER BY NNUMPREST";
        
         try (PreparedStatement ps = con.prepareStatement(sentencia);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    prestamo = new Prestamov3();

                    prestamo.setnNumPrest(rs.getInt("nNumPrest"));
                    prestamo.setdFecPrest(rs.getDate("dFecPrest").toLocalDate());
                    prestamo.setnImpPrest(rs.getInt("nImpPrest"));
                    prestamo.setnImpReci(rs.getInt("nImpReci"));

                    FPago fpago = new FPago();
                    fpago.setcCodFPago(rs.getString("cCodFPago"));
                    fpago.setcDesFPago(rs.getString("cDesFPago"));
                    fpago.setnRecibos(rs.getInt("nRecibos"));
                    
                    prestamo.setcCodFPago(fpago);
                    
                    ArrayList<Recibov1> recibos = RC.findByPrestamoId(new Prestamov3(prestamo.getnNumPrest(), null, 0,0, null, null));
                    
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

        int filasInsertadas = 0;

        sentencia = "INSERT INTO PRESTAMOS VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sentencia)) {

            ps.setInt(1, prestamo.getnNumPrest());
            ps.setDate(2, Date.valueOf(prestamo.getdFecPrest()));
            ps.setInt(3, prestamo.getnImpPrest());
            ps.setInt(4, prestamo.getnImpReci());
            ps.setString(5, prestamo.getcCodFPago().getcCodFPago());

            filasInsertadas = ps.executeUpdate();

            RC.generarRecibos(prestamo);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return filasInsertadas;
    }
    
    public int update(Prestamov3 prestamo) {
        
        int filasActualizadas = 0;
        
        if(tienePrestamosPagados(prestamo))return 0;

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

            filasActualizadas = ps.executeUpdate();
            
            //al modificar el préstamo se borran sus recibos y se vuelven a generar con los nuevos datos
            RC.deleteByPrestamoId(prestamo);

            RC.generarRecibos(prestamo);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return filasActualizadas;
    }
    
    public int delete(Prestamov3 prestamo) {

        if(tienePrestamosPagados(prestamo))return 0;

        //Los recibos del préstamo no se borran a mano: la clave foránea de RECIBOS tiene ON DELETE CASCADE
        //y la base de datos los elimina sola al borrar el préstamo.
        sentencia = "DELETE FROM PRESTAMOS WHERE nNumPrest = ?";

        try (PreparedStatement ps = con.prepareStatement(sentencia)) {

            ps.setInt(1, prestamo.getnNumPrest());

            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return 0;
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
    
   
public int actualizarImporteRecibido(Prestamov3 prestamo){

    int filasActualizadas = 0;

    //COALESCE devuelve el primer valor que no sea nulo. si todos los valores son nulos entonces devuelve null
    String sql = "UPDATE PRESTAMOS "
               + "SET nImpReci = (SELECT COALESCE(SUM(nImpRec), 0) "
               + "                FROM RECIBOS "
               + "                WHERE nNumPrest = ? AND dFecPago IS NOT NULL) "
               + "WHERE nNumPrest = ?";

    try(PreparedStatement ps = con.prepareStatement(sql)){

        ps.setInt(1, prestamo.getnNumPrest());
        ps.setInt(2, prestamo.getnNumPrest());

        filasActualizadas = ps.executeUpdate();

    } catch(SQLException ex){
        System.err.println(ex.getMessage());
    }

    return filasActualizadas;
}

    private boolean tienePrestamosPagados(Prestamov3 prestamo) {
        
        
        String sql = "SELECT * FROM PRESTAMOS WHERE nNumPrest = ? AND nImpReci>0";
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, prestamo.getnNumPrest());
            
            try(ResultSet rs = ps.executeQuery()){
                
                //el prestamo tiene el impuesto de recibo mayor a 0
                if(rs.next()){
                    return true;
                }
                
            }catch(SQLException ex){
        System.err.println(ex.getMessage());
    }
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
    }
        
        return false;
    }


}
