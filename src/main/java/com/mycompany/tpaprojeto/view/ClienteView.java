package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.model.Cliente;
import static com.mycompany.tpaprojeto.view.ViewTools.clienteCtrl;
import static com.mycompany.tpaprojeto.view.ViewTools.ler;
import static com.mycompany.tpaprojeto.view.ViewTools.op;

public class ClienteView  extends ViewTools{
     public void menuCadastroCliente() {
        boolean flag = true;
        System.out.println("------------------Menu Cadastro de Clientes------------------");
        while (flag) {
            System.out.println("1-Cadastrar Cliente");
            System.out.println("2-Excluir cadastro de um Cliente");
            System.out.println("3-Alterar cadastro de um Cliente");
            System.out.println("4-Exibir todos os Clientes cadastrados");
            System.out.println("n-Digite outro número para sair do menu de Clientes");
            op = lerInt();
            switch (op) {
                case 1:
                    cadastrarCliente();
                    this.aperteEnterContinuar();
                    break;
                case 2:
                    deletarCliente();
                    this.aperteEnterContinuar();
                    break;
                case 3:
                    alterarCliente();
                    this.aperteEnterContinuar();
                    break;
                case 4:
                    exibirTodosClientes();
                    this.aperteEnterContinuar();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    public void cadastrarCliente() {
        System.out.println("Digite o CPF do Cliente a ser cadastrado:");
        String cpf = lerCpfValido();
        if (clienteCtrl.buscarCliente(cpf) == null) {
            System.out.println("Digite o nome do Cliente a ser cadastrado:");
            String nome = lerString();
            if (clienteCtrl.cadastrarCliente(cpf, nome, 0.0f)) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar esse Cliente!");
            }
        } else {
            System.out.println("Já existe um Cliente cadastrado com esse CPF!");
        }
    }

    public void deletarCliente() {
        System.out.println("Digite o CPF do Cliente cujo cadastro será deletado:");
        String cpf = lerCpfValido();
        if (clienteCtrl.deletarCliente(cpf)) {
            System.out.println("Cadastro de Cliente deletado com sucesso!");
        } else {
            System.out.println("Não foi possível deletar o cadastro desse Cliente,\n"
                    + " verifique se o CPF foi digitado corretamente.");
        }
    }

    public void alterarCliente() {
        System.out.println("Digite o cpf do Cliente a ser alterado:");
        String cpf = lerCpfValido();
        Cliente c;
        if ((c = clienteCtrl.buscarCliente(cpf)) == null) {
            System.out.println("Cliente não encontrado!");
        } else {
            System.out.println("--------------");
            System.out.print(c);
            System.out.println("--------------");
            System.out.println("Digite o novo nome do Cliente:");
            String nome = lerString();
            if (clienteCtrl.alterarCliente(cpf, nome)) {
                System.out.println("Cliente alterado com sucesso!");
            } else {
                System.out.println("Não foi possível alterar esse Cliente!");
            }
        }
    }

    public void exibirTodosClientes() {
        System.out.print(clienteCtrl.recuperarTodosClientesComoString());
        System.out.println("----------------------");
    }

}
