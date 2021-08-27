package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.model.Caixa;


public class CaixaView extends ViewTools {

    public void menuCadastroCaixa() {
        boolean flag = true;
        while (flag) {
            System.out.println("------------------Menu Cadastro de Caixas------------------");
            System.out.println("1-Cadastrar Caixa");
            System.out.println("2-Excluir cadastro de um Caixa");
            System.out.println("3-Alterar cadastro de um Caixa");
            System.out.println("4-Exibir todos os Caixas cadastrados");
            System.out.println("n-Digite outro número para sair do menu de Caixas");
            op = lerInt();
            switch (op) {
                case 1:
                    cadastrarCaixa();
                    this.aperteEnterContinuar();
                    break;
                case 2:
                    deletarCaixa();
                    this.aperteEnterContinuar();
                    break;
                case 3:
                    this.alterarCaixa();
                    this.aperteEnterContinuar();
                    break;
                case 4:
                    exibirTodosCaixas();
                    this.aperteEnterContinuar();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    public void cadastrarCaixa() {
        System.out.println("Digite a matrícula do Caixa a ser cadastrado:");
        int mat = lerIntPositivo();
        if (caixaCtrl.buscarCaixa(mat) == null) {
            System.out.println("Digite o nome do Caixa a ser cadastrado:");
            String nome = ler.nextLine();
            if (caixaCtrl.cadastrarCaixa(mat, nome)) {
                System.out.println("Caixa cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar esse Caixa!");
            }
        } else {
            System.out.println("Já existe um Caixa cadastrado com essa matrícula!");
        }
    }

    public void deletarCaixa() {
        System.out.println("Digite o matrícula do Caixa cujo cadastro será deletado:");
        int mat = lerIntPositivo();
        if (caixaCtrl.deletarCaixa(mat)) {
            System.out.println("Cadastro de Caixa deletado com sucesso!");
        } else {
            System.out.println("Não foi possível deletar o cadastro desse Caixa,\n"
                    + " verifique se a matrícula foi digitada corretamente.");
        }
    }

    public void alterarCaixa() {
        System.out.println("Digite a matrícula do Caixa a ser alterado:");
        int mat = lerIntPositivo();
        Caixa c;
        if ((c = caixaCtrl.buscarCaixa(mat)) == null) {
            System.out.println("Caixa não encontrado!");
        } else {
            System.out.println("--------------");
            System.out.print(c.toString());
            System.out.println("--------------");
            System.out.println("Digite o novo nome do Caixa:");
            String nome = ler.nextLine();

            if (caixaCtrl.alterarCaixa(mat, nome)) {
                System.out.println("Caixa alterado com sucesso!");
            } else {
                System.out.println("Não foi possível alterar esse Caixa!");
            }
        }
    }

    public void exibirTodosCaixas() {
        System.out.print(caixaCtrl.recuperarTodosCaixasComoString());
        System.out.println("----------------------");
    }
}
