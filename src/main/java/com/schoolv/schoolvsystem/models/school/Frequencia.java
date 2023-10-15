package com.schoolv.schoolvsystem.models.school;


import com.schoolv.schoolvsystem.models.students.Estudante;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_frequencia")
public class Frequencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_frequencia")
    private Long idFrequencia;

    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;

    @Column(name = "data_frequencia", nullable = false)
    private Date dataFrequencia;

    @Column(name = "falta_justificada", nullable = false)
    private boolean faltaJustificada;

    public Long getIdFrequencia() {
        return idFrequencia;
    }

    public void setIdFrequencia(Long idFrequencia) {
        this.idFrequencia = idFrequencia;
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

    public Date getDataFrequencia() {
        return dataFrequencia;
    }

    public void setDataFrequencia(Date dataFrequencia) {
        this.dataFrequencia = dataFrequencia;
    }

    public boolean isFaltaJustificada() {
        return faltaJustificada;
    }

    public void setFaltaJustificada(boolean faltaJustificada) {
        this.faltaJustificada = faltaJustificada;
    }
}
