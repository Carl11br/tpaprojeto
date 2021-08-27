package com.mycompany.tpaprojeto.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Compra implements Serializable{
    private float total;
    private List<Item> itens;
    private Cliente cliente;
    private Caixa caixa;
    int descontoRecebido;

    public Compra(Cliente cliente, Caixa caixa) {
        this.total = 0;
        this.cliente = cliente;
        this.caixa = caixa;
        this.descontoRecebido = 0;//Calcular descontoRecebido baseado no comprasAcumuladas do cliente
        this.itens = new ArrayList<Item>();
    }
    public boolean add_Item(Item i)
    {
       this.atualizarTotal(i.getSubtotal());
       return this.itens.add(i);
       
    }

    public boolean remove_Item(Item i){
         this.atualizarTotal(-i.getSubtotal());
        return this.itens.remove(i);
    }
    
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public String getTotalComoString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.getTotal());
    }

    private void atualizarTotal(float valor) {
        this.total += valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getDescontoRecebido() {
        return descontoRecebido;
    }

    public void setDescontoRecebido(int descontoRecebido) {
        this.descontoRecebido = descontoRecebido;
    }

    public List<Item> getItens() {
        return itens;
    }
    @Override
    public String toString() {
        return String.format("Cliente CPF: %s\nCaixa matrícula: %d\nTotal: R$ %.2f\n"
                , this.cliente.getCpf(),this.caixa.getMatricula(),this.total);
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    
    
}
