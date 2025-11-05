package com.decad.crm.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner scanner;

    public MenuPrincipal(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenuPrincipal() {
        int escolha = -1;

        while (true) {
            System.out.println("\n=======================");
            System.out.println("   DECAD CRM - MVP");
            System.out.println("=======================");
            System.out.println("1. Gerenciar Pacientes");
            System.out.println("2. Gerenciar Profissionais");
            System.out.println("3. Consultar Agenda");
            System.out.println("0. Sair do Sistema");
            System.out.println("=======================");
            System.out.print("Digite sua opção: ");

            try {
                escolha = Integer.parseInt(scanner.nextLine());

                if (escolha >= 0 && escolha <= 3) {
                    return escolha;
                } else {
                    System.err.println("Opção inválida! Por favor, escolha um número entre 0 e 3.");
                }

            } catch (NumberFormatException e) {
                System.err.println("Erro: Você deve digitar um NÚMERO, não letras ou símbolos.");
            }
        }
    }
}