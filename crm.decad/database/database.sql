CREATE DATABASE IF NOT EXISTS crm_clinicas;
USE crm_clinicas;

CREATE TABLE EnderecoUnidade (
    IdEnderecoUnidade INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL
);

CREATE TABLE Unidade (
    IdUnidade INT AUTO_INCREMENT PRIMARY KEY,
    nomeUnidade VARCHAR(255) NOT NULL,
    IdEnderecoUnidade INT,
    FOREIGN KEY (IdEnderecoUnidade) REFERENCES EnderecoUnidade(IdEnderecoUnidade)
);

CREATE TABLE TelefonesProfissional (
    IdTelefonesProfissional INT AUTO_INCREMENT PRIMARY KEY,
    telefone1 INT UNIQUE,
    telefone2 INT UNIQUE
);

CREATE TABLE PagamentoProfissional (
    IdPagamentoProfissional INT AUTO_INCREMENT PRIMARY KEY,
    ValorPagamentoProfissional DECIMAL(10,2) NOT NULL,
    DataPagamentoProfissional DATE NOT NULL,
    StatusPagamentoProfissional VARCHAR(64) NOT NULL
);

CREATE TABLE Relatorio (
    IdRelatorio INT AUTO_INCREMENT PRIMARY KEY,
    tipoRelatorio VARCHAR(255) NOT NULL,
    periodoRelatorio VARCHAR(255) NOT NULL,
    dadosGeradosRelatorio VARCHAR(255) NOT NULL
);

CREATE TABLE Financeiro (
    IdFinanceiro INT AUTO_INCREMENT PRIMARY KEY,
    senhaFinanceiro VARCHAR(64) NOT NULL,
    loginFinanceiro VARCHAR(255) UNIQUE NOT NULL,
    nomeFinanceiro VARCHAR(255) NOT NULL,
    IdRelatorio INT,
    FOREIGN KEY (IdRelatorio) REFERENCES Relatorio(IdRelatorio)
);

CREATE TABLE PlanoTratamento (
    IdPlanoTratamento INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL,
    ValorTotal NUMERIC(10,2) NOT NULL,
    Status VARCHAR(64) NOT NULL
);

CREATE TABLE EtapaTratamento (
    IdEtapaTratamento INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL,
    Valor NUMERIC(10,2) NOT NULL,
    Status VARCHAR(255) NOT NULL,
    IdPlanoTratamento INT,
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento)
);

CREATE TABLE Profissional (
    IdProfissional INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    CRO_CRM VARCHAR(30) UNIQUE NOT NULL,
    especialidade VARCHAR(255) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    IdPagamentoProfissional INT,
    IdPlanoTratamento INT,
    IdTelefonesProfissional INT,
    FOREIGN KEY (IdPagamentoProfissional) REFERENCES PagamentoProfissional(IdPagamentoProfissional),
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento),
    FOREIGN KEY (IdTelefonesProfissional) REFERENCES TelefonesProfissional(IdTelefonesProfissional)
);

CREATE TABLE Agenda (
    IdAgenda INT AUTO_INCREMENT PRIMARY KEY,
    StatusAgenda VARCHAR(64) NOT NULL,
    horaAgenda TIME NOT NULL,
    dataAgenda DATE NOT NULL,
    IdUnidade INT,
    IdProfissional INT,
    FOREIGN KEY (IdUnidade) REFERENCES Unidade(IdUnidade),
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(IdProfissional)
);

CREATE TABLE Recepcionista (
    IdRecepcionista INT AUTO_INCREMENT PRIMARY KEY,
    nomeRecepcionista VARCHAR(255) NOT NULL,
    SenhaRecepcionista VARCHAR(64) NOT NULL,
    loginRecepcionista VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE EnderecoPaciente (
    IdEnderecoPaciente INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(255) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL
);

CREATE TABLE TelefonesPaciente (
    IdTelefonesPaciente INT AUTO_INCREMENT PRIMARY KEY,
    telefone1 INT UNIQUE,
    telefone2 INT UNIQUE
);

CREATE TABLE Paciente (
    IdPaciente INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    IdPagamento INT,
    IdPlanoTratamento INT,
    IdTelefonesPaciente INT,
    IdEnderecoPaciente INT,
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento),
    FOREIGN KEY (IdTelefonesPaciente) REFERENCES TelefonesPaciente(IdTelefonesPaciente),
    FOREIGN KEY (IdEnderecoPaciente) REFERENCES EnderecoPaciente(IdEnderecoPaciente)
);

CREATE TABLE Agendamento (
    IdAgendamento INT AUTO_INCREMENT PRIMARY KEY,
    StatusAgendamento VARCHAR(64) NOT NULL,
    horaAgendamento TIME NOT NULL,
    dataAgendamento DATE NOT NULL,
    IdAgenda INT,
    IdPaciente INT,
    IdRecepsionista INT,
    FOREIGN KEY (IdAgenda) REFERENCES Agenda(IdAgenda),
    FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente),
    FOREIGN KEY (IdRecepsionista) REFERENCES Recepcionista(IdRecepcionista)
);

CREATE TABLE Pagamento (
    IdPagamento INT AUTO_INCREMENT PRIMARY KEY,
    Status VARCHAR(64) NOT NULL,
    ValorPagamento DECIMAL(10,2) NOT NULL,
    dataPagamento DATE NOT NULL,
    formaPagamento VARCHAR(255) NOT NULL,
    IdAgendamento INT,
    IdPaciente INT,
    IdPlanoTratamento INT,
    FOREIGN KEY (IdAgendamento) REFERENCES Agendamento(IdAgendamento),
    FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente),
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento)
);

ALTER TABLE PagamentoProfissional 
ADD COLUMN IdFinanceiro INT,
ADD FOREIGN KEY (IdFinanceiro) REFERENCES Financeiro(IdFinanceiro);

ALTER TABLE Paciente 
ADD FOREIGN KEY (IdPagamento) REFERENCES Pagamento(IdPagamento);

ALTER TABLE Profissional 
ADD COLUMN IdAgenda INT,
ADD FOREIGN KEY (IdAgenda) REFERENCES Agenda(IdAgenda);

ALTER TABLE Recepcionista 
ADD COLUMN IdAgendamento INT,
ADD FOREIGN KEY (IdAgendamento) REFERENCES Agendamento(IdAgendamento);