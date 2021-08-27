package com.mycompany.tpaprojeto.controller;

import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.persistence.ClientePersitence;

public class ClienteController {

    private static final ClientePersitence clientePer = new ClientePersitence();

    public boolean cadastrarCliente(String cpf, String nome, float totalComprasAcumuladas) {
        Cliente c = new Cliente(cpf, nome, totalComprasAcumuladas);
        return clientePer.adicionarClienteNoArquivo(c);
    }

    public Cliente buscarCliente(String cpf) {
        return clientePer.buscarClienteNoArquivo(cpf);
    }

    public boolean deletarCliente(String cpf) {
        return clientePer.deletarClienteDoArquivo(cpf);
    }

    public String recuperarTodosClientesComoString() {
        String s = "";
        for (Cliente c : this.clientePer.getClientes().values()) {
            s = s + "----------------------\n" + c.toString();
        }
        return s;

    }
}
