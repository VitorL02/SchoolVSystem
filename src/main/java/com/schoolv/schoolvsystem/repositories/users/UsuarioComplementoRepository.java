package com.schoolv.schoolvsystem.repositories.users;

import com.schoolv.schoolvsystem.models.users.UsuarioComplemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioComplementoRepository extends JpaRepository<UsuarioComplemento,Long> {
    UsuarioComplemento findByUsuario_Login(String subject);
}
