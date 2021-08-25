package com.mycompany.tpaprojeto.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Produto implements Serializable {
    private int codigo;
    private String nome;
    private float preco;

    public Produto(int codigo, String nome, float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Código: " + this.codigo + "\nNome: " + this.nome + "\nPreço: R$ "+ df.format(this.preco) + "\n";
    }
    
    
}
