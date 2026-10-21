package tema02_ejercicio1.Controllers;

import java.sql.*;
import java.util.ArrayList;
import tema02_ejercicio1.Models.FPago;

public class FPagoController {
    private Connection con;

    public FPagoController(Connection con){
        if(this.con == null) this.con = con;
    }
    
    public FPago findById(FPago f){
        try(PreparedStatement sel = con.prepareStatement("SELECT * FROM FPAGO WHERE CCODFPAGO = ?")){
            sel.setString(1, f.getcCodFPago());
            try (ResultSet rFPago = sel.executeQuery()) {
                if(!rFPago.next()) return null;
                return new FPago(f.getcCodFPago(), rFPago.getString("CDESFPAGO"), rFPago.getInt("NRECIBOS"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    public int insert(FPago f){
        if(f == null)return 0;
        try (PreparedStatement insert = con.prepareStatement("INSERT INTO FPAGO(CCODFPAGO,CDESFPAGO,NRECIBOS) VALUES(?,?,?)");) {
            insert.setString(1, f.getcCodFPago());
            insert.setString(2, f.getcDesFPago());
            insert.setInt(3, f.getnRecivos());
            return insert.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int update(FPago f){
        if(f == null)return 0;
        try (PreparedStatement update = con.prepareStatement("UPDATE FPAGO SET CDESFPAGO = ?, NRECIBOS = ? WHERE CCODFPAGO LIKE ?");) {
            update.setString(1, f.getcDesFPago());
            update.setInt(2, f.getnRecivos());
            update.setString(3, f.getcCodFPago());
            return update.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int delete(FPago f){
        if(f == null)return 0;
        try (PreparedStatement delete = con.prepareStatement("DELETE FROM FPAGO WHERE CCODFPAGO = ?");) {
            delete.setString(1, f.getcCodFPago());
            return delete.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public ArrayList<FPago> findAllLazy(){
        String sql = "SELECT * FROM FPAGO ORDER BY CCODFPAGO";
        try (ResultSet datos = con.prepareStatement(sql).executeQuery();) {
            ArrayList<FPago> fPagos = new ArrayList<>();
            while(datos.next()){
                fPagos.add(new FPago(
                    datos.getString("CCODFPAGO"), 
                    datos.getString("CDESFPAGO"), 
                    datos.getInt("NRECIBOS"))
                );
            }
            return fPagos;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    public ArrayList<FPago> findAllEagerWithJoin() {
    String sql = "SELECT f.* " +
                 "FROM FPAGO f " +
                 "LEFT JOIN PRESTAMOS p ON f.CCODFPAGO = p.CCODFPAGO " +
                 "ORDER BY f.CCODFPAGO";

    ArrayList<FPago> fPagos = new ArrayList<>();
    FPago actual = null;

    try (PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet datos = stmt.executeQuery()) {

        while (datos.next()) {
            String codFPago = datos.getString("CCODFPAGO");

            // Si cambiamos de forma de pago, creamos la nueva instancia
            if (actual == null || !actual.getcCodFPago().equals(codFPago)) {
                actual = new FPago(
                    codFPago,
                    datos.getString("CDESFPAGO"),
                    datos.getInt("NRECIBOS")
                );
                actual.setPrestamos(new ArrayList<>());
                fPagos.add(actual);
            }

            // Si la fila del JOIN trae un préstamo asociado, lo añadimos
            int numPrest = datos.getInt("NNUMPREST");
            if (!datos.wasNull()) {
                Prestamo p = new Prestamo();
                p.setnNumPrest(numPrest);
                p.setdFecPrest(datos.getDate("DFECPREST"));
                p.setnImpPrest(datos.getBigDecimal("NIMPPREST"));
                
                actual.getPrestamos().add(p);
            }
        }
        return fPagos;

    } catch (SQLException ex) {
        System.err.println("Error en findAllEagerWithJoin: " + ex.getMessage());
        return null;
    }
}
    
}
