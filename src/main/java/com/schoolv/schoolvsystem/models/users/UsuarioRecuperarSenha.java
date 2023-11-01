package com.schoolv.schoolvsystem.models.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_solicitacao_nova_senha")
public class UsuarioRecuperarSenha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_roles")
    private Long idRoleUsuario;

    @Column(name = "email_usuario_solicitacao")
    private String emailRecuperacao;

    @Column(name = "codigo_solicitacao_senha")
    private String codigoRecuperacao;

    public Long getIdRoleUsuario() {
        return idRoleUsuario;
    }

    public void setIdRoleUsuario(Long idRoleUsuario) {
        this.idRoleUsuario = idRoleUsuario;
    }

    public String getEmailRecuperacao() {
        return emailRecuperacao;
    }

    public void setEmailRecuperacao(String emailRecuperacao) {
        this.emailRecuperacao = emailRecuperacao;
    }

    public String getCodigoRecuperacao() {
        return codigoRecuperacao;
    }

    public void setCodigoRecuperacao(String codigoRecuperacao) {
        this.codigoRecuperacao = codigoRecuperacao;
    }
}
