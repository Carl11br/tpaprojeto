package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.persistence.CaixaPersitence;

public class CaixaController {
    private static final CaixaPersitence caixaPer = new CaixaPersitence();
    
    public boolean cadastrarCaixa(int mat, String nome) {
        Caixa c = new Caixa(mat, nome);
        return CaixaController.caixaPer.adicionarCaixaNoArquivo(c);
    }

 
    public Caixa buscarCaixa(int cod) {
        return caixaPer.buscarCaixaNoArquivo(cod);
    }

    public String recuperarTodosCaixasComoString() {
        String s = "";
        for (Caixa c : CaixaController.caixaPer.getCaixas().values()) {
            s = s + "----------------------\n" + c.toString();
        }
        return s;
    }

    public boolean deletarCaixa(int mat) {
        return CaixaController.caixaPer.deletarCaixaDoArquivo(mat);
    }
}
