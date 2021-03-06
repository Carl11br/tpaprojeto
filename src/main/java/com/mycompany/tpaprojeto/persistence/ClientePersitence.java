package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Cliente;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ClientePersitence {
   private static HashMap<String, Cliente> clientes;

    public ClientePersitence() {
        if(this.lerClientesDoArquivo() == false)
            this.clientes = new HashMap<>();
    }

    public boolean salvarClientesNoArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clientes.bin"));
            oos.writeObject(clientes);
            oos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean lerClientesDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clientes.bin"));
            this.clientes = (HashMap<String, Cliente>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarClienteNoArquivo(Cliente c) {

        if (clientes.keySet().contains(c.getCpf()))//cpf já cadastrada
        {
            return false;
        } else {
            clientes.put(c.getCpf(), c);
            return this.salvarClientesNoArquivo();
        }
    }
    public boolean deletarClienteDoArquivo(String cpf)
    {
           if (clientes.keySet().contains(cpf))
        {
            Cliente c = clientes.remove(cpf);
            if(this.salvarClientesNoArquivo())
                return true;
            else//Se der algum erro ao salvar no arquivo, devolve o caixa removido ao hashmap
                clientes.put(c.getCpf(),c);
        }
            return false;
        
    }
    public Cliente buscarClienteNoArquivo(String cpf)
    {
        if (clientes.keySet().contains(cpf))
            return clientes.get(cpf);
        else 
            return null;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public boolean alterarClienteNoArquivo(Cliente c) {
        if (!(clientes.keySet().contains(c.getCpf())))//cpf não cadastrado
        {
            return false;
        } else {
            clientes.put(c.getCpf(), c);
            return this.salvarClientesNoArquivo();
        }
    }
}
