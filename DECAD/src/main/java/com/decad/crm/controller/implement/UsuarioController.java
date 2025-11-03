package com.decad.crm.controller.implement;

import com.decad.crm.dao.IUsuarioDAO;
import com.decad.crm.model.Usuario;
import com.decad.crm.controller.IUsuarioController;

import java.util.Optional;
import java.sql.SQLException;

public class UsuarioController implements IUsuarioController{
    private final IUsuarioDAO usuarioDAO;

    public UsuarioController(IUsuarioDAO UsuarioDAO) {
        this.usuarioDAO = UsuarioDAO;
    }

    @Override
    public void criarComValidacao(Usuario usuario) throws SQLException {
        if(usuario.getLogin() == null || usuario.getLogin().isEmpty()){

            throw new RuntimeException("Login obrigatório!");
        }

        if(usuarioDAO.buscarPorLogin(usuario.getLogin()).isPresent()){

            throw new RuntimeException("Usuário já existente.");
        }

        if(usuario.getSenha() == null || usuario.getSenha().isEmpty()){

            throw new RuntimeException("Senha obrigatória!");
        }

        usuarioDAO.criar(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(int idUsuario) throws SQLException{
        return usuarioDAO.buscarPorId(idUsuario);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) throws SQLException{
        return usuarioDAO.buscarPorLogin(login);
    }
}
