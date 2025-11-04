package com.decad.crm.controller.implement;

import com.decad.crm.dao.IAgendamentoDAO;
import com.decad.crm.model.Agendamento;
import com.decad.crm.controller.IAgendamentoController;

import java.time.LocalDate;
import java.util.List;

public class AgendamentoController implements IAgendamentoController{
    private final IAgendamentoDAO agendamentoDAO;

    public AgendamentoController(IAgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }

    public void salvarComValidacao(Agendamento agendamento){
        // Inserir lógica de validação
    }

    public void deletar(long id){
        agendamentoDAO.deletar(id);
    }

    public List<Agendamento> buscarPorProfissionalEData(long idProfissional, LocalDate data){
        return agendamentoDAO.buscarPorProfissionalEData(idProfissional, data);
    }

    public List<String> getHorariosDisponiveis(int idProfissional, LocalDate data){
        return agendamentoDAO.getHorariosDisponiveis(idProfissional, data);
    }
}
