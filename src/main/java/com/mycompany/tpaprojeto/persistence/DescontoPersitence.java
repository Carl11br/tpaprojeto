package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Desconto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class  DescontoPersitence {

    private static HashMap<Integer, Desconto> descontos;

    public DescontoPersitence() {
        if (this.lerDescontosDoArquivo() == false) {
            this.descontos = new HashMap<>();
        }
    }

    public boolean salvarDescontosNoArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("descontos.bin"));
            oos.writeObject(descontos);
            oos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean lerDescontosDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("descontos.bin"));
            this.descontos = (HashMap<Integer, Desconto>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarDescontoNoArquivo(Desconto d) {
       
        if (descontos.keySet().contains(d.getDesconto())) {
            return false;
        } else {
            descontos.put(d.getDesconto(), d);
            return this.salvarDescontosNoArquivo();
        }
    }

    public boolean deletarDescontoDoArquivo(int descontoPorcentagem) {
        if (descontos.keySet().contains(descontoPorcentagem)) {
            descontos.remove(descontoPorcentagem);
        } else {
            return false;
        }
        return this.salvarDescontosNoArquivo();

    }

    public Desconto buscarDescontoNoArquivo(int descontoPorcentagem) {
        if (descontos.keySet().contains(descontoPorcentagem)) {
            return descontos.get(descontoPorcentagem);
        } else {
            return null;
        }
    }

    public HashMap<Integer, Desconto> getDescontos() {
        return descontos;
    }
}
