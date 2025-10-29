package com.decad.crm.service;

import com.decad.crm.dao.UsuarioDAO;
import com.decad.crm.model.Usuario;

public class AutenticacaoService {

    private final UsuarioDAO usuarioDAO;

    public AutenticacaoService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean login(String login, String senha) {
        try {
            Usuario usuario = usuarioDAO.buscarPorLogin(login);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido para o usuário: " + login);
                return true;
            } else {
                System.out.println("Falha no login: Credenciais inválidas para o usuário: " + login);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro durante o processo de login: " + e.getMessage());
            return false;
        }
    }
}
