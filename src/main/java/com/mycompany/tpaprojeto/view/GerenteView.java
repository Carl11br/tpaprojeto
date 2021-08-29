package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.model.Gerente;

public class GerenteView extends ViewTools {

    public void menuGerente() {
        boolean flag = true;
        while (flag) {
            clearConsole();
            System.out.println("------------------Acesso do Gerente------------------");
            System.out.println("1-Acessar menu Produto");
            System.out.println("2-Acessar menu Caixa");
            System.out.println("3-Acessar menu Gerente");
            System.out.println("4-Acessar menu Cliente");
            System.out.println("5-Acessar menu de Relátorios de Compras");
            System.out.println("6-Acessar menu Desconto");
            System.out.println("n-Digite outro número para sair do menu do Gerente");
            op = lerInt();
            switch (op) {
                case 1:
                    clearConsole();
                    produtoVw.menuProduto();
                    break;
                case 2:
                    clearConsole();
                    caixaVw.menuCadastroCaixa();
                    break;
                case 3:
                    clearConsole();
                    gerenteVw.menuCadastroGerente();
                    break;
                case 4:
                    clearConsole();
                    clienteVw.menuCadastroCliente();
                    break;
                case 5:
                    clearConsole();
                    compraVw.menuRelatoriosCompras();
                    break;
                case 6:
                    clearConsole();
                    descontoVw.menuCadastroDesconto();
                    break;

                default:
                    flag = false;
                    break;
            }
        }
    }

    public void menuCadastroGerente() {
        boolean flag = true;

        System.out.println("------------------Menu Cadastro de Gerentes------------------");
        while (flag) {
            System.out.println("1-Cadastrar Gerente");
            System.out.println("2-Excluir cadastro de um Gerente");
            System.out.println("3-Alterar cadastro de um Gerente");
            System.out.println("4-Exibir todos os Gerentes cadastrados");
            System.out.println("n-Digite outro número para sair do menu de cadastro de Gerentes");
            op = lerInt();
            switch (op) {
                case 1:
                    clearConsole();
                    cadastrarGerente();
                    this.aperteEnterContinuar();
                    break;
                case 2:
                    clearConsole();
                    deletarGerente();
                    this.aperteEnterContinuar();
                    break;
                case 3:
                    clearConsole();
                    this.alterarGerente();
                    this.aperteEnterContinuar();
                    break;
                case 4:
                    clearConsole();
                    exibirTodosGerentes();
                    this.aperteEnterContinuar();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    public void cadastrarGerente() {
        System.out.println("Digite a matrícula do Gerente a ser cadastrado:");
        int mat = lerIntPositivo();
        if (gerenteCtrl.buscarGerente(mat) == null) {
            System.out.println("Digite o nome do Gerente a ser cadastrado:");
            String nome = lerString();
            String senha = cadastrarSenha();
            if (gerenteCtrl.cadastrarGerente(mat, nome, senha)) {
                System.out.println("Gerente cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar esse Gerente!");
            }
        } else {
            System.out.println("Já existe um Gerente cadastrado com essa matrícula!");
        }
    }

    public void deletarGerente() {
        System.out.println("Digite o matrícula do Gerente cujo cadastro será deletado ou 0 para voltar:");
        int mat = lerIntPositivoEZero();
        if(mat == 0)
        {
            clearConsole();
            return;
        }
        int conseguiuDeletar = gerenteCtrl.deletarGerente(mat);
        switch (conseguiuDeletar) {
            case 1:
                System.out.println("Cadastro de Gerente deletado com sucesso!");
                break;
            case 0:
                System.out.println("Não foi possível deletar o cadastro desse Gerente,\n"
                        + " verifique se a matrícula foi digitada corretamente.");
                break;
            default:
                System.out.println("Não é possível deletar o cadastro do Gerente Padrão!");
                break;
        }
    }

    public void alterarGerente() {
        System.out.println("Digite a matrícula do Gerente cujo cadastro será alterado ou 0 para voltar:");
        int mat = lerIntPositivoEZero();
         if(mat == 0)
        {
            clearConsole();
            return;
        }
        Gerente g = gerenteCtrl.buscarGerente(mat);
        if (g == null) {
            System.out.println("Gerente não encontrado!");
        } else if (gerenteCtrl.verificarGerentePadrao(g)) {
            System.out.println("Não é permitido alterar o Gerente Padrão!");
        } else {
            System.out.println("--------------");
            System.out.print(g.toString());
            System.out.println("--------------");
            System.out.println("Digite o novo nome do Gerente:");
            String nome = lerString();
            System.out.println("Deseja alterar a senha desse gerente também? 1-Sim 2-Não");
            int escolha = lerInt();
            switch (escolha) {
                case 1:
                    if (autenticarGerenteAlteracao(g)) {
                        String nova_senha = cadastrarSenhaAlteracao();
                        if ((gerenteCtrl.alterarGerente(mat, nome, nova_senha)) == 1) {
                            System.out.println("Gerente alterado com sucesso!");
                        } else {
                            System.out.println("Não foi possível alterar esse Gerente!");
                        }
                    } else {
                        if ((gerenteCtrl.alterarGerente(mat, nome, g.getSenha())) == 1) {
                            System.out.println("Somente a o nome foi alterado!");
                        } else {
                            System.out.println("Não foi possível alterar esse Gerente!");
                        }

                    }
                    break;
                default:
                    if ((gerenteCtrl.alterarGerente(mat, nome, g.getSenha())) == 1) {
                        System.out.println("Gerente alterado com sucesso!");
                    } else {
                        System.out.println("Não foi possível alterar esse Gerente!");
                    }
                    break;
            }

        }

    }

    public void exibirTodosGerentes() {
        System.out.print(gerenteCtrl.recuperarTodosGerentesComoString());
        System.out.println("----------------------");
    }

    public boolean autenticarGerente() {
        System.out.println("Gerente, digite sua matrícula ou 0 para voltar ao menu anterior:");
        int mat = lerIntPositivoEZero();
        if(mat==0)
        {
            clearConsole();
            return false;
        }
        String senha;
        System.out.println("Gerente, digite sua senha:");
        senha = lerSenha();
        boolean b = gerenteCtrl.autenticarGerente(mat, senha);
        if (b == false) {
            System.out.println("Matrícula e/ou senha errados!");
            this.aperteEnterContinuar();
        }

        return b;
    }

    public boolean autenticarGerenteAlteracao(Gerente g) {
        String senha;
        System.out.println("Digite a senha do gerente a ser alterado:");
        senha = lerSenha();
        boolean b = gerenteCtrl.autenticarGerente(g.getMatricula(), senha);
        if (b == false) {
            System.out.println("Senha errada!");
        }

        return b;
    }

    public String cadastrarSenha() {
        String senha1 = "senha1", senha2 = "senha2";
        while (!senha1.equals(senha2)) {
            System.out.println("Digite a senha a ser cadastrada:");
            senha1 = lerSenha();
            System.out.println("Digite novamente a senha ser cadastrada:");
            senha2 = lerSenha();
            if (!senha1.equals(senha2)) {
                System.out.println("As senhas digitadas não coencidem!");
            }
        }
        return senha2;
    }

    public String cadastrarSenhaAlteracao() {
        String senha1 = "senha1", senha2 = "senha2";
        while (!senha1.equals(senha2)) {
            System.out.println("Digite a nova senha:");
            senha1 = lerSenha();
            System.out.println("Digite novamente a nova senha:");
            senha2 = lerSenha();
            if (!senha1.equals(senha2)) {
                System.out.println("As senhas digitadas não coencidem!");
            }
        }
        return senha2;
    }

}
