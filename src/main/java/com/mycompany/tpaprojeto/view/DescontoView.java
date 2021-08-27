package com.mycompany.tpaprojeto.view;

public class DescontoView extends ViewTools {

    public void menuCadastroDesconto() {
        boolean flag = true;
        System.out.println("------------------Menu Cadastro de Descontos------------------");
        while (flag) {
            System.out.println("1-Cadastrar Desconto");
            System.out.println("2-Excluir cadastro de um Desconto");
            System.out.println("3-Exibir todos os Descontos cadastrados");
            System.out.println("n-Digite outro número para sair do menu de Descontos");
            op = lerInt();
            switch (op) {
                case 1:
                    cadastrarDesconto();
                    this.aperteEnterContinuar();
                    break;
                case 2:
                    deletarDesconto();
                    this.aperteEnterContinuar();
                    break;
                case 3:
                    exibirTodosDescontos();
                    this.aperteEnterContinuar();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    public void cadastrarDesconto() {
        System.out.println("Digite um número de 1 a 100, para o desconto que você deseja dar:");
        int desc = lerDesconto();
        if (descontoCtrl.buscarDesconto(desc) == null) {
            System.out.println("Digite o valor mínimo de total de compras acumuladas em reais para o desconto ser aplicado:");
            float valorMin = lerFloat();
            if (descontoCtrl.cadastrarDesconto(desc, valorMin)) {
                System.out.println("Desconto cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar esse desconto!");
            }
        } else {
            System.out.println("Um desconto com essa porcentagem já está cadastrado!");
        }
    }

    public void deletarDesconto() {
        System.out.println("Digite um número de 1 a 100, para o desconto que você deseja deletar:");
        int desc = lerDesconto();
        if (descontoCtrl.deletarDesconto(desc)) {
            System.out.println("Desconto deletado com sucesso!");
        } else {
            System.out.println("Não foi possível deletar esse desconto,\n"
                    + " verifique se ele realmente está cadastrado!.");
        }
    }

    public void exibirTodosDescontos() {
        System.out.print(descontoCtrl.recuperarTodosDescontosComoString());
        System.out.println("----------------------");
    }
}
