
package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Produto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CaixaPersitence {
    private HashMap<Integer, Caixa> caixas;

    public CaixaPersitence() {
        if(this.lerCaixasDoArquivo() == false)
            this.caixas = new HashMap<>();
    }

    public boolean salvarCaixasNoArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("caixas.bin"));
            oos.writeObject(caixas);
            oos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean lerCaixasDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("caixas.bin"));
            this.caixas = (HashMap<Integer, Caixa>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {

            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarCaixaNoArquivo(Caixa cx) {

        if (caixas.keySet().contains(cx.getMatricula()))//matrícula já cadastrada
        {
            return false;
        } else {
            caixas.put(cx.getMatricula(), cx);
            return this.salvarCaixasNoArquivo();
        }
    }
    public boolean deletarCaixaDoArquivo(int mat)
    {
        if (caixas.keySet().contains(mat))
            caixas.remove(mat);
        else
            return false;
        return this.salvarCaixasNoArquivo();
        
    }
    public Caixa buscarCaixaNoArquivo(int mat)
    {
        if (caixas.keySet().contains(mat))
            return caixas.get(mat);
        else 
            return null;
    }

    public HashMap<Integer, Caixa> getCaixas() {
        return caixas;
    }
    
}
