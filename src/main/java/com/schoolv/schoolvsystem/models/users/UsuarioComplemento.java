package com.schoolv.schoolvsystem.models.users;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_usuario_complemento")
public class UsuarioComplemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_complemento")
    private Long idUsuarioComplemento;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "data_de_cadastro")
    private Date dataDeCadastro;

    @Column(name = "telefone")
    private String telefone;
    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id")
    private Usuario usuario;

    public UsuarioComplemento(String nome, String sobrenome, Date dataDeCadastro, String telefone,Usuario usuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataDeCadastro = dataDeCadastro;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public UsuarioComplemento() {
    }

    public Long getIdUsuarioComplemento() {
        return idUsuarioComplemento;
    }

    public void setIdUsuarioComplemento(Long idUsuarioComplemento) {
        this.idUsuarioComplemento = idUsuarioComplemento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
