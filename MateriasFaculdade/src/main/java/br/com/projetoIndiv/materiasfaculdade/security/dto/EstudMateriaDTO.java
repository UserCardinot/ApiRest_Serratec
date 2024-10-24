package br.com.projetoIndiv.materiasfaculdade.security.dto;

public class EstudMateriaDTO {

    private String nomeEstudante;
    private String nomeMateria;

    public String getNomeEstudante() {
        return nomeEstudante;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeEstudante(String nomeEstudante) {
        this.nomeEstudante = nomeEstudante;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }
}