package com.schoolv.schoolvsystem.models.users;

import com.schoolv.schoolvsystem.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuario_roles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_roles")
    private Long idRoleUsuario;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id")
    private Usuario usuario;
    @Column(name = "role_usuario")
    private String roles;

    @Column(name = "usuario_ativo")
    private boolean isUsuarioAtivo;

    public Roles(Usuario usuario, String roles,boolean isUsuarioAtivo) {
        this.usuario = usuario;
        this.roles = roles;
        this.isUsuarioAtivo = isUsuarioAtivo;
    }


}
