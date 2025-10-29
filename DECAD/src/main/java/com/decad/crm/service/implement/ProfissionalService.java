package com.decad.crm.service.implement;

import com.decad.crm.dao.IProfissionalDAO;
import com.decad.crm.model.Profissional;
import com.decad.crm.service.IProfissionalService;

import java.util.List;
import java.util.Optional;

// Camada para inserir a lógica geral de cada método implementado, autenticação, validação, etc...

public class ProfissionalService implements IProfissionalService {
    private final IProfissionalDAO profissionalDAO;

    public ProfissionalService(IProfissionalDAO profissionalDAO) {
        this.profissionalDAO = profissionalDAO;
    }

    @Override
    public void salvar(Profissional profissional) {
        if(profissional.getCpf() == null || profissional.getCpf().isEmpty()) {

            throw new RuntimeException("CPF é obrigatório!");
        }

        if(profissionalDAO.buscarPorCPF(profissional.getCpf()).isPresent()) {

            throw new RuntimeException("Profissional já cadastrado!");
         }

        profissionalDAO.salvar(profissional);
    }

    @Override
    public void atualizar(Profissional profissional) {
        profissionalDAO.atualizar(profissional);
    }

    @Override
    public void deletar(long id) {
        profissionalDAO.deletar(id);
    }

    @Override
    public Optional<Profissional> buscarPorId(long id) {
        return profissionalDAO.buscarPorId(id);
    }

    @Override
    public Optional<Profissional> buscarPorCPF(String CPF) {
        return profissionalDAO.buscarPorCPF(CPF);
    }

    @Override
    public List<Profissional> listarTodos() {
        return profissionalDAO.listarTodos();
    }
}