package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.controller.Controller;
import com.mycompany.tpaprojeto.model.Produto;
import java.util.Scanner;

public class View {

    Scanner ler = new Scanner(System.in);
    int op = 0;
    Controller ctr = new Controller();

    public void menuPrincipal() {

        System.out.println("1-Iniciar compra");
        System.out.println("2-Acesso do gerente");
        op = ler.nextInt();
        switch (op) {
            case 1:
                break;
            default:
                break;
        }
    }

    public void menuCompra() {
        System.out.println("1-Adicionar item");
        System.out.println("2-Remover item");
        System.out.println("3-Concluir compra");
        System.out.println("4-Cancelar Compra");
    }

    public void adicionarItem() {
        System.out.println("Digite o código do produto:");
        int cod = ler.nextInt();
        System.out.println("Digite a quantidade de items:");
        int qtd = ler.nextInt();
        //Produto p = BuscarProduto(cod);
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
        System.out.println("1-Cadastrar Produto");
        System.out.println("2-Alterar Produto");
        System.out.println("3-Excluir Produto");
        op = ler.nextInt();
        switch (op) {
            case 1:
                break;
            default:
                break;
        }
    }
    public void cadastrarProduto()
    {
        System.out.println("Digite o código do produto a ser cadastrado:");
        int cod = ler.nextInt();
        System.out.println("Digite o nome do produto a ser cadastrado:");
        String nome = ler.next();
        System.out.println("Digite o preço do produto a ser cadastrado:");
        float preco = ler.nextFloat();
        ctr.cadastrarProduto(cod,nome,preco);
           
    }

}
