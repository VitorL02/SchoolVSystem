package com.schoolv.schoolvsystem;

import com.schoolv.schoolvsystem.infra.exceptions.ExceptionGenerica;
import com.schoolv.schoolvsystem.models.users.UsuarioRecuperarSenha;
import com.schoolv.schoolvsystem.repositories.users.RecuperarSenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleJob {
    @Autowired
    private RecuperarSenhaRepository recuperarSenhaRepository;

    @Scheduled(fixedDelay = 300000)
    public void limpaTabelaRecuperacaoSenha() throws InterruptedException{
        try{
            System.out.println("*** Deletando registros solicitação recuperação de senha ***");
            List<UsuarioRecuperarSenha> listaUsuariosSolicitacao = recuperarSenhaRepository.findAll();
            if(listaUsuariosSolicitacao != null && !listaUsuariosSolicitacao.isEmpty()){
                recuperarSenhaRepository.deleteAll(listaUsuariosSolicitacao);
            }
            System.out.println("*** Finalizando  ***");

        }catch (Exception e){
            throw new ExceptionGenerica(new StringBuilder().append("Erro ao limpar tabela de recuperação de senha ").append(e).toString());
        }
    }

}
