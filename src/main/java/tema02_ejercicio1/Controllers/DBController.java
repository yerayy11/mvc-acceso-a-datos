package tema02_ejercicio1.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {

    private final String HOST = "localhost";
    private final String PORT_MYSQL = "3306";
    private final String DB = "BANCOS";

    private final String URL_MYSQL = "jdbc:mysql://" + HOST + ":" + PORT_MYSQL + "/" + DB;
    private final String USER = "BANCOS";
    private final String PASSWORD = "BANCOS";

    private Connection con;

    public DBController() {
        try {
            this.con = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Conexión establecida.");
        } catch (SQLException ex) {
            System.err.println("Error de conexión");
        }
    }

    public Connection getCon() {
        return this.con;
    }
}
