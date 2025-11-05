package com.decad.crm.dao;

import com.decad.crm.model.Agendamento;
import com.decad.crm.model.Profissional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IAgendamentoDAO {

    void salvar(Agendamento agendamento);

    void deletar(long id);

    boolean checarConflito(long idProfissional, LocalDate data, LocalTime hora);

    List<Agendamento> buscarPorProfissionalEData(long idProfissional, LocalDate data);

    List<String> getHorariosOcupados(long idProfissional, LocalDate data);
}
