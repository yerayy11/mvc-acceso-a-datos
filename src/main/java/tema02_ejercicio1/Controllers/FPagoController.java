package tema02_ejercicio1.Controllers;

import java.sql.*;
import java.util.ArrayList;
import tema02_ejercicio1.Models.FPago;

public class FPagoController {
    private Connection con;

    public FPagoController(Connection con){
        if(this.con == null) this.con = con;
    }
    
    public ArrayList<FPago> selectAll(){
        try (ResultSet datos = con.prepareStatement("SELECT * FROM FPAGO").executeQuery();) {
            ArrayList<FPago> fPagos = new ArrayList<>();
            while(datos.next()){
                fPagos.add(new FPago(datos.getString("CCODFPAGO"), datos.getString("CDESFPAGO"), datos.getInt("NRECIBOS")));
            }
            return fPagos;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    public FPago selectById(FPago f){
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
    
}
