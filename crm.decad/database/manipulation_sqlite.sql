INSERT INTO EnderecoUnidade (rua, estado, numero, bairro) VALUES
('Rua das Flores', 'SP', '123', 'Centro'),
('Av. Paulista', 'SP', '456', 'Bela Vista'),
('Rua Augusta', 'SP', '789', 'Consolação');

INSERT INTO Unidade (nomeUnidade, IdEnderecoUnidade) VALUES
('Clínica Odontológica Centro', 1),
('Clínica Odontológica Paulista', 2),
('Clínica Odontológica Augusta', 3);

INSERT INTO TelefonesProfissional (telefone1, telefone2) VALUES
(11987654321, 11987654322),
(11987654323, 11987654324),
(11987654325, 11987654326),
(11987654327, 11987654328),
(11987654329, 11987654330);

INSERT INTO Relatorio (tipoRelatorio, periodoRelatorio, dadosGeradosRelatorio) VALUES
('Financeiro Mensal', '2024-01', 'Receitas e despesas do mês'),
('Financeiro Trimestral', '2024-Q1', 'Relatório trimestral completo'),
('Atendimentos', '2024-01', 'Número de consultas realizadas');

INSERT INTO Financeiro (senhaFinanceiro, loginFinanceiro, nomeFinanceiro, IdRelatorio) VALUES
('senha123', 'financeiro1', 'Maria Silva', 1),
('senha456', 'financeiro2', 'João Santos', 2),
('senha789', 'financeiro3', 'Ana Costa', 3);

INSERT INTO PlanoTratamento (descricao, ValorTotal, Status) VALUES
('Tratamento de canal', 1500.00, 'Ativo'),
('Implante dentário', 3000.00, 'Ativo'),
('Ortodontia', 5000.00, 'Ativo'),
('Limpeza e profilaxia', 200.00, 'Concluído'),
('Restauração', 300.00, 'Ativo');

INSERT INTO EtapaTratamento (descricao, Valor, Status, IdPlanoTratamento) VALUES
('Diagnóstico e planejamento', 200.00, 'Concluído', 1),
('Tratamento de canal', 800.00, 'Em andamento', 1),
('Restauração final', 500.00, 'Pendente', 1),
('Avaliação inicial', 300.00, 'Concluído', 2),
('Cirurgia de implante', 2000.00, 'Pendente', 2),
('Protese sobre implante', 700.00, 'Pendente', 2),
('Avaliação ortodôntica', 400.00, 'Concluído', 3),
('Instalação do aparelho', 2000.00, 'Em andamento', 3),
('Acompanhamento mensal', 200.00, 'Pendente', 3);

INSERT INTO PagamentoProfissional (ValorPagamentoProfissional, DataPagamentoProfissional, StatusPagamentoProfissional, IdFinanceiro) VALUES
(5000.00, '2024-01-31', 'Pago', 1),
(4500.00, '2024-02-28', 'Pago', 2),
(5200.00, '2024-03-31', 'Pendente', 3),
(4800.00, '2024-01-31', 'Pago', 1),
(5100.00, '2024-02-28', 'Pago', 2);

INSERT INTO Agenda (StatusAgenda, horaAgenda, dataAgenda, IdUnidade, IdProfissional) VALUES
('Disponível', '09:00:00', '2024-04-15', 1, 1),
('Ocupada', '10:30:00', '2024-04-15', 1, 1),
('Disponível', '14:00:00', '2024-04-15', 1, 2),
('Ocupada', '15:30:00', '2024-04-15', 2, 2),
('Disponível', '08:00:00', '2024-04-16', 2, 3),
('Ocupada', '11:00:00', '2024-04-16', 3, 3),
('Disponível', '16:00:00', '2024-04-16', 3, 4),
('Ocupada', '17:30:00', '2024-04-16', 3, 5);

INSERT INTO Profissional (login, nome, CRO_CRM, especialidade, senha, IdPagamentoProfissional, IdPlanoTratamento, IdTelefonesProfissional, IdAgenda) VALUES
('dr.silva', 'Dr. Carlos Silva', 'CRO123456', 'Endodontia', 'senha123', 1, 1, 1, 1),
('dra.santos', 'Dra. Maria Santos', 'CRO789012', 'Implantodontia', 'senha456', 2, 2, 2, 3),
('dr.costa', 'Dr. João Costa', 'CRO345678', 'Ortodontia', 'senha789', 3, 3, 3, 5),
('dra.oliveira', 'Dra. Ana Oliveira', 'CRO901234', 'Periodontia', 'senha012', 4, 4, 4, 7),
('dr.ferreira', 'Dr. Pedro Ferreira', 'CRO567890', 'Odontopediatria', 'senha345', 5, 5, 5, 8);

