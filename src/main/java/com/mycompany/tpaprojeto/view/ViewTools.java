package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.controller.CaixaController;
import com.mycompany.tpaprojeto.controller.ClienteController;
import com.mycompany.tpaprojeto.controller.CompraController;
import com.mycompany.tpaprojeto.controller.DescontoController;
import com.mycompany.tpaprojeto.controller.GerenteController;
import com.mycompany.tpaprojeto.controller.ItemController;
import com.mycompany.tpaprojeto.controller.ProdutoController;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewTools {

    protected static final DescontoController descontoCtrl = new DescontoController();
    protected static final CompraController compraCtrl = new CompraController();
    protected static final ItemController itemCtrl = new ItemController();
    protected static final CaixaController caixaCtrl = new CaixaController();
    protected static final GerenteController gerenteCtrl = new GerenteController();
    protected static final ClienteController clienteCtrl = new ClienteController();
    protected static final ProdutoController produtoCtrl = new ProdutoController();
    protected static final ProdutoView produtoVw = new ProdutoView();
    protected static final CaixaView caixaVw = new CaixaView();
    protected static final ClienteView clienteVw = new ClienteView();
    protected static final CompraView compraVw = new CompraView();
    protected static final DescontoView descontoVw = new DescontoView();
    protected static final GerenteView gerenteVw = new GerenteView();
    protected static Scanner ler = new Scanner(System.in);
    protected static int op = 0;

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

    public int lerIntPositivo() {
        int num;
        boolean flag = true;
        do {
            num = lerInt();
            if (num <= 0) {
                System.out.println("Digite um número inteiro maior que 0:");

            } else {
                flag = false;
            }
        } while (flag);
        return num;
    }

    public int lerIntPositivoEZero() {
        int num;
        boolean flag = true;
        do {
            num = lerInt();
            if (num < 0) {
                System.out.println("Digite um número inteiro maior ou igual a 0:");

            } else {
                flag = false;
            }
        } while (flag);
        return num;
    }

    public int lerDesconto() {
        int desc;
        boolean flag = true;
        do {
            desc = lerInt();
            if ((desc < 1 || desc > 100)) {
                System.out.println("Digite um número entre 1 e 100:");

            } else {
                flag = false;
            }
        } while (flag);
        return desc;
    }

    public float lerFloat() {
        float num = 0;
        boolean flag = true;
        while (flag) {
            try {
                num = Float.parseFloat(ler.nextLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas um número reais (Ex.: 00.00):");
            }
        }
        return num;
    }

    public float lerFloatPositivo() {
        float num;
        boolean flag = true;
        do {
            num = lerFloat();
            if (num <= 0.0) {
                System.out.println("Digite um número real maior que 00.00:");

            } else {
                flag = false;
            }
        } while (flag);
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
                System.out.println("Digite um CPF válido:");
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
        clearConsole();
        

    }

    public String lerSenha() {
        return ler.nextLine().replaceAll("[\\n]", "");
    }

    public final static void clearConsole() {
       try {  
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
