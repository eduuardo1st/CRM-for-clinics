package com.decad.crm.controller;

import com.decad.crm.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface IBaseController<Tipo> {

    void salvarComValidacao(Tipo objeto);

    void atualizar(Tipo objeto);

    void deletar(long id);

    Optional<Tipo> buscarPorId(long id);

    Optional<Tipo> buscarPorCPF(String cpf);

    List<Tipo> listarTodos();

}

