package com.decad.crm.dao.implement;

import com.decad.crm.dao.IPacienteDAO;
import com.decad.crm.model.Paciente;
import com.decad.crm.model.Profissional;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDAO implements IPacienteDAO{
    // Usei ResultSet para pegar os dados das consultas SQL que realizei, então sempre que ver ResultSet no
    // código quer dizer que estou pegando os dados da consulta SQL

    private final String SQL_todos_campos = "SELECT IdPaciente, nomeCompleto, email, cpf, telefone FROM Paciente WHERE IdPaciente = ?";

    @Override
    public void salvar(Paciente paciente) {
        String sql = "INSERT INTO Paciente (nomeCompleto, email, cpf, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getEmail());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getTelefone());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Paciente salvo com sucesso!");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        paciente.setIdPaciente(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao salvar Paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar Paciente: " + e);
        }
    }

    @Override
    public void atualizar(Paciente paciente) {
        String sql = "UPDATE Paciente SET nomeCompleto = ?, email = ?, cpf = ?, telefone = ? WHERE IdPaciente = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getEmail());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getTelefone());
            statement.setLong(5, paciente.getId());

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Paciente atualizado com sucesso!");
            } else {
                System.out.println("ERRO! Nenhum paciente possui o ID informado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar Paciente: " + e);
        }
    }

    @Override
    public void deletar(long id) {
        String sql = "DELETE FROM Paciente WHERE IdPaciente = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Paciente deletado com sucesso!");
            } else {
                System.out.println("ERRO! Nenhum paciente possui o ID informado!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar paciente: " + e);
        }
    }

    @Override
    public Optional<Paciente> buscarPorId(long id) {
        String sql = SQL_todos_campos + " WHERE IdPaciente = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(passarDadosPacienteRS(resultSet));
                }
            }


        } catch (SQLException e) {
            System.out.println("Erro ao buscar Paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar Paciente: " + e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Paciente> buscarPorCPF(String CPF) {
        String sql = SQL_todos_campos + " WHERE CPF = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, CPF);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(passarDadosPacienteRS(resultSet));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar paciente: " + e);
        }
        return Optional.empty();
    }

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(SQL_todos_campos);
             ResultSet resultset = statement.executeQuery()) {
            while (resultset.next()) {
                pacientes.add(passarDadosPacienteRS(resultset));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar Pacientes: " + e.getMessage());
            throw new RuntimeException("Erro ao listar Pacientes: " + e);
        }
        return pacientes;
    }

    private Paciente passarDadosPacienteRS(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente(
                rs.getString("nomeCompleto"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("telefone")
        );
        paciente.setIdPaciente(rs.getLong("IdPaciente"));
        return paciente;
    }
}
