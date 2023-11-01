package com.schoolv.schoolvsystem.models.users;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="tb_usuarios")
@Entity(name="Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id_usuario")
    private Roles role;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role != null && ( role.getRoles().equalsIgnoreCase("PROFESSOR") || role.getRoles().equalsIgnoreCase("STAFF"))){
            return List.of(new SimpleGrantedAuthority("STAFF"),new SimpleGrantedAuthority("ROLE_STAFF"));
        }else if(role != null && role.getRoles().equalsIgnoreCase("ADMIN")) {
            return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return List.of(new SimpleGrantedAuthority("USER"),new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}