package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Produto;
import com.mycompany.tpaprojeto.persistence.ProdutoPersitence;

public class ProdutoController {

    private static final ProdutoPersitence prodPer = new ProdutoPersitence();

    public boolean cadastrarProduto(int cod, String nome, float preco) {
        //checar se o produto já está cadastrado ou não
        Produto prod;
        prod = new Produto(cod, nome, preco);
        return this.prodPer.adicionarProdutoNoArquivo(prod);

    }

    public boolean deletarProduto(int cod) {
        return prodPer.deletarProdutoDoArquivo(cod);
    }

    public String recuperarTodosProdutosComoString() {
        String s = "";
        for (Produto p : this.prodPer.getProdutos().values()) {
            s = s + "----------------------\n" + p.toString();
        }
        return s;
    }

    public Produto buscarProduto(int cod) {

        return prodPer.buscarProdutoNoArquivo(cod);
    }

   
}
