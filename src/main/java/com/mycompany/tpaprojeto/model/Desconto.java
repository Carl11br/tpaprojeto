/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpaprojeto.model;

import java.util.HashMap;

public class Desconto {
    private float desconto;
    private float valorMinimo;

    public Desconto(float desconto, float valorMinimo) {
        this.desconto = desconto;
        this.valorMinimo = valorMinimo;
    }

    public float getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }
    @Override
    public String toString()
    {
        return "Desconto:" + this.desconto + "%\n" + "Valor mínimo: R$ " +
                this.valorMinimo + "\n";
    }
}
