
USE ClinicaDB;

CREATE TABLE Unidade (
    IdUnidade INT AUTO_INCREMENT PRIMARY KEY,
    nomeUnidade VARCHAR(255),
    IdEnderecoUnidade INT
);

CREATE TABLE EnderecoUnidade (
    IdEnderecoUnidade INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255),
    estado VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255),
    IdUnidade INT,
    FOREIGN KEY (IdUnidade) REFERENCES Unidade(IdUnidade)
);

CREATE TABLE Agenda (
    IdAgenda INT AUTO_INCREMENT PRIMARY KEY,
    StatusAgenda VARCHAR(64),
    horaAgenda TIME,
    dataAgenda DATE,
    IdUnidade INT,
    IdProfissional INT,
    FOREIGN KEY (IdUnidade) REFERENCES Unidade(IdUnidade)
);

CREATE TABLE EnderecoPaciente (
    IdEnderecoPaciente INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255),
    estado VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255),
    IdPaciente INT
);

CREATE TABLE TelefonesPaciente (
    IdTelefonesPaciente INT AUTO_INCREMENT PRIMARY KEY,
    telefone1 VARCHAR(255) UNIQUE,
    telefone2 VARCHAR(255) UNIQUE,
    IdPaciente INT
);

CREATE TABLE TelefonesProfissional (
    IdTelefonesProfissional INT AUTO_INCREMENT PRIMARY KEY,
    telefone1 VARCHAR(255) UNIQUE,
    telefone2 VARCHAR(255) UNIQUE,
    IdProfissional INT
);

CREATE TABLE Profissional (
    IdProfissional INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) UNIQUE,
    nome VARCHAR(255),
    cpf VARCHAR(11) UNIQUE,
    CRO_CRM VARCHAR(30) UNIQUE,
    especialidade VARCHAR(255),
    senha VARCHAR(64),
    IdPagamentoProfissional INT,
    IdPlanoTratamento INT,
    IdTelefonesProfissional INT,
    IdAgenda INT,
    FOREIGN KEY (IdAgenda) REFERENCES Agenda(IdAgenda),
    FOREIGN KEY (IdTelefonesProfissional) REFERENCES TelefonesProfissional(IdTelefonesProfissional)
);

ALTER TABLE Agenda
ADD CONSTRAINT fk_agenda_prof FOREIGN KEY (IdProfissional) REFERENCES Profissional(IdProfissional);

CREATE TABLE Agendamento (
    IdAgendamento INT AUTO_INCREMENT PRIMARY KEY,
    StatusAgendamento VARCHAR(64),
    horaAgendamento TIME,
    dataAgendamento DATE,
    IdAgenda INT,
    IdPaciente INT,
    IdRecepcionista INT,
    FOREIGN KEY (IdAgenda) REFERENCES Agenda(IdAgenda)
);

CREATE TABLE Paciente (
    IdPaciente INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    nome VARCHAR(255),
    cpf VARCHAR(11) UNIQUE,
    IdAgendamento INT,
    IdPagamento INT,
    IdPlanoTratamento INT,
    IdTelefonesPaciente INT,
    IdEnderecoPaciente INT,
    FOREIGN KEY (IdAgendamento) REFERENCES Agendamento(IdAgendamento),
    FOREIGN KEY (IdTelefonesPaciente) REFERENCES TelefonesPaciente(IdTelefonesPaciente),
    FOREIGN KEY (IdEnderecoPaciente) REFERENCES EnderecoPaciente(IdEnderecoPaciente)
);

CREATE TABLE Recepcionista (
    IdRecepcionista INT AUTO_INCREMENT PRIMARY KEY,
    nomeRecepcionista VARCHAR(255),
    SenhaRecepcionista VARCHAR(64),
    loginRecepcionista VARCHAR(255) UNIQUE,
    IdAgendamento INT,
    FOREIGN KEY (IdAgendamento) REFERENCES Agendamento(IdAgendamento)
);

CREATE TABLE Pagamento (
    IdPagamento INT AUTO_INCREMENT PRIMARY KEY,
    Status VARCHAR(64),
    ValorPagamento DECIMAL(10,2),
    dataPagamento DATE,
    formaPagamento VARCHAR(32),
    IdAgendamento INT,
    IdPaciente INT,
    IdPlanoTratamento INT,
    FOREIGN KEY (IdAgendamento) REFERENCES Agendamento(IdAgendamento),
    FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente)
);

CREATE TABLE PlanoTratamento (
    IdPlanoTratamento INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    ValorTotal DECIMAL(10,2),
    Status VARCHAR(64),
    IdEtapaTratamento INT
);

CREATE TABLE EtapaTratamento (
    IdEtapaTratamento INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    Valor DECIMAL(10,2),
    Status VARCHAR(64),
    IdPlanoTratamento INT,
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento)
);

CREATE TABLE PagamentoProfissional (
    IdPagamentoProfissional INT AUTO_INCREMENT PRIMARY KEY,
    ValorPagamentoProfissional DECIMAL(10,2),
    DataPagamentoProfissional DATE,
    StatusPagamentoProfissional VARCHAR(64),
    IdFinanceiro INT,
    IdProfissional INT,
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(IdProfissional)
);

CREATE TABLE Financeiro (
    IdFinanceiro INT AUTO_INCREMENT PRIMARY KEY,
    senhaFinanceiro VARCHAR(64),
    loginFinanceiro VARCHAR(255) UNIQUE,
    nomeFinanceiro VARCHAR(255),
    IdRelatorio INT,
    IdPagamentoProfissional INT,
    FOREIGN KEY (IdPagamentoProfissional) REFERENCES PagamentoProfissional(IdPagamentoProfissional)
);

CREATE TABLE Relatorio (
    IdRelatorio INT AUTO_INCREMENT PRIMARY KEY,
    tipoRelatorio VARCHAR(255),
    periodoRelatorio VARCHAR(255),
    dadosGeradosRelatorio TEXT,
    IdFinanceiro INT,
    FOREIGN KEY (IdFinanceiro) REFERENCES Financeiro(IdFinanceiro)
);
