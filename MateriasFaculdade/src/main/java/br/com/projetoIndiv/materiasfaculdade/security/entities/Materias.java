package br.com.projetoIndiv.materiasfaculdade.security.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Materias")
public class Materias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Mat_int_id")
    private int id;

    @NotBlank
    @Column(name = "Mat_txt_nome")
    private String nome;

    @NotBlank
    @Column(name = "Mat_txt_professor")
    private String professor;

    @Column(name = "Mat_int_cargaHoraria")
    private int cargaHoraria;

    @ManyToMany
    @JoinTable(name = "estudante_materia", joinColumns = @JoinColumn(name = "materia_id"), inverseJoinColumns = @JoinColumn(name = "estudante_id"))
    @JsonBackReference
    private Set<Estudante> estudantes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "materia_faculdade", joinColumns = @JoinColumn(name = "materia_id"), inverseJoinColumns = @JoinColumn(name = "faculdade_id"))
    @JsonBackReference
    private Set<Faculdade> faculdades = new HashSet<>();

    public Materias() {
    }

    public Materias(int id, String nome, String professor, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.cargaHoraria = cargaHoraria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Set<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(Set<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public Set<Faculdade> getFaculdades() {
        return faculdades;
    }

    public void setFaculdades(Set<Faculdade> faculdades) {
        this.faculdades = faculdades;
    }
}
