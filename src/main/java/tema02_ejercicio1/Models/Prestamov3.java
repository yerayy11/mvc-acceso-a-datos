package tema02_ejercicio1.Models;

import java.time.LocalDate;
import java.util.List;

public class Prestamov3 {
    private int nNumPrest;
    private LocalDate dFecPrest;
    private int nImpPrest;
    private int nImpReci;
    private FPago cCodFPago;
    private List<Recibov1> listaRecibos;

    @Override
    public String toString() {
        return "Prestamov3{" + "nNumPrest=" + nNumPrest + ", dFecPrest=" + dFecPrest + ", nImpPrest=" + nImpPrest + ", nImpReci=" + nImpReci + ", cCodFPago=" + cCodFPago + ", listaRecibos=" + listaRecibos + '}';
    }

    public Prestamov3(int nNumPrest, LocalDate dFecPrest, int nImpPrest, int nImpReci, FPago cCodFPago, List<Recibov1> listaRecibos) {
        this.nNumPrest = nNumPrest;
        this.dFecPrest = dFecPrest;
        this.nImpPrest = nImpPrest;
        this.nImpReci = nImpReci;
        this.cCodFPago = cCodFPago;
        this.listaRecibos = listaRecibos;
    }

    public Prestamov3() {}
    
    public int getnNumPrest() {
        return nNumPrest;
    }

    public void setnNumPrest(int nNumPrest) {
        this.nNumPrest = nNumPrest;
    }

    public LocalDate getdFecPrest() {
        return dFecPrest;
    }

    public void setdFecPrest(LocalDate dFecPrest) {
        this.dFecPrest = dFecPrest;
    }

    public int getnImpPrest() {
        return nImpPrest;
    }

    public void setnImpPrest(int nImpPrest) {
        this.nImpPrest = nImpPrest;
    }

    public int getnImpReci() {
        return nImpReci;
    }

    public void setnImpReci(int nImpReci) {
        this.nImpReci = nImpReci;
    }

    public FPago getcCodFPago() {
        return cCodFPago;
    }

    public void setcCodFPago(FPago cCodFPago) {
        this.cCodFPago = cCodFPago;
    }

    public List<Recibov1> getListaRecibos() {
        return listaRecibos;
    }

    public void setListaRecibos(List<Recibov1> listaRecibos) {
        this.listaRecibos = listaRecibos;
    }
    
    
}
