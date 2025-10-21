package com.decad.crm.model;

public class Profissional extends Pessoa{
    protected long idProfissional;
    protected String cro_crm;
    protected String especialidade;

    Profissional() {}

    Profissional(String nome, String email, String cpf, String telefone){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    Profissional(String nome, String email, String cpf, String telefone, String cro_crm, String especialidade){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cro_crm = cro_crm;
        this.especialidade = especialidade;
    }

    public long getId() { return idProfissional; }

    public String getCro_crm() { return cro_crm; }

    public String getEspecialidade() { return especialidade; }
}
