package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.controller.Controller;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View {

    Scanner ler = new Scanner(System.in);
    int op = 0;
    Controller ctr = new Controller();

    public int lerInt() {
        int num = 0;
        boolean flag = true;
        while (flag) {
            try {
                num = Integer.parseInt(ler.nextLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas um número que seja inteiro:");
            }
        }
        return num;
    }

    public float lerFloat() {
        float num = 0;
        boolean flag = true;
        while (flag) {
            try {
                num = Float.parseFloat(ler.nextLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas um número que seja real (Ex.: 00.00):");
            }
        }
        return num;
    }

    public void aperteEnterContinuar() {
        System.out.println("Aperte ENTER para continuar ...");
        ler.nextLine();
        //Clears Screen in java
        try {

            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }

        } catch (IOException | InterruptedException ex) {
        }
        System.out.print("\f"); 

    }

    public void menuPrincipal() {

        System.out.println("1-Iniciar compra");
        System.out.println("2-Acesso do gerente");
        System.out.println("3-Encerrar");
        op = lerInt();
        switch (op) {
            case 1:
                this.menuCompra(this.associarCliente());
                break;
            default:
                break;
        }
    }

    public void menuCompra(Cliente cliente) {
        op = 0;
        Compra compra = ctr.iniciarCompra(cliente);
        while (op != 3 && op != 4) {
            System.out.println("1-Adicionar item");
            System.out.println("2-Remover item");
            System.out.println("3-Concluir compra");
            System.out.println("4-Cancelar Compra");
            op = lerInt();
            switch (op) {
                case 1:
                    this.adicionarItem(compra);

                    break;
                default:
                    break;
            }
        }
    }

    public void adicionarItem(Compra compra) {
        System.out.println("Digite o código do produto:");
        int cod = ler.nextInt();
        System.out.println("Digite a quantidade de items:");
        int qtd = ler.nextInt();
        Item i = ctr.criarItem(cod, qtd);
        //Produto p = BuscarProduto(cod);
        ctr.adicionarItemACompra(i, compra);
    }

    public void menuGerente() {
        System.out.println("1-Acessar menu Produto");
        System.out.println("2-Acessar menu Caixa/Gerente");
        op = ler.nextInt();
        switch (op) {
            case 1:
                break;
            default:
                break;
        }

    }

    public void menuProduto() {
        boolean flag = true;
        System.out.println("------------------Menu Produtos------------------");
        while (flag) {
            System.out.println("1-Cadastrar Produto");
            System.out.println("2-Alterar Produto");
            System.out.println("3-Excluir Produto");
            System.out.println("4-Exibir todos os produtos cadastrados");
            System.out.println("n-Digite outro número para sair do menu de produtos");
            op = lerInt();
            switch (op) {
                case 1:
                    cadastrarProduto();
                    this.aperteEnterContinuar();
                    break;
                case 2:
                    alterarProduto();
                    this.aperteEnterContinuar();
                    break;
                case 3:
                    deletarProduto();
                    this.aperteEnterContinuar();
                    break;
                case 4:
                    exibirTodosProdutos();
                    this.aperteEnterContinuar();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    public void cadastrarProduto() {
        System.out.println("Digite o código do produto a ser cadastrado:");
        int cod = lerInt();
        if (ctr.buscarProduto(cod) == null) {
            System.out.println("Digite o nome do produto a ser cadastrado:");
            String nome = ler.nextLine();
            System.out.println("Digite o preço do produto a ser cadastrado:");
            float preco = lerFloat();
            if (ctr.cadastrarProduto(cod, nome, preco)) {
                System.out.println("Produto cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar esse produto!");
            }
        } else {
            System.out.println("Já existe um produto cadastrado com esse código!");
        }
    }

    public void alterarProduto() {
        System.out.println("Digite o código do produto a ser alterado:");
        int cod = lerInt();
        Produto p;
        if ((p = ctr.buscarProduto(cod)) == null) {
            System.out.println("Produto não encontrado!");
        } else {
            System.out.println("--------------");
            System.out.print(p.toString());
            System.out.println("--------------");
            System.out.println("Digite o novo nome do produto:");
            String nome = ler.nextLine();
            System.out.println("Digite o novo preço do produto:");
            float preco = lerFloat();

            if (ctr.deletarProduto(cod) && ctr.cadastrarProduto(cod, nome, preco)) {
                System.out.println("Produto alterado com sucesso!");
            } else {
                System.out.println("Não foi possível alterar esse produto!");
            }
        }
    }

    public void deletarProduto() {
        System.out.println("Digite o código do produto a ser deletado:");
        int cod = lerInt();
        if (ctr.deletarProduto(cod)) {
            System.out.println("Produto deletado com sucesso!");
        } else {
            System.out.println("Não foi possível deletar esse produto!");
        }
    }

    public void exibirTodosProdutos() {
        System.out.print(ctr.recuperarTodosProdutosComoString());
        System.out.println("----------------------");
    }

    public Cliente associarCliente() {
        System.out.println("Digite o cpf do cliente:");
        int cpf = lerInt();
        //verificar se cliente é cadastrado, recuperar ele e associar a compra
        return ctr.cadastrarCliente(cpf);
    }
    
    public void menuCadastroCaixa() {
        boolean flag = true;
        System.out.println("------------------Menu Cadastro de Caixas------------------");
        while (flag) {
            System.out.println("1-Cadastrar Caixa");
            System.out.println("2-Excluir cadastro de um Caixa");
            System.out.println("3-Exibir todos os Caixas cadastrados");
            System.out.println("n-Digite outro número para sair do menu de produtos");
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
        int mat = lerInt();
        if (ctr.buscarCaixa(mat) == null) {
            System.out.println("Digite o nome do Caixa a ser cadastrado:");
            String nome = ler.nextLine();
            if (ctr.cadastrarCaixa(mat, nome)) {
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
        int mat = lerInt();
        if (ctr.deletarCaixa(mat)) {
            System.out.println("Cadastro de Caixa deletado com sucesso!");
        } else {
            System.out.println("Não foi possível deletar o cadastro desse Caixa,\n"
                    + " verifique se a matrícula foi digitada corretamente.");
        }
    }
     public void exibirTodosCaixas() {
        System.out.print(ctr.recuperarTodosCaixasComoString());
        System.out.println("----------------------");
    }

}
