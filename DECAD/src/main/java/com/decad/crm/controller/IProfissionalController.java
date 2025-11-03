package com.decad.crm.controller;

import com.decad.crm.model.Profissional;
import java.util.List;
import java.util.Optional;

public interface IProfissionalController {

    void salvar(Profissional profissional);

    void atualizar(Profissional profissional);

    void deletar(long id);

    Optional<Profissional> buscarPorId(long id);

    Optional<Profissional> buscarPorCPF(String CPF);

    List<Profissional> listarTodos();
}