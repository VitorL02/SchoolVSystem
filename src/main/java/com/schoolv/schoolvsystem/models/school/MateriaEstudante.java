package com.schoolv.schoolvsystem.models.school;

import com.schoolv.schoolvsystem.models.students.Estudante;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_materia_estudante")
public class MateriaEstudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia_estudante")
    private Long idMateriaEstudante;
    @OneToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;
    @OneToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;

    public Long getIdMateriaEstudante() {
        return idMateriaEstudante;
    }

    public void setIdMateriaEstudante(Long idMateriaEstudante) {
        this.idMateriaEstudante = idMateriaEstudante;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
}
