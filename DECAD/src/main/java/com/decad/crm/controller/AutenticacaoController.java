package com.decad.crm.controller;

import com.decad.crm.dao.IUsuarioDAO;
import com.decad.crm.model.Usuario;
import java.util.Optional;

public class AutenticacaoController {

    private final IUsuarioDAO usuarioDAO;

    public AutenticacaoController(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean login(String login, String senha) {
        try {
            Optional<Usuario> usuarioOpt = usuarioDAO.buscarPorLogin(login);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                if (usuario.getSenha().equals(senha)) {
                    System.out.println("Login bem-sucedido para o usuário: " + login);
                    return true;
                }
            }

            System.out.println("Falha no login: Credenciais inválidas para o usuário: " + login);
            return false;

        } catch (Exception e) {
            System.err.println("Erro durante o processo de login: " + e.getMessage());
            return false;
        }
    }
}