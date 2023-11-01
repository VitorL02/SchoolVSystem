package com.schoolv.schoolvsystem;

import com.schoolv.schoolvsystem.infra.exceptions.ExceptionGenerica;
import com.schoolv.schoolvsystem.models.users.UsuarioRecuperarSenha;
import com.schoolv.schoolvsystem.repositories.users.RecuperarSenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduleJob {
    @Autowired
    private RecuperarSenhaRepository recuperarSenhaRepository;

    @Scheduled(fixedDelay = 30000)
    public void limpaTabelaRecuperacaoSenha() throws InterruptedException{
        try{
            List<UsuarioRecuperarSenha> listaCodigosExpirados = new ArrayList<>();
            System.out.println(new StringBuilder().append("*** Deletando registros solicitação recuperação de senha " +
                    "***").append(new Date()).toString());
            List<UsuarioRecuperarSenha> listaUsuariosSolicitacao = recuperarSenhaRepository.findAll();
            retornaListaCodigosExpirados(listaUsuariosSolicitacao,listaCodigosExpirados);
            if(listaCodigosExpirados != null && !listaCodigosExpirados.isEmpty()){
                recuperarSenhaRepository.deleteAll(listaCodigosExpirados);
            }
            System.out.println(new StringBuilder().append("*** Finalizando  ***").append(new Date()).toString());

        }catch (Exception e){
            throw new ExceptionGenerica(new StringBuilder().append("Erro ao limpar tabela de recuperação de senha ").append(e).toString());
        }
    }

    private List<UsuarioRecuperarSenha> retornaListaCodigosExpirados(List<UsuarioRecuperarSenha> listaUsuariosSolicitacao, List<UsuarioRecuperarSenha> listaCodigosExpirados) {
        if(listaUsuariosSolicitacao != null && !listaUsuariosSolicitacao.isEmpty())
        for (UsuarioRecuperarSenha codigoUnico :listaUsuariosSolicitacao ){
            Date dataCadastro = codigoUnico.getDataCadastro();
            Date dataAtual = new Date();
            Long diferencaEmMinutos = TimeUnit.MILLISECONDS.toMinutes(dataAtual.getTime() - dataCadastro.getTime());
            if(diferencaEmMinutos >= 5){
                listaCodigosExpirados.add(codigoUnico);
            }
        }
        return listaCodigosExpirados;
    }


}
