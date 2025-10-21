package com.decad.crm.model;
import java.sql.Date;
import java.sql.Time;

public class Agendamento {
    protected long idAgendamento;
    protected String statusAgendamento;
    protected Time horaAgendamento;
    protected Date dataAgendamento;
    protected long idAgenda;
    protected long idPaciente;
    protected long idRecepcionista;

    public Agendamento(long idAgendamento,String statusAgendamento, Time horaAgendamento, Date dataAgendamento, long idAgenda, long idPaciente, long idRecepcionista) {
        this.statusAgendamento = statusAgendamento;
        this.horaAgendamento = horaAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.idAgenda = idAgendamento;
        this.idPaciente = idPaciente;
        this.idRecepcionista = idRecepcionista;
    }
    public long getIdAgendamento() {
        return idAgendamento;
    }
    public void setIdAgendamento(long idAgendamento) {}
}
