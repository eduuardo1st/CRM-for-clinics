package com.decad.crm.service.implement;

import com.decad.crm.dao.IPacienteDAO;
import com.decad.crm.model.Paciente;
import com.decad.crm.service.IPacienteService;

import java.util.List;
import java.util.Optional;

public class PacienteService implements  IPacienteService {

    private final IPacienteDAO pacienteDAO;

    public PacienteService(IPacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    @Override
    public void salvar(Paciente paciente) {

        if(paciente.getCpf() == null || paciente.getCpf().isEmpty()) {

            throw new RuntimeException("CPF é obrigatório!");
        }

        pacienteDAO.salvar(paciente);
    }

    @Override
    public void atualizar(Paciente paciente) {
        pacienteDAO.atualizar(paciente);
    }

    @Override
    public void deletar(long id) {
        pacienteDAO.deletar(id);
    }

    @Override
    public Optional<Paciente> buscarPorId(long id) {
        return pacienteDAO.buscarPorId(id);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteDAO.listarTodos();
    }
}

