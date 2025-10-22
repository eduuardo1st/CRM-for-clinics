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
        this.idAgendamento = idAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.horaAgendamento = horaAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.idPaciente = idPaciente;
        this.idRecepcionista = idRecepcionista;
    }

    public void setIdAgendamento(long idAgendamento) {this.idAgendamento = idAgendamento;}
    public String getStatusAgendamento() {return statusAgendamento;}
    public Time getHoraAgendamento() {return horaAgendamento;}
    public Date getDataAgendamento() {return dataAgendamento;}
    public long getIdAgenda() {return idAgenda;}
    public long getIdPaciente() {return idPaciente;}
    public long getIdRecepcionista() {return idRecepcionista;}

}
