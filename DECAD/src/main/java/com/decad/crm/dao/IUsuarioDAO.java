package com.decad.crm.dao;

import com.decad.crm.model.Usuario;
import java.sql.SQLException;
import java.util.Optional;

public interface IUsuarioDAO {

    void criar(Usuario usuario);

    Optional<Usuario> buscarPorId(int idUsuario) throws SQLException;

    Optional <Usuario> buscarPorLogin(String login) throws SQLException;
}
