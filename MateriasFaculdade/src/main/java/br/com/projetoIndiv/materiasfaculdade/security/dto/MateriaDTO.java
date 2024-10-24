package br.com.projetoIndiv.materiasfaculdade.security.dto;

public class MateriaDTO {

    private String nome;
    private String professor;
    private Integer cargaHoraria;
    private String nomeFaculdade;

    public String getNome() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public String getNomeFaculdade() {
        return nomeFaculdade;
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

    public void setNomeFaculdade(String nomeFaculdade) {
        this.nomeFaculdade = nomeFaculdade;
    }
}