package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Gerente;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class GerentePersitence {
    private HashMap<Integer, Gerente> gerentes;
    public GerentePersitence() {
        if(this.lerGerentesDoArquivo() == false)
            this.gerentes = new HashMap<>();
    }

    public boolean salvarGerentesNoArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gerentes.bin"));
            oos.writeObject(gerentes);
            oos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean lerGerentesDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gerentes.bin"));
            this.gerentes = (HashMap<Integer, Gerente>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {

            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarGerenteNoArquivo(Gerente ger) {

        if (gerentes.keySet().contains(ger.getMatricula()))//matrícula já cadastrada
        {
            return false;
        } else {
            gerentes.put(ger.getMatricula(), ger);
            return this.salvarGerentesNoArquivo();
        }
    }
    public boolean deletarGerenteDoArquivo(int mat)
    {
        if (gerentes.keySet().contains(mat))
            gerentes.remove(mat);
        else
            return false;
        return this.salvarGerentesNoArquivo();
        
    }
    public Gerente buscarGerenteNoArquivo(int mat)
    {
        if (gerentes.keySet().contains(mat))
            return gerentes.get(mat);
        else 
            return null;
    }

    public HashMap<Integer, Gerente> getGerentes() {
        return gerentes;
    }
    
}
