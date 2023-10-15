package com.schoolv.schoolvsystem.models.school;


import jakarta.persistence.*;

@Entity
@Table(name = "tb_materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long idMateria;

    @Column(name = "materia", nullable = false)
    private String materia;

    @Column(name = "turno_materia", nullable = false)
    private String turnoMateria;


    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTurnoMateria() {
        return turnoMateria;
    }

    public void setTurnoMateria(String turnoMateria) {
        this.turnoMateria = turnoMateria;
    }
}