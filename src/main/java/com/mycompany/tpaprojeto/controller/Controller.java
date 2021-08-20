package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;

public class Controller {

    public void cadastrarProduto(int cod, String nome, float preco) {
        //checar se o produto já está cadastrado ou não
        Produto p = new Produto(cod, nome, preco);
        //adicionar no bd ou arq bin
    }

    public void cadastrarCliente(int cpf) {
        //if(cpfEhValido(cpf)) checar se o cpf é valido e se já está cadastrado
        Cliente c = new Cliente(cpf, 0.0f);
        //add ao arq bin
    }

    public void cadastrarCaixa(int mat, String nome) {
        //Checar se já existe caixa com essa matrícula.
        Caixa c = new Caixa(mat, nome);
        //add ao arq bin
    }

    public void cadastrarGerente(int mat, String nome, String senha) {
        //Checar se já existe gerente com essa matrícula.
        Caixa c = new Caixa(mat, nome);
        //add ao arq bin
    }
    public Item criarItem(int cod, int qtd)
    {
        //buscar produto no arquivo binário
        Produto p = null;
        return new Item(p,qtd);
        
    }
    public void adicionarItemACompra(Item i, Compra compra) {
        return;
    }
}
