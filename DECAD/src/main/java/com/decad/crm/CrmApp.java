package com.decad.crm;

import com.decad.crm.util.ConectorBancoDeDados;
import com.decad.crm.view.MenuPrincipal;
import com.decad.crm.service.AutenticacaoService;
import com.decad.crm.view.MenuAgenda;
import java.util.Scanner;

public class CrmApp {
    public static void main(String[] args) {
        AutenticacaoService autenticacaoService = new AutenticacaoService();
        Scanner scanner = new Scanner(System.in);

        boolean logado = false;
        while (!logado) {
            System.out.print("Digite o login: ");
            String login = scanner.nextLine();
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            if (autenticacaoService.login(login, senha)) {
                System.out.println("Login bem-sucedido!");
                logado = true;
            } else {
                System.out.println("Login ou senha incorretos. Tente novamente.");
            }
        }

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
        scanner.close();
    }
}
