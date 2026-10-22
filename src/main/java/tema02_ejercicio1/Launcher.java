package tema02_ejercicio1;

import javax.swing.JOptionPane;
import tema02_ejercicio1.Controllers.DBController;
import tema02_ejercicio1.Views.Index;
import static tema02_ejercicio1.Views.Index.contr;

public class Launcher {
    
    public static void main(String[] args) {
        
        DBController contr = new DBController();
        //en caso de que la conexión sea mala
        if(contr.getCon() == null){
            JOptionPane.showMessageDialog(null, "Error en la conexión");
           
            return;
        }
        new Index().setVisible(true);
        
        
    }
}
