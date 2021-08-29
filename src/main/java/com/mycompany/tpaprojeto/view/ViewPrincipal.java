package com.mycompany.tpaprojeto.view;

import java.util.Locale;

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
                    clearConsole();
                    break;
                case 2:
                    if (gerenteVw.autenticarGerente()) {
                        gerenteVw.menuGerente();
                        clearConsole();
                    }
                    break;
                default:
                    encerrar = true;
                    break;
            }
        }
    }

   

   
   
    

    

    

    

   
   
    

  


}
