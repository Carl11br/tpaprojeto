package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Desconto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class DescontoPersitence {

    HashMap<Float, Desconto> descontos;

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
            this.descontos = (HashMap<Float, Desconto>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarDescontoNoArquivo(float descontoPorcentagem, float valorMinimo) {
        Desconto d = new Desconto(descontoPorcentagem,valorMinimo);
        if (descontos.keySet().contains(descontoPorcentagem)) {
            return false;
        } else {
            descontos.put(descontoPorcentagem, d);
            return this.salvarDescontosNoArquivo();
        }
    }

    public boolean deletarDescontoDoArquivo(float descontoPorcentagem) {
        if (descontos.keySet().contains(descontoPorcentagem)) {
            descontos.remove(descontoPorcentagem);
        } else {
            return false;
        }
        return this.salvarDescontosNoArquivo();

    }

    public Desconto buscarDescontoNoArquivo(float descontoPorcentagem) {
        if (descontos.keySet().contains(descontoPorcentagem)) {
            return descontos.get(descontoPorcentagem);
        } else {
            return null;
        }
    }

    public HashMap<Float, Desconto> getDescontos() {
        return descontos;
    }
}
