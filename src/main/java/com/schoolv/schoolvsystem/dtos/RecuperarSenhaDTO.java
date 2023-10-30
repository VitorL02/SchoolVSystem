package com.schoolv.schoolvsystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecuperarSenhaDTO {

    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "O campo email deve ser um email")
    private String emailRecuperacao;
    @NotBlank(message = "O codigo não pode ser nulo ou vazio")
    @Size(min = 7,message = "O codigo deve conter no minimo 7 caracteres")
    private String codigoRecuperacao;

    @NotBlank(message = "A senha não pode ser nula ou vazia")
    @Size(min = 6,message = "Senha deve conter no minimo 6 caracteres")
    private String novaSenha;


    public String getCodigoRecuperacao() {
        return codigoRecuperacao;
    }

    public void setCodigoRecuperacao(String codigoRecuperacao) {
        this.codigoRecuperacao = codigoRecuperacao;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getEmailRecuperacao() {
        return emailRecuperacao;
    }

    public void setEmailRecuperacao(String emailRecuperacao) {
        this.emailRecuperacao = emailRecuperacao;
    }
}
