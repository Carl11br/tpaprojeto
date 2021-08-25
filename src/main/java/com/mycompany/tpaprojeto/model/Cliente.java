package com.mycompany.tpaprojeto.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    int cpf;
    private String nome;
    float comprasAcumuladas;

    public Cliente(int cpf, String nome, float comprasAcumuladas) {
        this.cpf = cpf;
        this.nome = nome;
        this.comprasAcumuladas = comprasAcumuladas;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public float getComprasAcumuladas() {
        return comprasAcumuladas;
    }

    public void setComprasAcumuladas(float comprasAcumuladas) {
        this.comprasAcumuladas = comprasAcumuladas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     @Override
    public String toString()
    {
        return "CPF: " + this.cpf + "\nNome: " + this.nome  + "\n" + "Total acumulado em compras: R$ " +
                this.comprasAcumuladas + "\n";
    }
}
