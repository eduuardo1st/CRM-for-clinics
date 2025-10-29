package com.decad.crm.model;

public class Profissional extends Pessoa {
    protected long idProfissional;
    protected String cro_crm;
    protected String especialidade;

    Profissional() {}

    public Profissional (String nome, String email, String cpf, String telefone){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Profissional (String nome, String email, String cpf, String telefone, String cro_crm, String especialidade){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cro_crm = cro_crm;
        this.especialidade = especialidade;
    }

    public void setIdProfissional(long idProfissional) { this.idProfissional = idProfissional; }

    public long getId() { return idProfissional; }

    public String getCro_crm() { return cro_crm; }
    public void setCro_crm(String cro_crm) {
        this.cro_crm = cro_crm;
    }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
