DROP DATABASE IF EXISTS ClinicaDB;
CREATE DATABASE ClinicaDB;
USE ClinicaDB;

CREATE TABLE Relatorio (
    IdRelatorio INT AUTO_INCREMENT PRIMARY KEY,
    tipoRelatorio VARCHAR(255),
    periodoRelatorio VARCHAR(255),
    dadosGeradosRelatorio TEXT
);

CREATE TABLE EnderecoUnidade (
    IdEnderecoUnidade INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255),
    estado VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255)
);

CREATE TABLE Unidade (
    IdUnidade INT AUTO_INCREMENT PRIMARY KEY,
    nomeUnidade VARCHAR(255),
    IdEnderecoUnidade INT,
    FOREIGN KEY (IdEnderecoUnidade) REFERENCES EnderecoUnidade(IdEnderecoUnidade)
);

CREATE TABLE TelefonesProfissional (
    IdTelefonesProfissional INT AUTO_INCREMENT PRIMARY KEY,
    telefone1 VARCHAR(255) UNIQUE,
    telefone2 VARCHAR(255) UNIQUE
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
    FOREIGN KEY (IdTelefonesProfissional) REFERENCES TelefonesProfissional(IdTelefonesProfissional)
);

CREATE TABLE Agenda (
    IdAgenda INT AUTO_INCREMENT PRIMARY KEY,
    StatusAgenda VARCHAR(64),
    horaAgenda TIME,
    dataAgenda DATE,
    IdUnidade INT,
    IdProfissional INT,
    FOREIGN KEY (IdUnidade) REFERENCES Unidade(IdUnidade),
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(IdProfissional)
);

CREATE TABLE Recepcionista (
    IdRecepcionista INT AUTO_INCREMENT PRIMARY KEY,
    nomeRecepcionista VARCHAR(255),
    SenhaRecepcionista VARCHAR(64),
    loginRecepcionista VARCHAR(255) UNIQUE
);

CREATE TABLE EnderecoPaciente (
    IdEnderecoPaciente INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255),
    estado VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255)
);

CREATE TABLE TelefonesPaciente (
    IdTelefonesPaciente INT AUTO_INCREMENT PRIMARY KEY,
    telefone1 VARCHAR(255) UNIQUE,
    telefone2 VARCHAR(255) UNIQUE
);

CREATE TABLE Paciente (
    IdPaciente INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    nome VARCHAR(255),
    cpf VARCHAR(11) UNIQUE,
    IdPagamento INT,
    IdPlanoTratamento INT,
    IdTelefonesPaciente INT,
    IdEnderecoPaciente INT,
    FOREIGN KEY (IdTelefonesPaciente) REFERENCES TelefonesPaciente(IdTelefonesPaciente),
    FOREIGN KEY (IdEnderecoPaciente) REFERENCES EnderecoPaciente(IdEnderecoPaciente)
);

CREATE TABLE Agendamento (
    IdAgendamento INT AUTO_INCREMENT PRIMARY KEY,
    StatusAgendamento VARCHAR(64),
    horaAgendamento TIME,
    dataAgendamento DATE,
    IdAgenda INT,
    IdPaciente INT,
    IdRecepcionista INT,
    FOREIGN KEY (IdAgenda) REFERENCES Agenda(IdAgenda),
    FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente),
    FOREIGN KEY (IdRecepcionista) REFERENCES Recepcionista(IdRecepcionista)
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
    Status VARCHAR(64)
);

CREATE TABLE EtapaTratamento (
    IdEtapaTratamento INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    Valor DECIMAL(10,2),
    Status VARCHAR(64),
    IdPlanoTratamento INT,
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento)
);

CREATE TABLE Financeiro (
    IdFinanceiro INT AUTO_INCREMENT PRIMARY KEY,
    senhaFinanceiro VARCHAR(64),
    loginFinanceiro VARCHAR(255) UNIQUE,
    nomeFinanceiro VARCHAR(255),
    IdRelatorio INT,
    FOREIGN KEY (IdRelatorio) REFERENCES Relatorio(IdRelatorio)
);

CREATE TABLE PagamentoProfissional (
    IdPagamentoProfissional INT AUTO_INCREMENT PRIMARY KEY,
    ValorPagamentoProfissional DECIMAL(10,2),
    DataPagamentoProfissional DATE,
    StatusPagamentoProfissional VARCHAR(64),
    IdFinanceiro INT,
    IdProfissional INT,
    FOREIGN KEY (IdFinanceiro) REFERENCES Financeiro(IdFinanceiro),
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(IdProfissional)
);