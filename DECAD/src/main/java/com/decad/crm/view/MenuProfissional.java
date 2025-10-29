package com.decad.crm.view;

import com.decad.crm.dao.ProfissionalDAO;
import com.decad.crm.model.Profissional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuProfissional extends MenuCrudBase {

    private ProfissionalDAO profissionalDAO;

    public MenuProfissional(Scanner scanner) {
        super(scanner);
        this.profissionalDAO = new ProfissionalDAO();
    }

    @Override
    protected String getNomeEntidade() {
        return "Profissionais";
    }

    @Override
    protected void acaoCadastrar() {
        System.out.println("\n--- Cadastrar Novo Profissional ---");

        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CRO/CRM: ");
        String croCrm = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        if (nome.isEmpty() || cpf.isEmpty() || croCrm.isEmpty()) {
            System.err.println("Erro: Nome, CPF e CRO/CRM são campos obrigatórios.");
            pausar();
            return;
        }

        Profissional novoProfissional = new Profissional(nome, email, cpf, telefone, croCrm, especialidade);

        try {
            profissionalDAO.salvarProfissionalEspecialidade(novoProfissional);
            System.out.println("Profissional cadastrado com sucesso! (ID: " + novoProfissional.getId() + ")");
        } catch (RuntimeException e) {
            System.err.println("Erro ao salvar profissional: " + e.getMessage());
        }
        pausar();
    }

    @Override
    protected void acaoListar() {
        System.out.println("\n--- Lista de Profissionais Cadastrados ---");

        List<Profissional> profissionais;
        try {
            profissionais = profissionalDAO.listarProfissional();
        } catch (RuntimeException e) {
            System.err.println("Erro ao listar profissionais: " + e.getMessage());
            pausar();
            return;
        }

        if (profissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado no sistema.");
            pausar();
            return;
        }

        String formatoTabela = "%-5s | %-30s | %-15s | %-10s | %-20s | %-30s | %-15s%n";
        System.out.printf(formatoTabela, "ID", "Nome Completo", "CPF", "CRO/CRM", "Especialidade", "Email", "Telefone");
        System.out.println(new String(new char[136]).replace("\0", "-"));

        for (Profissional p : profissionais) {
            System.out.printf(formatoTabela,
                    p.getId(),
                    p.getNome(),
                    p.getCpf(),
                    p.getCro_crm(),
                    p.getEspecialidade(),
                    p.getEmail(),
                    p.getTelefone()
            );
        }
        System.out.println(new String(new char[136]).replace("\0", "-"));
        pausar();
    }

    @Override
    protected void acaoAtualizar() {
        System.out.println("\n--- Atualizar Profissional ---");

        Optional<Long> idOpt = pedirId("Digite o ID do profissional que deseja atualizar: ");
        if (idOpt.isEmpty()) return;

        Optional<Profissional> profOpt = profissionalDAO.buscarProfissionalPorId(idOpt.get());
        if (profOpt.isEmpty()) {
            System.err.println("Profissional com ID " + idOpt.get() + " não encontrado.");
            pausar();
            return;
        }

        Profissional profissional = profOpt.get();
        System.out.println("Profissional encontrado: " + profissional.getNome());

        System.out.print("Novo Nome (Atual: " + profissional.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) profissional.setNome(nome);

        System.out.print("Novo Email (Atual: " + profissional.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) profissional.setEmail(email);

        System.out.print("Novo CPF (Atual: " + profissional.getCpf() + "): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty()) profissional.setCpf(cpf);

        System.out.print("Novo Telefone (Atual: " + profissional.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) profissional.setTelefone(telefone);

        System.out.print("Novo CRO/CRM (Atual: " + profissional.getCro_crm() + "): ");
        String croCrm = scanner.nextLine();
        if (!croCrm.isEmpty()) profissional.setCro_crm(croCrm);

        System.out.print("Nova Especialidade (Atual: " + profissional.getEspecialidade() + "): ");
        String especialidade = scanner.nextLine();
        if (!especialidade.isEmpty()) profissional.setEspecialidade(especialidade);

        try {
            profissionalDAO.atualizarProfissional(profissional);
            System.out.println("Profissional atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.err.println("Erro ao atualizar profissional: " + e.getMessage());
        }
        pausar();
    }

    @Override
    protected void acaoDeletar() {
        System.out.println("\n--- Deletar Profissional ---");

        Optional<Long> idOpt = pedirId("Digite o ID do profissional que deseja DELETAR: ");
        if (idOpt.isEmpty()) return;

        System.err.print("ATENÇÃO: Isso é permanente. Tem certeza que deseja deletar o profissional ID " + idOpt.get() + "? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            try {
                profissionalDAO.deletarProfissional(idOpt.get());
            } catch (RuntimeException e) {
                System.err.println("Erro ao deletar profissional: " + e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        pausar();
    }
}