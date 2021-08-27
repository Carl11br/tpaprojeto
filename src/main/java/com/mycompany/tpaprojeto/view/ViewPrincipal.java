package com.mycompany.tpaprojeto.view;

import com.mycompany.tpaprojeto.controller.CaixaController;
import com.mycompany.tpaprojeto.controller.ClienteController;
import com.mycompany.tpaprojeto.controller.CompraController;
import com.mycompany.tpaprojeto.controller.DescontoController;
import com.mycompany.tpaprojeto.controller.GerenteController;
import com.mycompany.tpaprojeto.controller.ItemController;
import com.mycompany.tpaprojeto.controller.ProdutoController;
import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Cliente;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Gerente;
import com.mycompany.tpaprojeto.model.Item;
import com.mycompany.tpaprojeto.model.Produto;

public class ViewPrincipal extends ViewTools {
    public void menuPrincipal() {
        boolean encerrar = false;
        while (encerrar == false) {
            System.out.println("------------------Sistema Gerenciador de Mercados------------------");
            System.out.println("1-Iniciar compra");
            System.out.println("2-Acesso do gerente");
            System.out.println("n-Digite qualquer outro número para encerrar");
            op = lerInt();
            switch (op) {
                case 1:
                    compraVw.menuCompra();
                    System.out.print("\f");
                    break;
                case 2:
                    if (gerenteVw.autenticarGerente()) {
                        gerenteVw.menuGerente();
                        System.out.print("\f");
                    }
                    break;
                default:
                    encerrar = true;
                    break;
            }
        }
    }

   

   
   
    

    

    

    

   
   
    

  


}
