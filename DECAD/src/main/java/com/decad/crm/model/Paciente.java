package com.decad.crm.model;

public class Paciente {
    protected long id;
    protected String nome;
    protected String email;
    protected String cpf;
    protected String telefone;

    public Paciente() {}

    public Paciente(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public long getId() { return id; }
}
