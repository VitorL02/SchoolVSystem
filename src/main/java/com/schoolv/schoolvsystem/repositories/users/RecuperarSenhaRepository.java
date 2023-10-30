package com.schoolv.schoolvsystem.repositories.users;

import com.schoolv.schoolvsystem.models.users.UsuarioRecuperarSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecuperarSenhaRepository extends JpaRepository<UsuarioRecuperarSenha,Long> {
    UsuarioRecuperarSenha findByEmailRecuperacaoAndCodigoRecuperacao(String emailRecuperacao, String codigoRecuperacao);
}
