package com.decad.crm.model;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
    private long idAgendamento;
    private LocalDate dataAgendamento;
    private LocalTime horaAgendamento;
    private long idPaciente;
    private long idProfissional;

    public Agendamento () {}

    public Agendamento(LocalDate dataAgendamento, LocalTime horaAgendamento, long idPaciente, long idProfissional) {
        this.dataAgendamento = dataAgendamento;
        this.horaAgendamento = horaAgendamento;
        this.idPaciente = idPaciente;
        this.idProfissional = idProfissional;
    }

    public long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalTime getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(LocalTime horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(long idProfissional) {
        this.idProfissional = idProfissional;
    }

}
