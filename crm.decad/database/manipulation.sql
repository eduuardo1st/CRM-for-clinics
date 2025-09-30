use ClinicaDB;

SELECT * FROM Paciente;

SELECT * FROM Paciente WHERE cpf = ''; -- Esperar o Cauê criar os inserts

SELECT COUNT(*) AS TotalPacientes FROM Paciente;

SELECT * FROM Profissional;

SELECT nome, especialidade FROM Profissional WHERE especialidade = ''; -- Esperar o Cauê criar os inserts

SELECT pr.nome, tp.telefone1, tp.telefone2
FROM Profissional pr
LEFT JOIN TelefonesProfissional tp ON pr.IdTelefonesProfissonal = tp.IdTelefonesProfissional;

SELECT * FROM Agendamento;

SELECT * FROM Agendamento WHERE dataAgendamento >= CURDATE();

SELECT * FROM Pagamento;