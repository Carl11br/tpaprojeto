package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Produto;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persitence {

    FileOutputStream caixasFOS;
    FileOutputStream gerentesFOS;
    //File comprasFOS;
    FileOutputStream produtosFOS;
    FileInputStream produtosFIS;

    public Persitence() {
        try {
            this.caixasFOS = new FileOutputStream("caixas.bin", true);
        } catch (FileNotFoundException e) {
            //criar o arquivo se ele não existir
        }
        try {
            this.gerentesFOS = new FileOutputStream("gerentes.bin", true);
        } catch (FileNotFoundException e) {
        }
        try {
            this.produtosFOS = new FileOutputStream("produtos.bin", true);
        } catch (FileNotFoundException e) {
        }
        try {
            this.produtosFIS = new FileInputStream("produtos.bin");
        } catch (FileNotFoundException e) {
        }
    }

    public boolean salvarCaixa(Caixa c) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(caixasFOS);
            oos.writeObject(c);
            oos.close();
            return true;
        } catch (IOException ex) {
            return false;
        }

    }

    public boolean salvarProdutoArquivo(Produto p) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(produtosFOS);
            oos.writeObject(p);
            oos.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public Produto lerProdutoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(produtosFIS);
            var p = (Produto) ois.readObject();
           // ois.close();
            return p;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
    public Produto buscarProduto(int codigo)
    {
        Produto prod ;
        while((prod= this.lerProdutoArquivo())!=null)
        {
            if(prod.getCodigo()==codigo)
                return prod;
        }
        return null;
    }
    public void deletarProduto(int codigo)
    {
        var produtos = new ArrayList<Produto>();
        Produto prod ;
        while((prod= this.lerProdutoArquivo())!=null)
        {
            System.out.println(prod.toString());
            if(prod.getCodigo()!=codigo)
                produtos.add(prod);       
        }
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("produtos.bin", false));//deleta o arquivoo e cria dnv
           for (Produto p : produtos)
           {
            oos.writeObject(p);
           }
            oos.close();         
        } catch (IOException ex) {
            
        }
        
        
    }

}
