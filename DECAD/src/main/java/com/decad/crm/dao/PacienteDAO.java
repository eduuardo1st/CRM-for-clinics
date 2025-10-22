package com.decad.crm.dao;

import com.decad.crm.model.Paciente;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDAO {
    // Usei ResultSet para pegar os dados das consultas SQL que realizei, então sempre que ver ResultSet no
    // código quer dizer que estou pegando os dados da consulta SQL

    public void salvarPaciente(Paciente paciente) {
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

    public Optional<Paciente> buscarPacientePorId(long id) {
        String sql = "SELECT IdPaciente, nomeCompleto, email, cpf, telefone FROM Paciente WHERE IdPaciente = ?";

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

    public List<Paciente> ListarPacientes() {
        String sql = "SELECT IdPaciente, nomeCompleto, email, cpf, telefone FROM Paciente";
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql);
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

    public void atualizarPaciente(Paciente paciente) {
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

    public void deletarPaciente(long id) {
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
