package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Produto;

public class Controller {
    public void cadastrarProduto(int cod, String nome, float preco)
    {
        Produto p = new Produto(cod,nome,preco);
        //adicionar no bd ou arq bin
    }
}
