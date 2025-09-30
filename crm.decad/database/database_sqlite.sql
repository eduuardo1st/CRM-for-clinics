CREATE TABLE EnderecoUnidade (
    IdEnderecoUnidade INTEGER PRIMARY KEY AUTOINCREMENT,
    rua VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL
);

CREATE TABLE Unidade (
    IdUnidade INTEGER PRIMARY KEY AUTOINCREMENT,
    nomeUnidade VARCHAR(255) NOT NULL,
    IdEnderecoUnidade INTEGER,
    FOREIGN KEY (IdEnderecoUnidade) REFERENCES EnderecoUnidade(IdEnderecoUnidade)
);

CREATE TABLE TelefonesProfissional (
    IdTelefonesProfissional INTEGER PRIMARY KEY AUTOINCREMENT,
    telefone1 INTEGER UNIQUE,
    telefone2 INTEGER UNIQUE
);

CREATE TABLE PagamentoProfissional (
    IdPagamentoProfissional INTEGER PRIMARY KEY AUTOINCREMENT,
    ValorPagamentoProfissional DECIMAL(10,2) NOT NULL,
    DataPagamentoProfissional DATE NOT NULL,
    StatusPagamentoProfissional VARCHAR(64) NOT NULL,
    IdFinanceiro INTEGER
);

CREATE TABLE Relatorio (
    IdRelatorio INTEGER PRIMARY KEY AUTOINCREMENT,
    tipoRelatorio VARCHAR(255) NOT NULL,
    periodoRelatorio VARCHAR(255) NOT NULL,
    dadosGeradosRelatorio VARCHAR(255) NOT NULL
);

CREATE TABLE Financeiro (
    IdFinanceiro INTEGER PRIMARY KEY AUTOINCREMENT,
    senhaFinanceiro VARCHAR(64) NOT NULL,
    loginFinanceiro VARCHAR(255) UNIQUE NOT NULL,
    nomeFinanceiro VARCHAR(255) NOT NULL,
    IdRelatorio INTEGER,
    FOREIGN KEY (IdRelatorio) REFERENCES Relatorio(IdRelatorio)
);

CREATE TABLE PlanoTratamento (
    IdPlanoTratamento INTEGER PRIMARY KEY AUTOINCREMENT,
    descricao TEXT NOT NULL,
    ValorTotal NUMERIC(10,2) NOT NULL,
    Status VARCHAR(64) NOT NULL
);

CREATE TABLE EtapaTratamento (
    IdEtapaTratamento INTEGER PRIMARY KEY AUTOINCREMENT,
    descricao TEXT NOT NULL,
    Valor NUMERIC(10,2) NOT NULL,
    Status VARCHAR(255) NOT NULL,
    IdPlanoTratamento INTEGER,
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento)
);

CREATE TABLE Profissional (
    IdProfissional INTEGER PRIMARY KEY AUTOINCREMENT,
    login VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    CRO_CRM VARCHAR(30) UNIQUE NOT NULL,
    especialidade VARCHAR(255) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    IdPagamentoProfissional INTEGER,
    IdPlanoTratamento INTEGER,
    IdTelefonesProfissional INTEGER,
    IdAgenda INTEGER,
    FOREIGN KEY (IdPagamentoProfissional) REFERENCES PagamentoProfissional(IdPagamentoProfissional),
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento),
    FOREIGN KEY (IdTelefonesProfissional) REFERENCES TelefonesProfissional(IdTelefonesProfissional),
    FOREIGN KEY (IdAgenda) REFERENCES Agenda(IdAgenda)
);

CREATE TABLE Agenda (
    IdAgenda INTEGER PRIMARY KEY AUTOINCREMENT,
    StatusAgenda VARCHAR(64) NOT NULL,
    horaAgenda TIME NOT NULL,
    dataAgenda DATE NOT NULL,
    IdUnidade INTEGER,
    IdProfissional INTEGER,
    FOREIGN KEY (IdUnidade) REFERENCES Unidade(IdUnidade),
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(IdProfissional)
);

CREATE TABLE Recepcionista (
    IdRecepcionista INTEGER PRIMARY KEY AUTOINCREMENT,
    nomeRecepcionista VARCHAR(255) NOT NULL,
    SenhaRecepcionista VARCHAR(64) NOT NULL,
    loginRecepcionista VARCHAR(255) UNIQUE NOT NULL,
    IdAgendamento INTEGER,
    FOREIGN KEY (IdAgendamento) REFERENCES Agendamento(IdAgendamento)
);

CREATE TABLE EnderecoPaciente (
    IdEnderecoPaciente INTEGER PRIMARY KEY AUTOINCREMENT,
    estado VARCHAR(255) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL
);

CREATE TABLE TelefonesPaciente (
    IdTelefonesPaciente INTEGER PRIMARY KEY AUTOINCREMENT,
    telefone1 INTEGER UNIQUE,
    telefone2 INTEGER UNIQUE
);

CREATE TABLE Paciente (
    IdPaciente INTEGER PRIMARY KEY AUTOINCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    IdPagamento INTEGER,
    IdPlanoTratamento INTEGER,
    IdTelefonesPaciente INTEGER,
    IdEnderecoPaciente INTEGER,
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento),
    FOREIGN KEY (IdTelefonesPaciente) REFERENCES TelefonesPaciente(IdTelefonesPaciente),
    FOREIGN KEY (IdEnderecoPaciente) REFERENCES EnderecoPaciente(IdEnderecoPaciente),
    FOREIGN KEY (IdPagamento) REFERENCES Pagamento(IdPagamento)
);

CREATE TABLE Agendamento (
    IdAgendamento INTEGER PRIMARY KEY AUTOINCREMENT,
    StatusAgendamento VARCHAR(64) NOT NULL,
    horaAgendamento TIME NOT NULL,
    dataAgendamento DATE NOT NULL,
    IdAgenda INTEGER,
    IdPaciente INTEGER,
    IdRecepsionista INTEGER,
    FOREIGN KEY (IdAgenda) REFERENCES Agenda(IdAgenda),
    FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente),
    FOREIGN KEY (IdRecepsionista) REFERENCES Recepcionista(IdRecepcionista)
);

CREATE TABLE Pagamento (
    IdPagamento INTEGER PRIMARY KEY AUTOINCREMENT,
    Status VARCHAR(64) NOT NULL,
    ValorPagamento DECIMAL(10,2) NOT NULL,
    dataPagamento DATE NOT NULL,
    formaPagamento VARCHAR(255) NOT NULL,
    IdAgendamento INTEGER,
    IdPaciente INTEGER,
    IdPlanoTratamento INTEGER,
    FOREIGN KEY (IdAgendamento) REFERENCES Agendamento(IdAgendamento),
    FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente),
    FOREIGN KEY (IdPlanoTratamento) REFERENCES PlanoTratamento(IdPlanoTratamento)
);
