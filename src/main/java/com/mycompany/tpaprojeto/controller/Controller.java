package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Desconto;
import com.mycompany.tpaprojeto.model.Gerente;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;
import com.mycompany.tpaprojeto.persistence.CaixaPersitence;
import com.mycompany.tpaprojeto.persistence.ClientePersitence;
import com.mycompany.tpaprojeto.persistence.CompraPersitence;
import com.mycompany.tpaprojeto.persistence.DescontoPersitence;
import com.mycompany.tpaprojeto.persistence.GerentePersitence;
import com.mycompany.tpaprojeto.persistence.ProdutoPersitence;

import java.util.List;

public class Controller {

    ProdutoPersitence prodPer;
    CaixaPersitence caixaPer;
    GerentePersitence gerentePer;
    CompraPersitence compraPer;
    ClientePersitence clientePer;
    DescontoPersitence descontoPer;

    Compra compra;

    public Controller() {
        this.prodPer = new ProdutoPersitence();
        this.caixaPer = new CaixaPersitence();
        this.gerentePer = new GerentePersitence();
        this.compraPer = new CompraPersitence();
        this.clientePer = new ClientePersitence();
        this.descontoPer = new DescontoPersitence();
    }

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

    public boolean cadastrarCaixa(int mat, String nome) {
        Caixa c = new Caixa(mat, nome);
        return this.caixaPer.adicionarCaixaNoArquivo(c);
    }

 
    public Caixa buscarCaixa(int cod) {
        return caixaPer.buscarCaixaNoArquivo(cod);
    }

    public String recuperarTodosCaixasComoString() {
        String s = "";
        for (Caixa c : this.caixaPer.getCaixas().values()) {
            s = s + "----------------------\n" + c.toString();
        }
        return s;
    }

    public boolean deletarCaixa(int mat) {
        return caixaPer.deletarCaixaDoArquivo(mat);
    }

   
    public boolean cadastrarCliente(String cpf, String nome, float totalComprasAcumuladas) {
        Cliente c = new Cliente(cpf, nome, totalComprasAcumuladas);
        return clientePer.adicionarClienteNoArquivo(c);
    }

    public Cliente buscarCliente(String cpf) {
        return clientePer.buscarClienteNoArquivo(cpf);
    }

    public boolean deletarCliente(String cpf) {
        return clientePer.deletarClienteDoArquivo(cpf);
    }

    public String recuperarTodosClientesComoString() {
        String s = "";
        for (Cliente c : this.clientePer.getClientes().values()) {
            s = s + "----------------------\n" + c.toString();
        }
        return s;
        
    }
    DescontoController dc = new DescontoController();
    
    public boolean aplicaDescontoCompra(Compra c)
    {
       int desconto =  dc.decidirDescontoCliente(c.getCliente());
       if(desconto>0)
       {
       c.setDescontoRecebido(desconto);
       float descontoEmReais = c.getTotal() * desconto/100;
       c.setTotal(c.getTotal()-descontoEmReais);
       return true;
       }
       return false;
        
    }
   

}
