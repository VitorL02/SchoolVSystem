package com.schoolv.schoolvsystem.repositories.school;

import com.schoolv.schoolvsystem.models.school.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
}
