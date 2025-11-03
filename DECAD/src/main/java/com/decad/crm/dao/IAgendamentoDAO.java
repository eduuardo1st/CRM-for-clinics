package com.decad.crm.dao;

import com.decad.crm.model.Agendamento;

import java.time.LocalDate;
import java.util.List;

public interface IAgendamentoDAO {

    void salvar(Agendamento agendamento);

    void deletar(long id);

    List<Agendamento> buscarPorProfissionalEData(long idProfissional, LocalDate data);

    List<String> getHorariosDisponiveis(int idProfissional, LocalDate data);
}
