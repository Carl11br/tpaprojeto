package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.persistence.CompraPersitence;
import java.util.HashMap;

public class CompraController {
   private static final CompraPersitence compraPer = new CompraPersitence();
    public String recuperarTodasComprasComoString() {
        String s = "";
        for (HashMap.Entry<Integer,Compra> ele : compraPer.getCompras().entrySet()) {
            s = s + "----------------------\nId: "+ ele.getKey()+ "\n" + ele.getValue().toString();
        }
        return s;
    }
    public int deletarComprasMaisAntigas(int n)
    {
        if(n<=0)
            return 0;
        return compraPer.deletarComprasAntigasDoArquivo(n);
    }
}
