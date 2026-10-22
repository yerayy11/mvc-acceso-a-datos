package tema02_ejercicio1.Controllers;

import java.sql.*;
import java.util.ArrayList;
import tema02_ejercicio1.Models.FPago;

public class FPagoController {
    private Connection con;

    public FPagoController(Connection con) {
        if (this.con == null) this.con = con;
    }

    public ArrayList<FPago> findAll() {
        String sentencia = "SELECT * FROM FPAGO";

        try (PreparedStatement ps = con.prepareStatement(sentencia);
             ResultSet rs = ps.executeQuery()) {

            ArrayList<FPago> fPagos = new ArrayList<>();
            while (rs.next()) {
                fPagos.add(new FPago(rs.getString("CCODFPAGO"), rs.getString("CDESFPAGO"), rs.getInt("NRECIBOS")));
            }
            return fPagos;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public FPago findById(FPago f) {
        String sentencia = "SELECT * FROM FPAGO WHERE CCODFPAGO = ?";

        try (PreparedStatement ps = con.prepareStatement(sentencia)) {
            ps.setString(1, f.getcCodFPago());

            try (ResultSet rFPago = ps.executeQuery()) {
                if (!rFPago.next()) return null;
                return new FPago(f.getcCodFPago(), rFPago.getString("CDESFPAGO"), rFPago.getInt("NRECIBOS"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public int create(FPago f) {
        if (f == null) return 0;
        String sentencia = "INSERT INTO FPAGO(CCODFPAGO,CDESFPAGO,NRECIBOS) VALUES(?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sentencia)) {
            ps.setString(1, f.getcCodFPago());
            ps.setString(2, f.getcDesFPago());
            ps.setInt(3, f.getnRecivos());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    public int update(FPago f) {
        if (f == null) return 0;
        String sentencia = "UPDATE FPAGO SET CDESFPAGO = ?, NRECIBOS = ? WHERE CCODFPAGO = ?";

        try (PreparedStatement ps = con.prepareStatement(sentencia)) {
            ps.setString(1, f.getcDesFPago());
            ps.setInt(2, f.getnRecivos());
            ps.setString(3, f.getcCodFPago());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    public int delete(FPago f) {
        if (f == null) return 0;
        String sentencia = "DELETE FROM FPAGO WHERE CCODFPAGO = ?";

        try (PreparedStatement ps = con.prepareStatement(sentencia)) {
            ps.setString(1, f.getcCodFPago());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}