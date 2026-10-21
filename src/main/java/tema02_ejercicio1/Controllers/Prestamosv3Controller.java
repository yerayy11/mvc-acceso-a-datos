package tema02_ejercicio1.Controllers;

import java.sql.Connection;
import java.util.ArrayList;
import tema02_ejercicio1.Models.Prestamov3;

public class Prestamosv3Controller {

    private Connection con;

    public Prestamosv3Controller(Connection con) {
        if(this.con == null) this.con = con;    
    }
    
    public ArrayList<Prestamov3> findAllLazy(){
        
        return null;
    }
    
    public ArrayList<Prestamov3> findAllEager(){
        
        return null;
    }
    
    public Prestamov3 findById(Prestamov3 pId){
        
        return null;
    }
    
    public int create(Prestamov3 prestamo){
        
        return 0;
    }
    
    public int update(Prestamov3 prestamo){
        
        return 0;
    }
    
    public int delete(Prestamov3 prestamo){
        
        return 0;
    }
}
