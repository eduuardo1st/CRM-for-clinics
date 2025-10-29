package com.decad.crm.view;

import com.decad.crm.dao.PacienteDAO;
import com.decad.crm.model.Paciente;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuPaciente {

    private Scanner scanner;
    private PacienteDAO pacienteDAO;

    public MenuPaciente(Scanner scanner) {
        this.scanner = scanner;
        this.pacienteDAO = new PacienteDAO();
    }

    public void mostrarMenuPacientes() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciamento de Pacientes ---");
            System.out.println("1. Cadastrar Novo Paciente");
            System.out.println("2. Listar Todos os Pacientes");
            System.out.println("3. Atualizar Paciente");
            System.out.println("4. Deletar Paciente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha sua opção: ");

            try {
                int escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1:
                        mostrarCadastrarPaciente();
                        break;
                    case 2:
                        mostrarListarPacientes();
                        break;
                    case 3:
                        mostrarAtualizarPaciente();
                        break;
                    case 4:
                        mostrarDeletarPaciente();
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

    private void mostrarCadastrarPaciente() {
        System.out.println("\n--- Cadastrar Novo Paciente ---");

        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        if (nome.isEmpty() || cpf.isEmpty()) {
            System.err.println("Erro: Nome e CPF são campos obrigatórios.");
            pausar();
            return;
        }
        Paciente novoPaciente = new Paciente(nome, email, cpf, telefone);

        try {
            pacienteDAO.salvarPaciente(novoPaciente);
            System.out.println("Paciente cadastrado com sucesso! (ID: " + novoPaciente.getId() + ")");
        } catch (RuntimeException e) {
            System.err.println("Erro ao salvar paciente: " + e.getMessage());
        }
        pausar();
    }

    private void mostrarListarPacientes() {
        System.out.println("\n--- Lista de Pacientes Cadastrados ---");

        List<Paciente> pacientes;
        try {
            pacientes = pacienteDAO.listarPacientes();
        } catch (RuntimeException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            pausar();
            return;
        }

        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado no sistema.");
            pausar();
            return;
        }

        // Formato da tabela
        String formatoTabela = "%-5s | %-30s | %-15s | %-30s | %-15s%n";
        System.out.printf(formatoTabela, "ID", "Nome Completo", "CPF", "Email", "Telefone");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Paciente p : pacientes) {
            System.out.printf(formatoTabela,
                    p.getId(),
                    p.getNome(),
                    p.getCpf(),
                    p.getEmail(),
                    p.getTelefone()
            );
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        pausar();
    }

    private void mostrarAtualizarPaciente() {
        System.out.println("\n--- Atualizar Paciente ---");

        // Pede o ID
        long id;
        try {
            System.out.print("Digite o ID do paciente que deseja atualizar: ");
            id = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("ID inválido. A atualização foi cancelada.");
            pausar();
            return;
        }

        Optional<Paciente> pacienteOpt = pacienteDAO.buscarPacientePorId(id);
        if (pacienteOpt.isEmpty()) {
            System.err.println("Paciente com ID " + id + " não encontrado.");
            pausar();
            return;
        }

        Paciente paciente = pacienteOpt.get();
        System.out.println("Paciente encontrado: " + paciente.getNome());

        System.out.print("Novo Nome (Atual: " + paciente.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) paciente.setNome(nome);

        System.out.print("Novo Email (Atual: " + paciente.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) paciente.setEmail(email);

        System.out.print("Novo CPF (Atual: " + paciente.getCpf() + "): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty()) paciente.setCpf(cpf);

        System.out.print("Novo Telefone (Atual: " + paciente.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) paciente.setTelefone(telefone);

        try {
            pacienteDAO.atualizarPaciente(paciente);
            System.out.println("Paciente atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
        }
        pausar();
    }

    private void mostrarDeletarPaciente() {
        System.out.println("\n--- Deletar Paciente ---");

        long id;
        try {
            System.out.print("Digite o ID do paciente que deseja DELETAR: ");
            id = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("ID inválido. A operação foi cancelada.");
            pausar();
            return;
        }

        System.err.print("ATENÇÃO: Isso é permanente. Tem certeza que deseja deletar o paciente ID " + id + "? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            try {
                pacienteDAO.deletarPaciente(id);
            } catch (RuntimeException e) {
                System.err.println("Erro ao deletar paciente: " + e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        pausar();
    }

    private void pausar() {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }
}