package com.decad.crm.service;

import com.decad.crm.dao.UsuarioDAO;
import com.decad.crm.model.Usuario;
import java.sql.SQLException;


// verifica se o login e senha estão corretos
public class AutenticacaoService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean login(String login, String senha) {
        try {
            Usuario usuario = usuarioDAO.buscarPorLogin(login);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
            throw new RuntimeException("Erro ao autenticar usuário.", e);
        }
        return false;
    }
}

