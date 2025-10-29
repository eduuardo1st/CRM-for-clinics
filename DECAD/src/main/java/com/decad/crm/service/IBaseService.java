package com.decad.crm.service;

import java.util.List;
import java.util.Optional;

public interface IBaseService<Tipo> {

    void salvar(Tipo objeto);

    void atualizar(Tipo objeto);

    void deletar(long id);

    Optional<Tipo> buscarPorId(long id);

    List<Tipo> listarTodos();
}

