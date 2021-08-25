package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.controller.Controller;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.Console;
import java.util.InputMismatchException;

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

    public String lerCpfValido() {
        String cpf = "";
        boolean flag = true;
        while (flag) {
            cpf = ler.nextLine().replaceAll("\\D", "");
            if (ValidaCpf(cpf)) {
                flag = false;
            } else {
                System.out.println("Digite um CPF válido!");
            }
        }

        return cpf;
    }
    //Função para validar cpf disponível em
    //https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097

    public static boolean ValidaCpf(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public void aperteEnterContinuar() {
        System.out.println("Aperte ENTER para continuar ...");
        ler.nextLine();
        //Clears Screen in java
        /* try {

            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }

        } catch (IOException | InterruptedException ex) {
        }*/
        System.out.print("\f");

    }

    public void menuPrincipal() {

        System.out.println("1-Iniciar compra");
        System.out.println("2-Acesso do gerente");
        System.out.println("3-Encerrar");
        op = lerInt();
        switch (op) {
            case 1:
                //this.menuCompra();
                break;
            default:
                break;
        }
    }

    public void menuCompra() {
        op = 0;
        Cliente cliente = associarCliente();
        Compra compra = ctr.iniciarCompra(cliente);
        while (op != 3 && op != 4) {
            this.exibirTodosItens(compra);
            System.out.println("TOTAL: R$ "+ compra.getTotal());
            System.out.println("1-Adicionar item");
            System.out.println("2-Remover item");
            System.out.println("3-Concluir compra");
            System.out.println("4-Cancelar Compra");
            op = lerInt();
            switch (op) {
                case 1:
                    this.adicionarItem(compra);
                    break;
                case 2:
                    this.removerItem(compra);
                    break;
                default:
                    break;
            }
        }
    }

    public void adicionarItem(Compra compra) {
        System.out.println("Digite o código do produto:");
        int cod = lerInt();
        Produto p = ctr.buscarProduto(cod);
        if (p == null) {
            System.out.println("Não existe um produto cadastrado com esse código!");
        } else {
            System.out.println("Digite a quantidade de items ou o peso em quilo:");
            float qtd = lerFloat();
            Item i = ctr.criarItem(p, qtd);
            if(i==null)
                System.out.println("A quantidade deve ser um número positivo!");
            else     
            ctr.adicionarItemACompra(i, compra);
        }
    }

    public void removerItem(Compra compra) {
        if (compra.getItens().size() != 0) {
            System.out.println("Digite o código do produto que deseja remover da compra:");
            int cod = ler.nextInt();
            Produto p = ctr.buscarProduto(cod);
            if (p == null) {
                System.out.println("Não existe um produto cadastrado com esse código!");
            } else {
                if (ctr.removerItemDaCompra(cod, compra)) {
                    System.out.println("Item removido com sucesso!");
                } else {
                    System.out.println("Não foi possível remover o item da compra,"
                            + " verifique se o código do produto foi digitado corretamente.");
                }
            }
        } else {
            System.out.println("Não há itens na compra para remover");
        }
    }

    public void exibirTodosItens(Compra compra) {
        System.out.print(ctr.recuperarTodosItensComoString(compra));
        System.out.println("----------------------");
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

    //Criar função p associar cliente a compra
    public Cliente associarCliente() {
        System.out.println("Digite o cpf do cliente:");
        String cpf = lerCpfValido();
        String nome;
        Cliente c = ctr.buscarCliente(cpf);
        if (c == null) {
            System.out.println("Digite o nome do cliente:");
            nome = ler.nextLine();
            if (ctr.cadastrarCliente(cpf, nome)) {
                System.out.println("Cliente cadastrado com sucesso!");
            }

        }
        c = ctr.buscarCliente(cpf);
        System.out.println("Seja bem-vindo(a), " + c.getNome() + "!");
        return c;
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

    public void menuCadastroGerente() {
        boolean flag = true;
        System.out.println("------------------Menu Cadastro de Gerentes------------------");
        while (flag) {
            System.out.println("1-Cadastrar Gerente");
            System.out.println("2-Excluir cadastro de um Gerente");
            System.out.println("3-Exibir todos os Gerentes cadastrados");
            System.out.println("n-Digite outro número para sair do menu de produtos");
            op = lerInt();
            switch (op) {
                case 1:
                    cadastrarGerente();
                    this.aperteEnterContinuar();
                    break;
                case 2:
                    deletarGerente();
                    this.aperteEnterContinuar();
                    break;
                case 3:
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
        int mat = lerInt();
        String senha1 = "senha1", senha2 = "senha2";
        if (ctr.buscarGerente(mat) == null) {
            System.out.println("Digite o nome do Gerente a ser cadastrado:");
            String nome = ler.nextLine();
            while (!senha1.equals(senha2)) {
                System.out.println("Digite a senha do Gerente a ser cadastrado:");
                senha1 = ler.nextLine().replaceAll("[\\n]", "");
                System.out.println("Digite novamente a senha do Gerente a ser cadastrado:");
                senha2 = ler.nextLine().replaceAll("[\\n ]", "");
                if (!senha1.equals(senha2)) {
                    System.out.println("As senhas digitadas não coencidem!");
                }
            }
            if (ctr.cadastrarGerente(mat, nome, senha2)) {
                System.out.println("Gerente cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar esse Gerente!");
            }
        } else {
            System.out.println("Já existe um Gerente cadastrado com essa matrícula!");
        }
    }

    public void deletarGerente() {
        System.out.println("Digite o matrícula do Gerente cujo cadastro será deletado:");
        int mat = lerInt();
        if (ctr.deletarGerente(mat)) {
            System.out.println("Cadastro de Gerente deletado com sucesso!");
        } else {
            System.out.println("Não foi possível deletar o cadastro desse Gerente,\n"
                    + " verifique se a matrícula foi digitada corretamente.");
        }
    }

    public void exibirTodosGerentes() {
        System.out.print(ctr.recuperarTodosGerentesComoString());
        System.out.println("----------------------");
    }

    public void menuCadastroCliente() {
        boolean flag = true;
        System.out.println("------------------Menu Cadastro de Clientes------------------");
        while (flag) {
            System.out.println("1-Cadastrar Cliente");
            System.out.println("2-Excluir cadastro de um Cliente");
            System.out.println("3-Exibir todos os Clientes cadastrados");
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
        if (ctr.buscarCliente(cpf) == null) {
            System.out.println("Digite o nome do Cliente a ser cadastrado:");
            String nome = ler.nextLine();
            if (ctr.cadastrarCliente(cpf, nome)) {
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
        if (ctr.deletarCliente(cpf)) {
            System.out.println("Cadastro de Cliente deletado com sucesso!");
        } else {
            System.out.println("Não foi possível deletar o cadastro desse Cliente,\n"
                    + " verifique se o CPF foi digitado corretamente.");
        }
    }

    public void exibirTodosClientes() {
        System.out.print(ctr.recuperarTodosClientesComoString());
        System.out.println("----------------------");
    }
}
