package com.decad.crm.view;

import com.decad.crm.dao.PacienteDAO;
import com.decad.crm.model.Paciente;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuPaciente extends MenuCrudBase {

    private PacienteDAO pacienteDAO;

    public MenuPaciente(Scanner scanner) {
        super(scanner);
        this.pacienteDAO = new PacienteDAO();
    }

    @Override
    protected String getNomeEntidade() {
        return "Pacientes";
    }

    @Override
    protected void acaoCadastrar() {
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

    @Override
    protected void acaoListar() {
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

    @Override
    protected void acaoAtualizar() {
        System.out.println("\n--- Atualizar Paciente ---");

        Optional<Long> idOpt = pedirId("Digite o ID do paciente que deseja atualizar: ");
        if (idOpt.isEmpty()) return;

        Optional<Paciente> pacienteOpt = pacienteDAO.buscarPacientePorId(idOpt.get());
        if (pacienteOpt.isEmpty()) {
            System.err.println("Paciente com ID " + idOpt.get() + " não encontrado.");
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
        } catch (RuntimeException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
        }
        pausar();
    }

    @Override
    protected void acaoDeletar() {
        System.out.println("\n--- Deletar Paciente ---");

        Optional<Long> idOpt = pedirId("Digite o ID do paciente que deseja DELETAR: ");
        if (idOpt.isEmpty()) return;

        System.err.print("ATENÇÃO: Isso é permanente. Tem certeza que deseja deletar o paciente ID " + idOpt.get() + "? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            try {
                pacienteDAO.deletarPaciente(idOpt.get());
            } catch (RuntimeException e) {
                System.err.println("Erro ao deletar paciente: " + e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        pausar();
    }
}