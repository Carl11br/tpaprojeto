package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
     public Item criarItem(Produto p, float qtd) {
        if(qtd>0)
            return new Item(p, qtd);
        return null;
    }
      public String recuperarTodosItensComoString(List<Item> itens) {
        String s = "";
        for (Item i : itens) {
            s = s + "----------------------\n" + i.toString();
        }
        return s;
    }
}
