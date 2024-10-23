package br.com.projetoIndiv.materiasfaculdade.security.dto;

public class FaculdadeDTO {

    private Integer id;
    private String nome;
    private String campus;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCampus() {
        return campus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

}
