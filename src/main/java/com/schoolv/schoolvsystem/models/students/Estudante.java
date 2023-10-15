package com.schoolv.schoolvsystem.models.students;

import com.schoolv.schoolvsystem.models.school.Curso;
import com.schoolv.schoolvsystem.models.school.Frequencia;
import com.schoolv.schoolvsystem.models.school.Materia;
import com.schoolv.schoolvsystem.models.school.MateriaEstudante;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_estudante")
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudante")
    private Long idEstudante;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "matricula", nullable = false)
    private Long matricula;

    @Column(name = "data_de_cadastro")
    private Date dataCadastro;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;


    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_frequencia")
    private Frequencia frequencia;

    @OneToMany
    @JoinColumn(name = "id_estudante")
    private List<MateriaEstudante> materiaEstudantes;

    public Long getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(Long idEstudante) {
        this.idEstudante = idEstudante;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    public List<MateriaEstudante> getMateriaEstudantes() {
        return materiaEstudantes;
    }

    public void setMateriaEstudantes(List<MateriaEstudante> materiaEstudantes) {
        this.materiaEstudantes = materiaEstudantes;
    }
}