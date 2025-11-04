package com.decad.crm.controller.implement;

import com.decad.crm.dao.IAgendamentoDAO;
import com.decad.crm.model.Agendamento;
import com.decad.crm.controller.IAgendamentoController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController implements IAgendamentoController {

    private final IAgendamentoDAO agendamentoDAO;

    public AgendamentoController(IAgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }

    @Override
    public void salvarComValidacao(Agendamento agendamento){

        if(agendamento.getDataAgendamento() == null) {

            throw new RuntimeException("Data obrigatório!");
        }

        if(agendamento.getHoraAgendamento() == null) {

            throw new RuntimeException("Hora do agendamento obrigatória!");
        }

        if(agendamento.getIdProfissional() == 0) {

            throw new RuntimeException("ID do profissional obrigatório!");
        }

        if(agendamento.getIdPaciente() == 0) {

            throw new RuntimeException("ID do paciente obrigatório!");
        }

        agendamentoDAO.salvar(agendamento);
    }

    @Override
    public void deletar(long id){
        agendamentoDAO.deletar(id);
    }

    @Override
    public List<Agendamento> buscarPorProfissionalEData(long idProfissional, LocalDate data){
        return agendamentoDAO.buscarPorProfissionalEData(idProfissional, data);
    }

    @Override
    public List<String> getHorariosDisponiveis(long idProfissional, LocalDate data) {

        // Expediente de 9 a 17
        List<String> todosOsHorarios = new ArrayList<>();
        for (int hora = 9; hora < 17; hora++) {
            todosOsHorarios.add(String.format("%02d:00", hora));
        }

        List<String> horariosOcupados = agendamentoDAO.getHorariosOcupados(idProfissional, data);

        todosOsHorarios.removeAll(horariosOcupados);

        return todosOsHorarios;
    }
}
