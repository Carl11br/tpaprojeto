package com.mycompany.tpaprojeto.model;

import java.io.Serializable;

public class Caixa implements Serializable {
    private int matricula;
    private String nome;

    public Caixa(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    
}
