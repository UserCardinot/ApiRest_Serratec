package br.com.projetoIndiv.materiasfaculdade.security.dto;

public class ViaCepResponseDTO {

    private String logradouro;
    private String bairro;
    private String localidade;
    private String cep;
    private String estado;

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }
}