package com.decad.crm.model;

public class Paciente extends Pessoa{
    protected long idPaciente;

    public Paciente() {}

    public Paciente(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public void setIdPaciente(long idPaciente) { this.idPaciente = idPaciente; }

    public long getId() { return idPaciente; }
}
