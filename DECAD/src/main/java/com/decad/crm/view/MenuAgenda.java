package com.decad.crm.view;

import com.decad.crm.controller.IAgendamentoController;
import com.decad.crm.controller.IProfissionalController;
import com.decad.crm.controller.implement.AgendamentoController;
import com.decad.crm.controller.implement.ProfissionalController;
import com.decad.crm.dao.IAgendamentoDAO;
import com.decad.crm.dao.IProfissionalDAO;
import com.decad.crm.dao.implement.AgendamentoDAO;
import com.decad.crm.dao.implement.ProfissionalDAO;
import com.decad.crm.model.Agendamento;
import com.decad.crm.model.Profissional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuAgenda {

    private Scanner scanner;
    private IAgendamentoController agendamentoController;
    private IProfissionalController profissionalController;

    private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

    public MenuAgenda(Scanner scanner) {
        this.scanner = scanner;

        IProfissionalDAO profissionalDAO = new ProfissionalDAO();
        this.profissionalController = new ProfissionalController(profissionalDAO);

        IAgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        this.agendamentoController = new AgendamentoController(agendamentoDAO);
    }

    public void exibirMenu() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciamento de agenda ---");
            System.out.println("1. Consultar agenda");
            System.out.println("2. Cadastrar novo agendamento");
            System.out.println("3. Deletar agendamento existente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha sua opção: ");

            try {
                int escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1:
                        mostrarConsultarAgenda();
                        break;
                    case 2:
                        salvarAgendamento();
                        break;
                    case 3:
                        deletarAgendamento();
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

    public void salvarAgendamento() {
        System.out.println("\n--- Cadastrar novo agendamento ---");

        System.out.print("Digite a data (formato dd/MM/yyyy)");
        String data_string = scanner.nextLine();
        System.out.print("Digite a hora (09:00 - 16:00)");
        String hora_string = scanner.nextLine();

        int id_paciente;
        int id_profissional;
        try {
            System.out.print("ID do paciente: ");
            id_paciente = Integer.parseInt(scanner.nextLine()); // Leia a linha e converta

            System.out.print("ID do profissional: ");
            id_profissional = Integer.parseInt(scanner.nextLine()); // Leia a linha e converta

        } catch (NumberFormatException e) {
            System.err.println("Erro: ID inválido. Preenchimento obrigatório dos IDs apenas com números. Agendamento cancelado.");
            System.out.println("\nPressione ENTER para voltar ao menu...");
            scanner.nextLine();
            return;
        }

        try {
            LocalDate data = LocalDate.parse(data_string, formatadorData);
            LocalTime hora = LocalTime.parse(hora_string, formatadorHora);

            Agendamento novoAgendamento = new Agendamento(data, hora, id_paciente, id_profissional);

            try {
                agendamentoController.salvarComValidacao(novoAgendamento);
                System.out.println("Agendamento cadastrado com sucesso! (ID: " + novoAgendamento.getIdAgendamento() + ")");

            } catch (RuntimeException e) {
                System.err.println("Erro ao salvar agendamento: " + e.getMessage());
            }

        } catch (DateTimeParseException d){
            System.err.println("Erro: Entrada inválida. Por favor, preencha os campos");
        }

        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    public void deletarAgendamento() {
        System.out.println("\n--- Deletar Agendamento ---");

        Optional<Long> idOpt = pedirId("Digite o ID do agendamento que deseja DELETAR: ");
        if (idOpt.isEmpty()) return;

        System.err.print("ATENÇÃO: Isso é permanente. Tem certeza que deseja deletar o agendamento ID " + idOpt.get() + "? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            try {
                agendamentoController.deletar(idOpt.get());
            } catch (RuntimeException e) {
                System.err.println("Erro ao deletar agendamento: " + e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }


    public void mostrarConsultarAgenda() {
        System.out.println("\n--- Consultar Agenda ---");

        Optional<Profissional> profissionalOpt = selecionarProfissional();
        if (profissionalOpt.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada");
            return;
        }
        Profissional profissional = profissionalOpt.get();

        LocalDate dataLocal = selecionarData();

        try {
            List<Agendamento> agendamentos = agendamentoController.buscarPorProfissionalEData(profissional.getId(), dataLocal);
            exibirAgenda(agendamentos, profissional, dataLocal);

        } catch (RuntimeException e) {
            System.err.println("Erro de banco de dados ao consultar a agenda: " + e.getMessage());
        }
    }

    private Optional<Profissional> selecionarProfissional() {
        List<Profissional> profissionais;
        try {
            profissionais = profissionalController.listarTodos();
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
                System.out.printf("ID %d - %s (Paciente ID: %d)\n",
                        ag.getIdAgendamento(),
                        ag.getHoraAgendamento(),
                        ag.getIdPaciente()
                );
            }
        }
        System.out.println("=============================================");
        System.out.println("Pressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    public Optional<Long> pedirId(String mensagemPrompt) {
        try {
            System.out.print(mensagemPrompt);
            long id = Long.parseLong(scanner.nextLine());
            return Optional.of(id);
        } catch (NumberFormatException e) {
            System.err.println("ID inválido. A operação foi cancelada.");
            System.out.println("\nPressione ENTER para voltar ao menu...");
            scanner.nextLine();
            return Optional.empty();
        }
    }
}

