package com.decad.crm.view;

import java.util.Scanner;

/**
 * Classe responsável por exibir o menu principal do sistema CRM
 * e capturar a escolha do usuário.
 * 
 * @author Sistema CRM
 * @version 1.0
 */
public class MenuPrincipal {
    
    private Scanner scanner;
    
    /**
     * Construtor da classe MenuPrincipal
     */
    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Exibe o menu principal com as opções disponíveis
     * e retorna a escolha do usuário.
     * 
     * @return int - Opção escolhida pelo usuário
     */
    public int exibirMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           SISTEMA CRM - MENU PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Pacientes");
        System.out.println("2. Profissionais");
        System.out.println("3. Agenda");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Digite sua opção: ");
        
        int opcao = -1;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= 0 && opcao <= 3) {
                    entradaValida = true;
                } else {
                    System.out.print("Opção inválida! Digite um número entre 0 e 3: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite um número: ");
            }
        }
        
        return opcao;
    }
    
    /**
     * Exibe uma mensagem de boas-vindas
     */
    public void exibirBoasVindas() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        BEM-VINDO AO SISTEMA CRM");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Exibe uma mensagem de despedida
     */
    public void exibirDespedida() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        OBRIGADO POR USAR O SISTEMA CRM");
        System.out.println("                    ATÉ LOGO!");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Exibe uma mensagem de erro genérica
     * 
     * @param mensagem - Mensagem de erro a ser exibida
     */
    public void exibirErro(String mensagem) {
        System.out.println("\n❌ ERRO: " + mensagem);
    }
    
    /**
     * Exibe uma mensagem de sucesso
     * 
     * @param mensagem - Mensagem de sucesso a ser exibida
     */
    public void exibirSucesso(String mensagem) {
        System.out.println("\n✅ " + mensagem);
    }
    
    /**
     * Fecha o scanner para liberar recursos
     */
    public void fechar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
