package com.decad.crm.controller.implement;

import com.decad.crm.dao.IPacienteDAO;
import com.decad.crm.model.Paciente;
import com.decad.crm.controller.IPacienteController;

import java.util.List;
import java.util.Optional;

public class PacienteController implements IPacienteController {

    private final IPacienteDAO pacienteDAO;

    public PacienteController(IPacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    @Override
    public void salvar(Paciente paciente) {

        if(paciente.getCpf() == null || paciente.getCpf().isEmpty()) {

            throw new RuntimeException("CPF obrigatório!");
        }

        if(pacienteDAO.buscarPorCPF(paciente.getCpf()).isPresent()) {

            throw new RuntimeException("Paciente já cadastrado!");
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
    public Optional<Paciente> buscarPorCPF(String cpf) {
        return pacienteDAO.buscarPorCPF(cpf);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteDAO.listarTodos();
    }
}

