package br.com.projetoIndiv.materiasfaculdade.security.dto;

import java.util.Set;

public class SignupFaculRequestDTO {

    private String nome;
    private String campus;

    private String cep;

    private Set<String> role;

    public String getNome() {
        return nome;
    }

    public String getCampus() {
        return campus;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
