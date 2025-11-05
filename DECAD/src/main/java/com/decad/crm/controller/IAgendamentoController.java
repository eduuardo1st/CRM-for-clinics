package com.decad.crm.controller;

import com.decad.crm.model.Agendamento;

import java.time.LocalDate;
import java.util.List;

public interface IAgendamentoController {

    void salvarComValidacao(Agendamento agendamento);

    void deletar(long id);

    List<Agendamento> buscarPorProfissionalEData(long idProfissional, LocalDate data);

    List<String> getHorariosDisponiveis(long idProfissional, LocalDate data);
}
