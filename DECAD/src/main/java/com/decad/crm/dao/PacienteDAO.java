package com.decad.crm.dao;

import com.decad.crm.model.Paciente;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    public void salvarPaciente(Paciente paciente) {
        String sql = "INSERT INTO Paciente (nomeCompleto, email, cpf, telefone) VALUES (?, ?, ?, ?)";

        try( Connection conexao = ConectorBancoDeDados.conectar();
            PreparedStatement statement = conexao.prepareStatement(sql)){

            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getEmail());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getTelefone());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Paciente salvo com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar Paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar Paciente: " + e);
        }
    }

    public void buscarPacientePorId(long id) {
        String sql = "SELECT * FROM Paciente WHERE id = ?";

        try(Connection conexao = ConectorBancoDeDados.conectar();
            PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            //em desenvolvimento...

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar Paciente: " + e);
        }
    }


}
