package com.schoolv.schoolvsystem.repositories.school;

import com.schoolv.schoolvsystem.models.school.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia,Long> {
}
