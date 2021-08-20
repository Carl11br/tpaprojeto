package com.mycompany.tpaprojeto.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    int cpf;
    float comprasAcumuladas;

    public Cliente(int cpf, float comprasAcumuladas) {
        this.cpf = cpf;
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
    
}
