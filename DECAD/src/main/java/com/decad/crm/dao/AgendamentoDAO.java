package com.decad.crm.dao;

import com.decad.crm.model.Agendamento;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public void salvar(Agendamento agendamento) {
        String sql = "INSERT INTO Agendamento (dataAgendamento, horaAgendamento, IdPaciente, IdProfissional) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, Date.valueOf(agendamento.getDataAgendamento()));
            stmt.setTime(2, Time.valueOf(agendamento.getHoraAgendamento()));
            stmt.setLong(3, agendamento.getIdPaciente());
            stmt.setLong(4, agendamento.getIdProfissional());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    agendamento.setIdAgendamento(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar Agendamento: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar Agendamento.", e);
        }
    }

    public List<Agendamento> buscarPorProfissionalEData(long idProfissional, LocalDate data) {
        String sql = "SELECT * FROM Agendamento WHERE IdProfissional = ? AND dataAgendamento = ? ORDER BY horaAgendamento";
        List<Agendamento> agendamentos = new ArrayList<>();

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idProfissional);
            stmt.setDate(2, Date.valueOf(data)); // Converte LocalDate para sql.Date

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    agendamentos.add(mapearResultSetParaAgendamento(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Agendamentos: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar Agendamentos.", e);
        }
        return agendamentos;
    }

    public void deletar(long id) {
        String sql = "DELETE FROM Agendamento WHERE IdAgendamento = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Agendamento deletado com sucesso!");
            } else {
                System.out.println("Nenhum agendamento encontrado com o ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Agendamento: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar Agendamento.", e);
        }
    }

    private Agendamento mapearResultSetParaAgendamento(ResultSet rs) throws SQLException {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdAgendamento(rs.getLong("IdAgendamento"));

        // Converte os tipos sql.Date/sql.Time para LocalDate/LocalTime
        agendamento.setDataAgendamento(rs.getDate("dataAgendamento").toLocalDate());
        agendamento.setHoraAgendamento(rs.getTime("horaAgendamento").toLocalTime());

        agendamento.setIdPaciente(rs.getLong("IdPaciente"));
        agendamento.setIdProfissional(rs.getLong("IdProfissional"));
        return agendamento;
    }
}