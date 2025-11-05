package com.decad.crm.dao.implement;

import com.decad.crm.dao.IProfissionalDAO;
import com.decad.crm.model.Profissional;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfissionalDAO implements IProfissionalDAO {

    private final String SQL_todos_campos = "SELECT IdProfissional, nomeCompleto, email, cpf, telefone, cro_crm, especialidade FROM Profissional";

    @Override
    public void salvar(Profissional profissional) {

        String sql = "INSERT INTO Profissional (nomeCompleto, email, cpf, telefone, cro_crm, especialidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getTelefone());
            statement.setString(5, profissional.getCro_crm());
            statement.setString(6, profissional.getEspecialidade());
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
            System.err.println("Erro ao salvar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar profissional: " + e);
        }
    }

    @Override
    public void atualizar(Profissional profissional) {
        String sql = "UPDATE Profissional SET nomeCompleto = ?, email = ?, cpf = ?, cro_crm = ?, especialidade = ?, telefone = ? WHERE IdProfissional = ?";

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
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar profissional: " + e);
        }
    }

    @Override
    public void deletar(long id) {
        String sql = "DELETE FROM Profissional WHERE IdProfissional = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Profissional deletado com sucesso!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar profissional: " + e);
        }
    }

    @Override
    public Optional<Profissional> buscarPorId(long id) {
        String sql = SQL_todos_campos + " WHERE IdProfissional = ?";

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

    @Override
    public Optional<Profissional> buscarPorCPF(String CPF) {
        String sql = SQL_todos_campos + " WHERE CPF = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, CPF);

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

    @Override
    public List<Profissional> listarTodos() {
        List<Profissional> profissionais = new ArrayList<>();

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(SQL_todos_campos);
             ResultSet resultset = statement.executeQuery()) {
            while (resultset.next()) {
                profissionais.add(passarDadosProfissionalRS(resultset));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar profissional: " + e.getMessage());
            throw new RuntimeException("Erro ao listar profissional: " + e);
        }
        return profissionais;
    }

    private Profissional passarDadosProfissionalRS(ResultSet rs) throws SQLException {
        Profissional profissional = new Profissional(
                rs.getString("nomeCompleto"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("cro_crm"),
                rs.getString("especialidade")
        );
        profissional.setIdProfissional(rs.getLong("IdProfissional"));

        return profissional;
    }
}