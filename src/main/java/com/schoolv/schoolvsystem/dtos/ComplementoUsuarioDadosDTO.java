package com.schoolv.schoolvsystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ComplementoUsuarioDadosDTO {
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "O sobrenome não pode ser vazio")
    private String sobrenome;
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
