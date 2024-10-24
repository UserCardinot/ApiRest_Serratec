package br.com.projetoIndiv.materiasfaculdade.security.dto;

public class MateriaDTO {

    private String nome;
    private String professor;
    private Integer cargaHoraria;

    public String getNome() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}