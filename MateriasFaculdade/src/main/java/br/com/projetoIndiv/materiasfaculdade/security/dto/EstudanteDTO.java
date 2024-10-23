package br.com.projetoIndiv.materiasfaculdade.security.dto;

public class EstudanteDTO {

    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}