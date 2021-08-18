package com.mycompany.tpaprojeto.model;

public class Cliente {
    int cpf;
    float comprasAcumuladas;

    public Cliente(int cpf) {
        this.cpf = cpf;
        this.comprasAcumuladas = 0;
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
    
}
