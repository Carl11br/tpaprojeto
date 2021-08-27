package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.persistence.ClientePersitence;
import com.mycompany.tpaprojeto.persistence.CompraPersitence;
import java.util.ArrayList;
import java.util.HashMap;

public class CompraController {
   private static final CompraPersitence compraPer = new CompraPersitence();
   private static final ClientePersitence clientePer = new ClientePersitence();
   private static final ItemController itemCrtl = new ItemController();
   private static final DescontoController descontoCtrl = new DescontoController();
    public String recuperarTodasComprasComoString() {
        String s = "";
        for (HashMap.Entry<Integer,Compra> ele : compraPer.getCompras().entrySet()) {
            s = s + "----------------------\nId: "+ ele.getKey()+ "\n" + ele.getValue().toString();
        }
        return s;
    }
    public int deletarComprasMaisAntigas(int n)
    {
        if(n<=0)
            return 0;
        return compraPer.deletarComprasAntigasDoArquivo(n);
    }
    
    public Compra iniciarCompra(Cliente cliente, Caixa caixa) {
        Compra c = new Compra(cliente,caixa);
        return c;
    }
    public boolean verificarCompraVazia(Compra compra)
    {
        return compra.verificarCompraVazia();
    }
    public boolean finalizarCompra(Compra compra, Cliente cliente){
        if(this.compraPer.adicionarCompraNoArquivo(compra)) {
            cliente.setComprasAcumuladas(cliente.getComprasAcumuladas() + compra.getTotal());
            clientePer.salvarClientesNoArquivo();
            return true;
        }
                
        return false;
    }
    public void adicionarItemACompra(Item i, Compra compra) {
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
     public String recuperarTodosItensDaCompraComoString(Compra compra)
     {
         return itemCrtl.recuperarTodosItensComoString(compra.getItens());
     }
     public boolean aplicaDescontoCompra(Compra c)
    {
       int desconto =  descontoCtrl.decidirDescontoCliente(c.getCliente());
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
