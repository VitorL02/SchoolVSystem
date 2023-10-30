package com.schoolv.schoolvsystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GerarRecuperacaoSenhaDTO {
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "O campo email deve ser um email")
    private String emailRecuperacao;

    public String getEmailRecuperacao() {
        return emailRecuperacao;
    }

    public void setEmailRecuperacao(String emailRecuperacao) {
        this.emailRecuperacao = emailRecuperacao;
    }
}
