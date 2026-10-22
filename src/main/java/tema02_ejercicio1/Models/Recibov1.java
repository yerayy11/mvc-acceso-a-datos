package tema02_ejercicio1.Models;

import java.time.LocalDate;

public class Recibov1 {
    private int nNumPrest;
    private int nNumRecib;
    private LocalDate dFecRec;
    private int nImpRec;
    private LocalDate dFecPago;

    public int getnNumPrest() {
        return nNumPrest;
    }

    public void setnNumPrest(int nNumPrest) {
        this.nNumPrest = nNumPrest;
    }

    public int getnNumRecib() {
        return nNumRecib;
    }

    public void setnNumRecib(int nNumRecib) {
        this.nNumRecib = nNumRecib;
    }

    public LocalDate getdFecRec() {
        return dFecRec;
    }

    public void setdFecRec(LocalDate dFecRec) {
        this.dFecRec = dFecRec;
    }

    public int getnImpRec() {
        return nImpRec;
    }

    public void setnImpRec(int nImpRec) {
        this.nImpRec = nImpRec;
    }

    public LocalDate getdFecPago() {
        return dFecPago;
    }

    public void setdFecPago(LocalDate dFecPago) {
        this.dFecPago = dFecPago;
    }

    @Override
    public String toString() {
        return "Recibosv1{" + "nNumPrest=" + nNumPrest + ", nNumRecib=" + nNumRecib + ", dFecRec=" + dFecRec + ", nImpRec=" + nImpRec + ", dFecPago=" + dFecPago + '}';
    }

    public Recibov1(int nNumPrest, int nNumRecib, LocalDate dFecRec, int nImpRec, LocalDate dFecPago) {
        this.nNumPrest = nNumPrest;
        this.nNumRecib = nNumRecib;
        this.dFecRec = dFecRec;
        this.nImpRec = nImpRec;
        this.dFecPago = dFecPago;
    }
    
    public Recibov1() {}
}
