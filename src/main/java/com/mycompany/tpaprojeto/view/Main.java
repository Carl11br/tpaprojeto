package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.model.Produto;
import com.mycompany.tpaprojeto.persistence.Persitence;

public class Main {
    
    public static void main(String[] args) {
        Persitence per = new Persitence();
        Produto prod;
        prod = per.lerProdutoArquivo();
        while(prod!=null)
        {
            System.out.println(prod.toString());
            prod = per.lerProdutoArquivo();
        }
                
        View v = new View();
        v.cadastrarProduto();
     
        
    }
}
