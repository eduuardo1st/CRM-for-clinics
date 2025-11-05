package com.decad.crm.dao.implement;

import com.decad.crm.model.Usuario;
import com.decad.crm.dao.IUsuarioDAO;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.util.Optional;

public class UsuarioDAO implements IUsuarioDAO{

    @Override
    public Optional <Usuario> buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
        Usuario usuario = null;

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(passarDadosUsuarioRS(resultSet));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar usuário: " + e);
        }
        return Optional.empty();
    }

    @Override
    public Optional <Usuario> buscarPorLogin(String login) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE login = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, login);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(passarDadosUsuarioRS(resultSet));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar usuário: " + e);
        }
        return Optional.empty();
    }

    private Usuario passarDadosUsuarioRS(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(
                rs.getString("login"),
                rs.getString("senha")
        );

        usuario.setIdUsuario(rs.getLong("IdUsuario"));

        return usuario;
    }
}
