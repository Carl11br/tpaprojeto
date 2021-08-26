package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Produto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ProdutoPersitence {

    private static HashMap<Integer, Produto> produtos;

    public ProdutoPersitence() {
        if(this.lerProdutosDoArquivo() == false)
            this.produtos = new HashMap<>();
    }

    public boolean salvarProdutosNoArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("produtos.bin"));
            oos.writeObject(produtos);
            oos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean lerProdutosDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("produtos.bin"));
            this.produtos = (HashMap<Integer, Produto>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {

            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarProdutoNoArquivo(Produto p) {

        if (produtos.keySet().contains(p.getCodigo()))//código já cadastrado
        {
            return false;
        } else {
            produtos.put(p.getCodigo(), p);
            return this.salvarProdutosNoArquivo();
        }
    }
    public boolean deletarProdutoDoArquivo(int cod)
    {
        if (produtos.keySet().contains(cod))
            produtos.remove(cod);
        else
            return false;
        return this.salvarProdutosNoArquivo();
        
    }
    public Produto buscarProdutoNoArquivo(int cod)
    {
        if (produtos.keySet().contains(cod))
            return produtos.get(cod);
        else 
            return null;
    }

    public HashMap<Integer, Produto> getProdutos() {
        return produtos;
    }
}
