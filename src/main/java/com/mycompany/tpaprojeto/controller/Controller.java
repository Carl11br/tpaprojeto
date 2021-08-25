package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Gerente;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;
import com.mycompany.tpaprojeto.persistence.CaixaPersitence;
import com.mycompany.tpaprojeto.persistence.ClientePersitence;
import com.mycompany.tpaprojeto.persistence.CompraPersitence;
import com.mycompany.tpaprojeto.persistence.GerentePersitence;
import com.mycompany.tpaprojeto.persistence.ProdutoPersitence;

import java.util.List;

public class Controller {

    ProdutoPersitence prodPer;
    CaixaPersitence caixaPer;
    GerentePersitence gerentePer;
    CompraPersitence compraPer;
    ClientePersitence clientePer;

    Compra compra;

    public Controller() {
        this.prodPer = new ProdutoPersitence();
        this.caixaPer = new CaixaPersitence();
        this.gerentePer = new GerentePersitence();
        this.compraPer = new CompraPersitence();
        this.clientePer = new ClientePersitence();
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

    public boolean cadastrarGerente(int mat, String nome, String senha) {

        Gerente g = new Gerente(mat, nome, senha);
        return this.gerentePer.adicionarGerenteNoArquivo(g);

    }

    public Item criarItem(Produto p, float qtd) {
        if(qtd>0)
            return new Item(p, qtd);
        return null;

    }

    public void adicionarItemACompra(Item i, Compra compra) {
        //checar se já tem um item com esse mesmo produto, 
        //para aumentar a quantidade, ao invés de add outro item do msm produto
        Item itemBuscado = buscarItemNaCompra(i.getProduto().getCodigo(),compra);
        if(itemBuscado==null)
            compra.add_Item(i);
        else
        {
            itemBuscado.aumentarQuantidade(i.getQuantidade());
        }
    }

    public Item buscarItemNaCompra(int cod, Compra compra) {
        for (Item i : compra.getItens()) {
            if (i.getProduto().getCodigo() == cod) {
                return i;
            }
        }
        return null;
    }

    public boolean removerItemDaCompra(int cod, Compra compra) {
        Item i = buscarItemNaCompra(cod, compra);
        if (i != null) {
            return compra.remove_Item(i);
        }
        return false;
    }

    public String recuperarTodosItensComoString(Compra compra) {
        String s = "";
        for (Item i : compra.getItens()) {
            s = s + "----------------------\n" + i.toString();
        }
        return s;
    }
    //public Item buscarItemMesmoProduto(int cod)

    public Compra iniciarCompra(Cliente cliente) {
        Compra c = new Compra(cliente);
        return c;
    }

    public boolean finalizarCompra(Compra compra, Cliente cliente){
        if(this.compraPer.adicionarCompraNoArquivo(compra)) {
            cliente.setComprasAcumuladas(cliente.getComprasAcumuladas() + 1);
            return true;
        }
        return false;
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

    public Gerente buscarGerente(int mat) {
        return gerentePer.buscarGerenteNoArquivo(mat);
    }
    public boolean autenticarGerente(int mat, String senha)
    {
        Gerente g = buscarGerente(mat);
        if(g!=null)
        {
            if(g.getSenha().equals(senha))
                return true;
        }
        return false;
    }
    public String recuperarTodosGerentesComoString() {
        String s = "";
        for (Gerente g : this.gerentePer.getGerentes().values()) {
            s = s + "----------------------\n" + g.toString();
        }
        return s;
    }

    public boolean cadastrarCliente(String cpf, String nome) {
        Cliente c = new Cliente(cpf, nome, 0.0f);
        return clientePer.adicionarClienteNoArquivo(c);
    }

    public boolean deletarGerente(int mat) {
        return gerentePer.deletarGerenteDoArquivo(mat);
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

}
