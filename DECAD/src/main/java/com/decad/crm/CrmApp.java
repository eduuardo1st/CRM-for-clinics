package com.decad.crm;

import com.decad.crm.util.ConectorBancoDeDados;
import com.decad.crm.view.MenuPrincipal;

public class CrmApp {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        boolean executando = true;

        while (executando) {

            int escolha = menuPrincipal.mostrarMenuPrincipal();

            switch (escolha) {
                case 1:
                    System.out.println("-> Você escolheu: GERENCIAR PACIENTES");
                    // Em desnvolvimento
                    break;
                case 2:
                    System.out.println("-> Você escolheu: GERENCIAR PROFISSIONAIS");
                    // Em desnvolvimento
                    break;
                case 3:
                    System.out.println("-> Você escolheu: GERENCIAR AGENDA");
                    // Em desnvolvimento
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    executando = false;
                    break;
                default:

                    System.err.println("Opção inesperada.");
                    break;
            }
        }

        menuPrincipal.fecharScanner();
    }
}
