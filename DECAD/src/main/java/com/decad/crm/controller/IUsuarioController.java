package com.decad.crm.controller;

import com.decad.crm.model.Usuario;
import java.sql.SQLException;
import java.util.Optional;

public interface IUsuarioController {

    void criarComValidacao(Usuario usuario) throws SQLException;

    Optional<Usuario> buscarPorId(int idUsuario) throws SQLException;

    Optional <Usuario> buscarPorLogin(String login) throws SQLException;
}