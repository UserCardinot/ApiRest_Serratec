package br.com.projetoIndiv.materiasfaculdade.entities;

public class Faculdade {

    private int id;
    private String nome;
    private String email;
    private String cidade;
    private String estado;
    private String pais;
    
    public Faculdade() {
    }

    public Faculdade(int id, String nome, String email, String cidade, String estado, String pais) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }    
}
