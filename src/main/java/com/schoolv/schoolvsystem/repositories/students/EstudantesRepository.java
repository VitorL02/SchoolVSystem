package com.schoolv.schoolvsystem.repositories.students;

import com.schoolv.schoolvsystem.models.students.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudantesRepository extends JpaRepository<Estudante,Long> {
}