INSERT INTO Recepcionista (nomeRecepcionista, SenhaRecepcionista, loginRecepcionista, IdAgendamento) VALUES
('Fernanda Lima', 'senha123', 'fernanda.lima', 1),
('Roberto Alves', 'senha456', 'roberto.alves', 3),
('Carla Mendes', 'senha789', 'carla.mendes', 5);

INSERT INTO EnderecoPaciente (estado, rua, numero, bairro) VALUES
('SP', 'Rua das Palmeiras', '100', 'Vila Madalena'),
('SP', 'Av. Faria Lima', '200', 'Itaim Bibi'),
('SP', 'Rua Oscar Freire', '300', 'Jardins'),
('SP', 'Rua Haddock Lobo', '400', 'Cerqueira César'),
('SP', 'Av. Rebouças', '500', 'Pinheiros'),
('SP', 'Rua Bela Cintra', '600', 'Consolação'),
('SP', 'Av. Ibirapuera', '700', 'Moema'),
('SP', 'Rua Augusta', '800', 'Consolação');

INSERT INTO TelefonesPaciente (telefone1, telefone2) VALUES
(11912345678, 11912345679),
(11912345680, 11912345681),
(11912345682, 11912345683),
(11912345684, 11912345685),
(11912345686, 11912345687),
(11912345688, 11912345689),
(11912345690, 11912345691),
(11912345692, 11912345693);

INSERT INTO Agendamento (StatusAgendamento, horaAgendamento, dataAgendamento, IdAgenda, IdPaciente, IdRecepsionista) VALUES
('Confirmado', '10:30:00', '2024-04-15', 2, 1, 1),
('Confirmado', '15:30:00', '2024-04-15', 4, 2, 1),
('Confirmado', '11:00:00', '2024-04-16', 6, 3, 2),
('Confirmado', '17:30:00', '2024-04-16', 8, 4, 2),
('Pendente', '09:00:00', '2024-04-17', 1, 5, 3),
('Pendente', '14:00:00', '2024-04-17', 3, 6, 3),
('Cancelado', '08:00:00', '2024-04-18', 5, 7, 1),
('Confirmado', '16:00:00', '2024-04-18', 7, 8, 2);

INSERT INTO Pagamento (Status, ValorPagamento, dataPagamento, formaPagamento, IdAgendamento, IdPaciente, IdPlanoTratamento) VALUES
('Pago', 200.00, '2024-04-15', 'Cartão de Crédito', 1, 1, 1),
('Pago', 300.00, '2024-04-15', 'PIX', 2, 2, 2),
('Pendente', 400.00, '2024-04-16', 'Dinheiro', 3, 3, 3),
('Pago', 200.00, '2024-04-16', 'Cartão de Débito', 4, 4, 4),
('Pendente', 300.00, '2024-04-17', 'PIX', 5, 5, 5),
('Pendente', 200.00, '2024-04-17', 'Cartão de Crédito', 6, 6, 1),
('Cancelado', 400.00, '2024-04-18', 'Dinheiro', 7, 7, 2),
('Pago', 200.00, '2024-04-18', 'PIX', 8, 8, 3);

INSERT INTO Paciente (email, nome, cpf, IdPlanoTratamento, IdTelefonesPaciente, IdEnderecoPaciente, IdPagamento) VALUES
('joao.silva@email.com', 'João Silva', '12345678901', 1, 1, 1, 1),
('maria.santos@email.com', 'Maria Santos', '23456789012', 2, 2, 2, 2),
('pedro.costa@email.com', 'Pedro Costa', '34567890123', 3, 3, 3, 3),
('ana.oliveira@email.com', 'Ana Oliveira', '45678901234', 4, 4, 4, 4),
('carlos.ferreira@email.com', 'Carlos Ferreira', '56789012345', 5, 5, 5, 5),
('julia.mendes@email.com', 'Julia Mendes', '67890123456', 1, 6, 6, 6),
('lucas.alves@email.com', 'Lucas Alves', '78901234567', 2, 7, 7, 7),
('fernanda.lima@email.com', 'Fernanda Lima', '89012345678', 3, 8, 8, 8);
