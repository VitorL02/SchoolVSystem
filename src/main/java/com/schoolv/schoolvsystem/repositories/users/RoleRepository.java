package com.schoolv.schoolvsystem.repositories.users;

import com.schoolv.schoolvsystem.models.users.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Long> {
}
