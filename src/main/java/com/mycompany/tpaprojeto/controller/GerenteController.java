package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Gerente;
import com.mycompany.tpaprojeto.persistence.GerentePersitence;

public class GerenteController {

    private static final GerentePersitence gerentePer = new GerentePersitence();

    public boolean cadastrarGerente(int mat, String nome, String senha) {
        Gerente g = new Gerente(mat, nome, senha);
        return GerenteController.gerentePer.adicionarGerenteNoArquivo(g);

    }

    public Gerente buscarGerente(int mat) {
        return gerentePer.buscarGerenteNoArquivo(mat);
    }

    public boolean autenticarGerente(int mat, String senha) {
        Gerente g = buscarGerente(mat);
        if (g != null) {
            if (g.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public String recuperarTodosGerentesComoString() {
        String s = "";
        for (Gerente g : GerenteController.gerentePer.getGerentes().values()) {
            s = s + "----------------------\n" + g.toString();
        }
        return s;
    }

    public int deletarGerente(int mat) {
        return gerentePer.deletarGerenteDoArquivo(mat);
    }

}
