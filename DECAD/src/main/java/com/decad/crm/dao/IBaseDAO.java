package com.decad.crm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface IBaseDAO<Tipo> {

    void salvar(Tipo objeto);

    void atualizar(Tipo objeto);

    void deletar(long id);

    Optional<Tipo> buscarPorId(long id);

    List<Tipo> listarTodos();
}
