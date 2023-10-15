package com.schoolv.schoolvsystem.repositories.school;

import com.schoolv.schoolvsystem.models.school.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia,Long> {
}
