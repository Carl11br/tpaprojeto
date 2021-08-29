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
    private static HashMap<Integer, Gerente> gerentes;
    private static final Gerente gerentePadrao = new Gerente(1,"Gerente Padrão","admin");

   
    public GerentePersitence() {
        if(this.lerGerentesDoArquivo() == false)
            this.gerentes = new HashMap<>();
            this.gerentes.put(gerentePadrao.getMatricula(), gerentePadrao);
    }
     public static Gerente getGerentePadrao() {
        return gerentePadrao;
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
    public int deletarGerenteDoArquivo(int mat)
    {
        if(mat == gerentePadrao.getMatricula())
            return -1;
        if (gerentes.keySet().contains(mat))
        {
            Gerente g = gerentes.remove(mat);
            if(this.salvarGerentesNoArquivo())
                return 1;
            else//Se der algum erro ao salvar no arquivo, devolve o gerente removido ao hashmap
                gerentes.put(g.getMatricula(),g);
        }
            return 0;
        
        
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

    public int alterarGerenteNoArquivo(Gerente g) {
        if(g.getMatricula() == gerentePadrao.getMatricula())
            return -1;
        if (gerentes.keySet().contains(g.getMatricula()))//matrícula já cadastrada
        {
            gerentes.put(g.getMatricula(), g);
            if(this.salvarGerentesNoArquivo()){
                return 1;
            }
        }
        return  0;
    }
    
}
