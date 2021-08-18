package com.mycompany.tpaprojeto.model;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    float total;
    List<Item> items;
    Cliente cliente;
    float desconto;

    public Compra(float total, Cliente cliente, float desconto) {
        this.total = total;
        this.cliente = cliente;
        this.desconto = desconto;
        this.items = new ArrayList<Item>();
    }
    public boolean add_Item(Item i)
    {
       return this.items.add(i);
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
    
}
