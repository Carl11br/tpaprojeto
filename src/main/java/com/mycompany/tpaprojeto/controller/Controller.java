package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;
import com.mycompany.tpaprojeto.persistence.Persitence;
import com.mycompany.tpaprojeto.persistence.ProdutoPersitence;

public class Controller {

    Persitence per;
    ProdutoPersitence prodPer;
    Compra compra;

    public Controller() {
        this.per = new Persitence();
        this.prodPer = new ProdutoPersitence();
    }
    public boolean cadastrarProduto(int cod, String nome, float preco) {
        //checar se o produto já está cadastrado ou não
        Produto prod;
        prod = new Produto(cod, nome, preco);
        return  this.prodPer.adicionarProdutoNoArquivo(prod);
        
    }
    public boolean deletarProduto(int cod)
    {
       return  prodPer.deletarProdutoDoArquivo(cod);
    }
    public String recuperarTodosProdutosComoString()
    {
        String s = "";
        for(Produto p : this.prodPer.getProdutos().values())
        {
            s = s + "----------------------\n" + p.toString();
        }
        return s;
    }
    public Produto  buscarProduto(int cod)
    {
         
        return prodPer.buscarProdutoNoArquivo(cod);
    }

    public Cliente cadastrarCliente(int cpf) {
        //if(cpfEhValido(cpf)) checar se o cpf é valido e se já está cadastrado
        Cliente c = new Cliente(cpf, 0.0f);
        //add ao arq bin
        return c;
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
        compra.add_Item(i);
        return;
    }

    public Compra iniciarCompra(Cliente cliente) {
       Compra c = new Compra(0.0f, cliente);
       return c;
    }
}
