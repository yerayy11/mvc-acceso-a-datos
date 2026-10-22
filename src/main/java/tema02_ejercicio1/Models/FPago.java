package tema02_ejercicio1.Models;

import java.util.Objects;

public class FPago {
    private String cCodFPago;
    private String cDesFPago;
    private int nRecivos;

    public String getcCodFPago() {
        return cCodFPago;
    }

    public void setcCodFPago(String cCodFPago) {
        this.cCodFPago = cCodFPago;
    }

    public String getcDesFPago() {
        return cDesFPago;
    }

    public void setcDesFPago(String cDesFPago) {
        this.cDesFPago = cDesFPago;
    }

    public int getnRecivos() {
        return nRecivos;
    }

    public void setnRecivos(int nRecivos) {
        this.nRecivos = nRecivos;
    }

    public FPago() {
        if(cDesFPago == null)cDesFPago = "";
    }

    public FPago(String cCodFPago, String cDesFPago, int nRecivos) {
        this.cCodFPago = cCodFPago;
        this.cDesFPago = cDesFPago;
        this.nRecivos = nRecivos;
    }
    
    

    @Override
    public String toString() {
        return cCodFPago+" " +cDesFPago;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.cCodFPago);
        hash = 47 * hash + Objects.hashCode(this.cDesFPago);
        hash = 47 * hash + this.nRecivos;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FPago other = (FPago) obj;
        return (this.cCodFPago == null ? other.cCodFPago == null : this.cCodFPago.equals(other.cCodFPago));
    }
    
    
}
