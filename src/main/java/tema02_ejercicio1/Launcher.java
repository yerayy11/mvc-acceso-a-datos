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
            //se termina la aplicación indicando con el código 1 que ha habido un error
            System.exit(1);
        }
        new Index(con).setVisible(true);
        
        
    }
}
