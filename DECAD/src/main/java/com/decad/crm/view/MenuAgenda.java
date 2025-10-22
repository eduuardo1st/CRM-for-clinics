package com.decad.crm.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por exibir o menu de agenda do sistema CRM
 * e capturar informações para agendamentos.
 * 
 * @author Sistema CRM
 * @version 1.0
 */
public class MenuAgenda {
    
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;
    private DateTimeFormatter timeFormatter;
    
    /**
     * Construtor da classe MenuAgenda
     */
    public MenuAgenda() {
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    }
    
    /**
     * Exibe o menu de agenda e permite seleção de profissional e data
     * 
     * @return String[] - Array com [idProfissional, dataSelecionada]
     */
    public String[] selecionarProfissionalEData() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              MENU AGENDA");
        System.out.println("=".repeat(50));
        
        String idProfissional = selecionarProfissional();
        String dataSelecionada = selecionarData();
        
        return new String[]{idProfissional, dataSelecionada};
    }
    
    /**
     * Permite ao usuário selecionar um profissional
     * 
     * @return String - ID do profissional selecionado
     */
    private String selecionarProfissional() {
        System.out.println("\n📋 SELECIONAR PROFISSIONAL:");
        System.out.println("1. Dr. João Silva - Dentista");
        System.out.println("2. Dra. Maria Santos - Ortodontista");
        System.out.println("3. Dr. Pedro Costa - Endodontista");
        System.out.println("4. Dra. Ana Lima - Periodontista");
        System.out.println("5. Dr. Carlos Oliveira - Implantodontista");
        
        int opcao = -1;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            System.out.print("Digite o número do profissional (1-5): ");
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= 1 && opcao <= 5) {
                    entradaValida = true;
                } else {
                    System.out.println("❌ Opção inválida! Digite um número entre 1 e 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida! Digite um número.");
            }
        }
        
        return String.valueOf(opcao);
    }
    
    /**
     * Permite ao usuário selecionar uma data
     * 
     * @return String - Data selecionada no formato dd/MM/yyyy
     */
    private String selecionarData() {
        System.out.println("\n📅 SELECIONAR DATA:");
        System.out.println("Formato: DD/MM/AAAA (ex: 25/12/2024)");
        
        String data = "";
        boolean dataValida = false;
        
        while (!dataValida) {
            System.out.print("Digite a data desejada: ");
            data = scanner.nextLine().trim();
            
            try {
                LocalDate.parse(data, dateFormatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Data inválida! Use o formato DD/MM/AAAA");
            }
        }
        
        return data;
    }
    
    /**
     * Exibe os horários disponíveis para um profissional em uma data específica
     * 
     * @param idProfissional - ID do profissional
     * @param data - Data no formato dd/MM/yyyy
     */
    public void exibirHorariosDisponiveis(String idProfissional, String data) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           HORÁRIOS DISPONÍVEIS");
        System.out.println("=".repeat(50));
        System.out.println("Profissional: " + obterNomeProfissional(idProfissional));
        System.out.println("Data: " + data);
        System.out.println("=".repeat(50));
        
        List<String> horarios = gerarHorariosDisponiveis();
        
        System.out.println("\n🕐 HORÁRIOS DISPONÍVEIS:");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i + 1) + ". " + horarios.get(i));
        }
        
        System.out.println("\n" + "=".repeat(50));
    }
    
    /**
     * Obtém o nome do profissional baseado no ID
     * 
     * @param idProfissional - ID do profissional
     * @return String - Nome do profissional
     */
    private String obterNomeProfissional(String idProfissional) {
        switch (idProfissional) {
            case "1": return "Dr. João Silva - Dentista";
            case "2": return "Dra. Maria Santos - Ortodontista";
            case "3": return "Dr. Pedro Costa - Endodontista";
            case "4": return "Dra. Ana Lima - Periodontista";
            case "5": return "Dr. Carlos Oliveira - Implantodontista";
            default: return "Profissional não encontrado";
        }
    }
    
    /**
     * Gera uma lista de horários disponíveis (simulação)
     * 
     * @return List<String> - Lista de horários disponíveis
     */
    private List<String> gerarHorariosDisponiveis() {
        List<String> horarios = new ArrayList<>();
        
        // Simulação de horários disponíveis (8h às 17h, intervalos de 1h)
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFim = LocalTime.of(17, 0);
        
        LocalTime horaAtual = horaInicio;
        while (horaAtual.isBefore(horaFim)) {
            // Simula disponibilidade aleatória (70% de chance de estar disponível)
            if (Math.random() > 0.3) {
                horarios.add(horaAtual.format(timeFormatter));
            }
            horaAtual = horaAtual.plusHours(1);
        }
        
        // Se não houver horários disponíveis, adiciona alguns padrão
        if (horarios.isEmpty()) {
            horarios.add("08:00");
            horarios.add("10:00");
            horarios.add("14:00");
            horarios.add("16:00");
        }
        
        return horarios;
    }
    
    /**
     * Permite ao usuário selecionar um horário específico
     * 
     * @param horarios - Lista de horários disponíveis
     * @return String - Horário selecionado
     */
    public String selecionarHorario(List<String> horarios) {
        if (horarios.isEmpty()) {
            System.out.println("❌ Não há horários disponíveis para esta data.");
            return null;
        }
        
        System.out.println("\n🕐 SELECIONAR HORÁRIO:");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i + 1) + ". " + horarios.get(i));
        }
        
        int opcao = -1;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            System.out.print("Digite o número do horário desejado: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= 1 && opcao <= horarios.size()) {
                    entradaValida = true;
                } else {
                    System.out.println("❌ Opção inválida! Digite um número entre 1 e " + horarios.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida! Digite um número.");
            }
        }
        
        return horarios.get(opcao - 1);
    }
    
    /**
     * Exibe uma mensagem de confirmação de agendamento
     * 
     * @param profissional - Nome do profissional
     * @param data - Data do agendamento
     * @param horario - Horário do agendamento
     */
    public void exibirConfirmacaoAgendamento(String profissional, String data, String horario) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        CONFIRMAÇÃO DE AGENDAMENTO");
        System.out.println("=".repeat(50));
        System.out.println("Profissional: " + profissional);
        System.out.println("Data: " + data);
        System.out.println("Horário: " + horario);
        System.out.println("=".repeat(50));
        System.out.println("✅ Agendamento realizado com sucesso!");
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
