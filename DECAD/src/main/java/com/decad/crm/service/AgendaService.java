package com.decad.crm.service;

import com.decad.crm.dao.AgendamentoDAO;
import com.decad.crm.model.Agendamento;
import com.decad.crm.dao.PacienteDAO;
import com.decad.crm.model.Paciente;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgendaService {

    private final PacienteDAO pacienteDAO;
    private final AgendamentoDAO agendamentoDAO;
    private static final LocalTime HORA_INICIO = LocalTime.of(8, 0);
    private static final LocalTime HORA_FIM = LocalTime.of(18, 0);
    private static final int INTERVALO_MINUTOS = 30;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public AgendaService() {
        this.agendamentoDAO = new AgendamentoDAO();
        this.pacienteDAO = new PacienteDAO();
    }

    public List<String> gerarAgendaDoDia(int profissionalId, LocalDate data) {
        Date sqlDate = Date.valueOf(data);
        List<String> agenda = new ArrayList<>();
        try {
            List<Agendamento> agendamentosDoDia = agendamentoDAO.buscarPorProfissionalEData(profissionalId, sqlDate);

            Map<LocalTime, Agendamento> horariosOcupados = agendamentosDoDia.stream()
                    .collect(Collectors.toMap(
                            ag -> ag.getHoraAgendamento().LocalTime(),
                            ag -> ag
                    ));

            LocalTime horaAtual = HORA_INICIO;

            while (horaAtual.isBefore(HORA_FIM)) {
                String horarioFormatado = horaAtual.format(FORMATTER);
                String status;

                if (horariosOcupados.containsKey(horaAtual)) {
                    Agendamento agendamento = horariosOcupados.get(horaAtual);
                    String nomePaciente = "ID Paciente: " + agendamento.getIdPaciente();
                    try {
                        Paciente paciente = pacienteDAO.buscarPacientePorId(agendamento.getIdPaciente()).orElse(null);
                        if (paciente != null) {
                            nomePaciente = paciente.getNome();
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao buscar paciente para agendamento: " + e.getMessage());
                    }
                    status = "Ocupado (" + nomePaciente + ")";
                } else {
                    status = "Disponível";
                }

                agenda.add(horarioFormatado + " - " + status);
                horaAtual = horaAtual.plusMinutes(INTERVALO_MINUTOS);
            }

        } catch (Exception e) {
            System.err.println("Erro ao gerar agenda do dia: " + e.getMessage());
        }

        return agenda;
    }
}
