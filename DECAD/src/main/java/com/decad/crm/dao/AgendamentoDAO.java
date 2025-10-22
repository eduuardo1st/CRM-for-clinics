package com.decad.crm.dao;

import com.decad.crm.model.Agendamento;
import com.decad.crm.util.ConectorBancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public void create(Agendamento a) throws SQLException {
        String sql = "INSERT INTO agendamento (status_agendamento, hora_agendamento, data_agendamento, " +
                "id_agenda, id_paciente, id_recepcionista) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getStatusAgendamento());
            stmt.setTime(2, a.getHoraAgendamento());
            stmt.setDate(3, a.getDataAgendamento());
            stmt.setLong(4, a.getIdAgenda());
            stmt.setLong(5, a.getIdPaciente());
            stmt.setLong(6, a.getIdRecepcionista());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                a.setIdAgendamento(rs.getLong(1)); // necessário usar um setter do id, pois os métdoso no models são privados.
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar Agendamento: " + e.getMessage());
            throw e;
        }
    }

    public List<Agendamento> buscarPorProfissionalEData(long idAgenda, Date data) throws SQLException {
        String sql = "SELECT * FROM agendamento WHERE id_agenda = ? AND data_agendamento = ?";
        List<Agendamento> agendamentos = new ArrayList<>();

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idAgenda);
            stmt.setDate(2, data);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                        rs.getLong("id_agendamento"),
                        rs.getString("status_agendamento"),
                        rs.getTime("hora_agendamento"),
                        rs.getDate("data_agendamento"),
                        rs.getLong("id_agenda"),
                        rs.getLong("id_paciente"),
                        rs.getLong("id_recepcionista")
                );
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Agendamentos por profissional e data: " + e.getMessage());
            throw e;
        }

        return agendamentos;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM agendamento WHERE id_agendamento = ?";

        try (Connection conexao = ConectorBancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Agendamento: " + e.getMessage());
            throw e;
        }
    }
}
