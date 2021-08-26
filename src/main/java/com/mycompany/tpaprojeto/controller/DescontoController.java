/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Desconto;
import com.mycompany.tpaprojeto.persistence.DescontoPersitence;

/**
 *
 * @author Carlos
 */
public  class DescontoController {
    private static final DescontoPersitence descontoPer = new DescontoPersitence();
    
     public boolean cadastrarDesconto(int desconto, float valorMinimo) {
        Desconto d = new Desconto(desconto,valorMinimo);
        return descontoPer.adicionarDescontoNoArquivo(d);
    }
      public Desconto buscarDesconto(int desconto) {
        return descontoPer.buscarDescontoNoArquivo(desconto);
    }
    public boolean deletarDesconto(int desconto) {
        return descontoPer.deletarDescontoDoArquivo(desconto);
    }
    public String recuperarTodosDescontosComoString() {
        String s = "";
        for (Desconto d : descontoPer.getDescontos().values()) {
            s = s + "----------------------\n" + d.toString();
        }
        return s;
    }
    public int decidirDescontoCliente(Cliente c)
    {
        float totalAcumulado = c.getComprasAcumuladas();
        int descontoAtual = 0;
        for (Desconto d : descontoPer.getDescontos().values()) {
            if(totalAcumulado >= d.getValorMinimo())
                descontoAtual = d.getDesconto();
             else
                break;
    }
        return descontoAtual;
    } 
}
