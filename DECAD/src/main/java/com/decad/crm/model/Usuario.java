package com.decad.crm.model;

public class Usuario {
    private long idUsuario;
    private String login;
    private String senha;

    public Usuario() {}

    public Usuario(long idUsuario, String login, String senha) {this.login = login;this.senha = senha;}

    public long getIdUsuario() {return idUsuario;}

    public void setIdUsuario(long idUsuario) {this.idUsuario = idUsuario;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}
}
