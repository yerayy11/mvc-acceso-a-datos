package tema02_ejercicio1.Controllers;

import java.sql.*;
import java.util.ArrayList;
import tema02_ejercicio1.Models.FPago;

public class FPagoController {
    private Connection con;
    private String sentencia;
    private PreparedStatement ps;
    private ResultSet rs;

    public FPagoController(Connection con){
        if(this.con == null) this.con = con;
    }
    
      public ArrayList<FPago> findAll(){
          
        try {
            sentencia = "SELECT * FROM FPAGO";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            ArrayList<FPago> fPagos = new ArrayList<>();
            while(rs.next()){
                fPagos.add(new FPago(rs.getString("CCODFPAGO"), rs.getString("CDESFPAGO"), rs.getInt("NRECIBOS")));
            }
            return fPagos;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    public FPago findById(FPago f){
        try {
            sentencia = "SELECT * FROM FPAGO WHERE CCODFPAGO = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, f.getcCodFPago());
            try (ResultSet rFPago = ps.executeQuery()) {
                if(!rFPago.next()) return null;
                return new FPago(f.getcCodFPago(), rFPago.getString("CDESFPAGO"), rFPago.getInt("NRECIBOS"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    public int create(FPago f){
        if(f == null)return 0;
        try  {
            sentencia = "INSERT INTO FPAGO(CCODFPAGO,CDESFPAGO,NRECIBOS) VALUES(?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, f.getcCodFPago());
            ps.setString(2, f.getcDesFPago());
            ps.setInt(3, f.getnRecivos());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int update(FPago f){
        if(f == null)return 0;
        try {
            sentencia = "UPDATE FPAGO SET CDESFPAGO = ?, NRECIBOS = ? WHERE CCODFPAGO LIKE ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, f.getcDesFPago());
            ps.setInt(2, f.getnRecivos());
            ps.setString(3, f.getcCodFPago());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int delete(FPago f){
        if(f == null)return 0;
        try {
            sentencia = "DELETE FROM FPAGO WHERE CCODFPAGO = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, f.getcCodFPago());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    
}
