DROP DATABASE IF EXISTS decad_crm_mvp;

CREATE DATABASE decad_crm_mvp;

USE decad_crm_mvp;

CREATE TABLE Usuario (
    IdUsuario INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL -- A senha será criptografada pelo Spring Security.
);

CREATE TABLE Paciente (
    IdPaciente INT AUTO_INCREMENT PRIMARY KEY,
    nomeCompleto VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(20)
);

CREATE TABLE Profissional (
    IdProfissional INT AUTO_INCREMENT PRIMARY KEY,
    nomeCompleto VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    cro_crm VARCHAR(30) UNIQUE,
    especialidade VARCHAR(255),
    telefone VARCHAR(20)
);

CREATE TABLE Agendamento (
    IdAgendamento INT AUTO_INCREMENT PRIMARY KEY,
    dataAgendamento DATE NOT NULL,
    horaAgendamento TIME NOT NULL,
    IdPaciente INT,
    IdProfissional INT,
    FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente),
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(IdProfissional)
);