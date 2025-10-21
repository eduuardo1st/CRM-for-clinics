package com.decad.crm.dao;

import com.decad.crm.model.Usuario;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void create(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (login, senha) VALUES (?, ?)";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar Usuario: " + e.getMessage());
            throw e;
        }
    }

    public Usuario buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
        Usuario usuario = null;

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Usuario por ID: " + e.getMessage());
            throw e;
        }

        return usuario;
    }

    public Usuario buscarPorLogin(String login) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE login = ?";
        Usuario usuario = null;

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Usuario por login: " + e.getMessage());
            throw e;
        }

        return usuario;
    }
}