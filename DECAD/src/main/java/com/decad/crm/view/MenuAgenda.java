package com.decad.crm.view;

import com.decad.crm.dao.AgendamentoDAO;
import com.decad.crm.dao.ProfissionalDAO;
import com.decad.crm.model.Agendamento;
import com.decad.crm.model.Profissional;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuAgenda {

    private Scanner scanner;
    private ProfissionalDAO profissionalDAO;
    private AgendamentoDAO agendamentoDAO;
    private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuAgenda(Scanner scanner) {
        this.scanner = scanner;
        this.profissionalDAO = new ProfissionalDAO();
        this.agendamentoDAO = new AgendamentoDAO();
    }

    public void mostrarConsultarAgenda() {
        System.out.println("\n--- Consultar Agenda ---");

        Optional<Profissional> profissionalOpt = selecionarProfissional();
        if (profissionalOpt.isEmpty()) {
            System.out.println("Consulta cancelada.");
            return;
        }
        Profissional profissional = profissionalOpt.get();

        LocalDate dataLocal = selecionarData();

        try {
            List<Agendamento> agendamentos = agendamentoDAO.buscarPorProfissionalEData(profissional.getId(), dataLocal);

            exibirAgenda(agendamentos, profissional, dataLocal);

        } catch (RuntimeException e) {
            System.err.println("Erro de banco de dados ao consultar a agenda: " + e.getMessage());
        }
    }

    private Optional<Profissional> selecionarProfissional() {
        List<Profissional> profissionais;
        try {
            profissionais = profissionalDAO.ListarProfissional();
        } catch (Exception e) {
            System.err.println("Erro ao listar profissionais: " + e.getMessage());
            return Optional.empty();
        }


        if (profissionais.isEmpty()) {
            System.err.println("Nenhum profissional cadastrado. Impossível consultar agenda.");
            return Optional.empty();
        }

        System.out.println("\nSelecione um profissional:");
        for (int i = 0; i < profissionais.size(); i++) {
            System.out.printf("%d. %s (Especialidade: %s)\n",
                    (i + 1),
                    profissionais.get(i).getNome(),
                    profissionais.get(i).getEspecialidade());
        }

        while (true) {
            System.out.print("Escolha o número (ou 0 para voltar): ");
            try {
                int escolha = Integer.parseInt(scanner.nextLine());

                if (escolha == 0) {
                    return Optional.empty();
                }

                if (escolha > 0 && escolha <= profissionais.size()) {
                    return Optional.of(profissionais.get(escolha - 1));
                } else {
                    System.err.println("Escolha inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Digite um número.");
            }
        }
    }

    private LocalDate selecionarData() {
        while (true) {
            System.out.print("Digite a data (formato dd/MM/yyyy): ");
            String inputData = scanner.nextLine();

            try {
                return LocalDate.parse(inputData, formatadorData);
            } catch (DateTimeParseException e) {
                System.err.println("Formato de data inválido! Use dd/MM/yyyy (ex: 25/10/2025).");
            }
        }
    }

    private void exibirAgenda(List<Agendamento> agendamentos, Profissional profissional, LocalDate data) {
        System.out.println("\n=============================================");
        System.out.println(" Agenda do(a) Prof. " + profissional.getNome());
        System.out.println(" Data: " + data.format(formatadorData));
        System.out.println("=============================================");

        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum horário agendado para este dia.");
        } else {
            System.out.println("Horários ocupados:");
            for (Agendamento ag : agendamentos) {
                System.out.printf("- %s (Paciente ID: %d)\n",
                        ag.getHoraAgendamento(),
                        ag.getIdPaciente()
                );
            }
        }
        System.out.println("=============================================");
        System.out.println("Pressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }
}