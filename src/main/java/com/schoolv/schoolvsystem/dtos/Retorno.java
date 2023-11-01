package com.schoolv.schoolvsystem.dtos;

import org.springframework.http.HttpStatus;

public class Retorno {

    private String status;
    private String mensagem;

    private Object objeto;

    public Retorno(HttpStatus status, String mensagem) {
        this.status = String.valueOf(status);
        this.mensagem = mensagem;
    }

    public Retorno(HttpStatus status, String mensagem, Object objeto) {
        this.status = String.valueOf(status);
        this.mensagem = mensagem;
        this.objeto = objeto;
    }

    public Retorno() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}
