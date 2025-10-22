package com.decad.crm.dao;

import com.decad.crm.model.Profissional;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfissionalDAO {

    public void salvarProfissional(Profissional profissional) {

        String sql = "INSERT INTO Profissional (nomeCompleto, email, cpf, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getTelefone());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Profissional salvo com sucesso!");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        profissional.setIdProfissional(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao salvar Profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar Profissional: " + e);
        }
    }

    public void salvarProfissionalEspecialidade(Profissional profissional) {

        String sql = "INSERT INTO Profissional (nomeCompleto, email, cpf, telefone, cro_crm, especialidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getTelefone());
            statement.setString(4, profissional.getCro_crm());
            statement.setString(4, profissional.getEspecialidade());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Profissional salvo com sucesso!");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        profissional.setIdProfissional(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao salvar Profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar Profissional: " + e);
        }
    }

    public Optional<Profissional> buscarProfissionalPorId(long id) {
        String sql = "SELECT IdProfissional, nomeCompleto, email, cpf, telefone FROM Profissional WHERE IdProfissional = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(passarDadosProfissionalRS(resultSet));
                }
            }


        } catch (SQLException e) {
            System.out.println("Erro ao buscar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar profissional: " + e);
        }
        return Optional.empty();
    }

    public List<Profissional> ListarProfissional() {
        String sql = "SELECT IdProfissional, nomeCompleto, email, cpf, telefone FROM Profissional";
        List<Profissional> profissional = new ArrayList<>();

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultset = statement.executeQuery()) {
            while (resultset.next()) {
                profissional.add(passarDadosProfissionalRS(resultset));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao listar profissional: " + e);
        }
        return profissional;
    }

    public void atualizarProfissional(Profissional profissional) {
        String sql = "UPDATE Profissional SET nomeCompleto = ?, eAmail = ?, cpf = ?, cro_crm = ?, especialidade = ?, telefone = ? WHERE IdProfissional = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getCro_crm());
            statement.setString(5, profissional.getEspecialidade());
            statement.setString(6, profissional.getTelefone());
            statement.setLong(7, profissional.getId());

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Profissional atualizado com sucesso!");
            } else {
                System.out.println("ERRO! Nenhum profissional possui o ID informado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar profissional: " + e);
        }
    }

    public void deletarProfissional(long id) {
        String sql = "DELETE FROM Profissional WHERE IdProfissional = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Profissional deletado com sucesso!");
            } else {
                System.out.println("ERRO! Nenhum profissional possui o ID informado!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar profissional: " + e);
        }
    }

    private Profissional passarDadosProfissionalRS(ResultSet rs) throws SQLException {
        Profissional profissional = new Profissional(
                rs.getString("nomeCompleto"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("telefone")
        );
        profissional.setIdProfissional(rs.getLong("IdProfissional"));

        return profissional;
    }
}
