package com.mycompany.tpaprojeto.model;

import java.util.ArrayList;
import java.util.List;

public class Compra{
    float total;
    private List<Item> itens;
    Cliente cliente;
    float desconto;

    public Compra(float total, Cliente cliente) {
        this.total = total;
        this.cliente = cliente;
        this.desconto = 0;//Calcular desconto baseado no comprasAcumuladas do cliente
        this.itens = new ArrayList<Item>();
    }
    public boolean add_Item(Item i)
    {
       return this.itens.add(i);
    }

    public boolean remove_Item(Item i){
        return this.itens.remove(i);
    }
    
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public List<Item> getItens() {
        return itens;
    }
    
}
