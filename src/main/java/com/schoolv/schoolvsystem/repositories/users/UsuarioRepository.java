package com.schoolv.schoolvsystem.repositories.users;

import com.schoolv.schoolvsystem.models.users.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByLogin(String login);


}
