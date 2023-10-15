package com.schoolv.schoolvsystem.models.users;


import jakarta.persistence.*;

@Table(name="tb_usuarios")
@Entity(name="Usuario")
public class Usuario {


    private Long id;
    private String login;
    private String senha;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
