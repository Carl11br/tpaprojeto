package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;
import static com.mycompany.tpaprojeto.view.ViewTools.compraCtrl;
import static com.mycompany.tpaprojeto.view.ViewTools.itemCtrl;
import static com.mycompany.tpaprojeto.view.ViewTools.ler;
import static com.mycompany.tpaprojeto.view.ViewTools.op;
import static com.mycompany.tpaprojeto.view.ViewTools.produtoCtrl;

public class CompraView extends ViewTools {
       
        
        public void menuCompra() {
        clearConsole();
        op = 0;
        Caixa caixa = associarCaixa();
        if (caixa == null) {
            aperteEnterContinuar();
            return;
        }
        Cliente cliente = associarCliente();
        aperteEnterContinuar();
        Compra compra = compraCtrl.iniciarCompra(cliente, caixa);
        boolean flag = true;
        while (flag) {
            clearConsole();
            System.out.println("----------------Menu Compra----------------");
            this.exibirTodosItensCompra(compra);
            System.out.println("TOTAL: R$ " + compra.getTotalComoString());
            System.out.println("1-Adicionar item");
            System.out.println("2-Remover item");
            System.out.println("3-Concluir compra");
            System.out.println("4-Cancelar Compra");
            System.out.println("n-Voltar(somente se não tiver nenhum item na compra)");
            op = lerInt();
            switch (op) {
                case 1:
                    this.adicionarItem(compra);
                    aperteEnterContinuar();
                    break;
                case 2:
                    if (gerenteVw.autenticarGerente()) {
                        this.removerItem(compra);
                        aperteEnterContinuar();
                    }
                    break;
                case 3:
                    if (compraCtrl.verificarCompraVazia(compra)) {
                        System.out.println("Não é possível concluir uma compra que não contenha itens!");
                        this.aperteEnterContinuar();

                    } else {
                        this.aplicarDesconto(compra);
                        System.out.println("TOTAL: R$ " + compra.getTotalComoString());
                        flag = !this.concluirCompra(compra, cliente);
                        flag = false;
                    }

                    break;
                case 4:
                    flag = !cancelarCompra();
                    break;
                default:
                    if (compraCtrl.verificarCompraVazia(compra)) {
                        flag = false;
                    } else {
                        System.out.println("Não é possível voltar quando existem itens na compra!\n"
                                + "Conclua ou cancele a compra!");
                        this.aperteEnterContinuar();
                    }
                    break;
            }
        }
    }

    public boolean cancelarCompra() {
        if (gerenteVw.autenticarGerente()) {

            System.out.println("Compra cancelada com sucesso!");
            this.aperteEnterContinuar();
            return true;
        }
        return false;
    }

    public void aplicarDesconto(Compra c) {
        if (compraCtrl.aplicaDescontoCompra(c)) {
            System.out.println("Parabéns! Você recebeu um desconto de " + c.getDescontoRecebido() + "%!");
        }

    }

  
    private boolean concluirCompra(Compra compra, Cliente cliente) {
        System.out.println("Deseja mesmo concluir a compra?\n1-Sim\n2 ou outro nº-Não");
        int escolha = lerInt();
        if(escolha == 1)
        {
        if (compraCtrl.finalizarCompra(compra, cliente)) {
            System.out.println("Compra concluída com sucesso!");
            this.aperteEnterContinuar();
            return true;
        } else {
            System.out.println("Não foi possível concluir a compra!");
            
        }
        }
        this.aperteEnterContinuar();
        return false;

    }

    public void adicionarItem(Compra compra) {
        System.out.println("Digite o código do produto:");
        int cod = lerInt();
        Produto p = produtoCtrl.buscarProduto(cod);
        if (p == null) {
            System.out.println("Não existe um produto cadastrado com esse código!");
        } else {
            System.out.println("Digite a quantidade de items ou o peso em quilo:");
            float qtd = lerFloatPositivo();
            Item i = itemCtrl.criarItem(p, qtd);
            if (i == null) {
                System.out.println("A quantidade deve ser um número positivo!");
            } else {
                compraCtrl.adicionarItemACompra(i, compra);
            }
        }
    }

    public void removerItem(Compra compra) {
        if (compra.getItens().size() != 0) {
            System.out.println("Digite o código do produto que deseja remover da compra:");
            int cod = ler.nextInt();
            Produto p = produtoCtrl.buscarProduto(cod);
            if (p == null) {
                System.out.println("Não existe um produto cadastrado com esse código!");
            } else {
                if (compraCtrl.removerItemDaCompra(cod, compra)) {
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

    public void exibirTodosItensCompra(Compra compra) {
        System.out.print(compraCtrl.recuperarTodosItensDaCompraComoString(compra));
        System.out.println("----------------------");
    }
        public void menuRelatoriosCompras() {
        boolean flag = true;
        System.out.println("------------------Menu Relatórios de Compras------------------");
        while (flag) {
            System.out.println("1-Exibir todas as compras");
            System.out.println("2-Excluir n compras mais antigas");
            System.out.println("n-Digite outro número para sair o menu de Clientes");
            op = lerInt();
            switch (op) {
                case 1:
                    this.exibirTodasCompras();
                    this.aperteEnterContinuar();
                    break;
                case 2:
                    this.deletarComprasAntigas();
                    this.aperteEnterContinuar();
                    break;
                case 3:
                    descontoVw.exibirTodosDescontos();
                    this.aperteEnterContinuar();
                    break;
                default:
                    flag = false;
                    break;
            }
        }

    }

    public void deletarComprasAntigas() {
        System.out.println("Digite quantos registros de compras antigos você deseja excluir:");
        int n = lerIntPositivo();
        int deletados = compraCtrl.deletarComprasMaisAntigas(n);
        System.out.println(String.format("Foram deletados %d registros de compras", deletados));
    }

    public void exibirTodasCompras() {
        System.out.print(compraCtrl.recuperarTodasComprasComoString());
        System.out.println("----------------------");
    }
    public Cliente associarCliente() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = lerCpfValido();
        String nome;
        Cliente c = clienteCtrl.buscarCliente(cpf);
        if (c == null) {
            System.out.println("Digite o nome do cliente:");
            nome = ler.nextLine();
            if (clienteCtrl.cadastrarCliente(cpf, nome, 0.0f)) {
                System.out.println("Cliente cadastrado com sucesso!");
            }

        }
        c = clienteCtrl.buscarCliente(cpf);
        clearConsole();
        System.out.println("Seja bem-vindo(a), " + c.getNome() + "!");
        return c;
    }

    public Caixa associarCaixa() {
        System.out.println("Digite a matrícula do caixa ou 0 para voltar ao menu anterior:");
        int mat = lerIntPositivoEZero();
        if(mat == 0)
            return null;
        Caixa c = caixaCtrl.buscarCaixa(mat);
        if (c == null) {
            System.out.println("Não foi encontrado um(a) Caixa com essa matrícula!");
        } else {
            System.out.println("Seja bem-vindo(a), operador(a) de caixa " + c.getNome() + "!");
        }
        return c;

    }
}
