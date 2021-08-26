package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Gerente;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CompraPersitence {
     private static HashMap<Integer, Compra> compras;
    public CompraPersitence() {
        if(this.lerComprasDoArquivo() == false)
            this.compras = new HashMap<>();
    }

    public boolean salvarComprasNoArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("compras.bin"));
            oos.writeObject(compras);
            oos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean lerComprasDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("compras.bin"));
            this.compras = (HashMap<Integer, Compra>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {

            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarCompraNoArquivo(Compra comp) {

            compras.put(compras.size()+1,comp);
            return this.salvarComprasNoArquivo();
        }
    
    public boolean deletarCompraDoArquivo(int id)
    {
        if (compras.keySet().contains(id))
            compras.remove(id);
        else
            return false;
        return this.salvarComprasNoArquivo();
        
    }
    public Compra buscarCompraNoArquivo(int id)
    {
        if (compras.keySet().contains(id))
            return compras.get(id);
        else 
            return null;
    }
    public HashMap<Integer, Compra> getCompras() {
        return compras;
    }
}
