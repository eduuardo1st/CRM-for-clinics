/*
package com.decad.crm.view;

import java.util.Optional;
import java.util.Scanner;

public abstract class MenuCrudBase {

    protected Scanner scanner;

    public MenuCrudBase(Scanner scanner) {
        this.scanner = scanner;
    }

    protected abstract String getNomeEntidade();
    protected abstract void acaoCadastrar();
    protected abstract void acaoListar();
    protected abstract void acaoAtualizar();
    protected abstract void acaoDeletar();

    public void exibirMenu() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciamento de " + getNomeEntidade() + " ---");
            System.out.println("1. Cadastrar Novo(a)");
            System.out.println("2. Listar Todos(as)");
            System.out.println("3. Atualizar");
            System.out.println("4. Deletar");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha sua opção: ");

            try {
                int escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1:
                        acaoCadastrar();
                        break;
                    case 2:
                        acaoListar();
                        break;
                    case 3:
                        acaoAtualizar();
                        break;
                    case 4:
                        acaoDeletar();
                        break;
                    case 0:
                        executando = false;
                        break;
                    default:
                        System.err.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro: Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    protected void pausar() {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    protected Optional<Long> pedirId(String mensagemPrompt) {
        try {
            System.out.print(mensagemPrompt);
            long id = Long.parseLong(scanner.nextLine());
            return Optional.of(id);
        } catch (NumberFormatException e) {
            System.err.println("ID inválido. A operação foi cancelada.");
            pausar();
            return Optional.empty();
        }
    }
}*/
