package com.mycompany.tpaprojeto.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Compra implements Serializable{
    private float total;
    private List<Item> itens;
    Cliente cliente;
    int descontoRecebido;

    public Compra(Cliente cliente) {
        this.total = 0;
        this.cliente = cliente;
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

    
    
}
