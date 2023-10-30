package com.schoolv.schoolvsystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistroUsuarioDTO {

    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "O campo email deve ser um email")
    private String email;

    @NotBlank(message = "A senha não pode ser nula ou vazia")
    @Size(min = 6,message = "Senha deve conter no minimo 6 caracteres")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
