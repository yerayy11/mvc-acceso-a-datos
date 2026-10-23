package tema02_ejercicio1;

import java.sql.Connection;
import javax.swing.JOptionPane;
import tema02_ejercicio1.Controllers.DBController;
import tema02_ejercicio1.Views.Index;

public class Launcher {
    
    public static void main(String[] args) {
        
        DBController contr = new DBController();
        //en caso de que la conexión sea mala
        Connection con;
        if((con = contr.getCon()) == null){
            JOptionPane.showMessageDialog(null, "Error en la conexión");
           
            return;
        }
        new Index(con).setVisible(true);
        
        
    }
}
