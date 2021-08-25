package com.mycompany.tpaprojeto.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Item  implements Serializable{

    private Produto produto;
    private float quantidade;
    private float subtotal;

    public Item(Produto produto, float quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPreco() * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public void aumentarQuantidade(float quantidade) {
        if (quantidade > 0) {
            this.quantidade += quantidade;
            this.atualizaSubtotal();
        }
    }

    public float getSubtotal() {
        return subtotal;
    }

    private void atualizaSubtotal() {
        this.subtotal = this.produto.getPreco() * this.quantidade;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return this.produto.toString() + "Quantidade: " + this.quantidade
                + "\nSubtotal: R$ " + df.format(this.subtotal) + "\n";
    }
}
