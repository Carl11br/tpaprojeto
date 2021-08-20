package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Caixa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Persitence {
    
    FileOutputStream caixasFOS ;
    FileOutputStream  gerentesFOS;
    //File comprasFOS;
    FileOutputStream  produtosFOS;

    public Persitence() {
        try{this.caixasFOS = new FileOutputStream("caixas.bin");}
        catch(FileNotFoundException e)
        {
           //criar o arquivo se ele não existir
        }
        try {this.gerentesFOS = new FileOutputStream("gerentes.bin");}
        catch(FileNotFoundException e){
        }
        try{this.produtosFOS = new FileOutputStream("produtos.bin");}
        catch(FileNotFoundException e)
        {
        }
    }
    public void persitirCaixa (Caixa c) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(caixasFOS);
        oos.writeObject(c);
        oos.close();
        
    }
    
}
