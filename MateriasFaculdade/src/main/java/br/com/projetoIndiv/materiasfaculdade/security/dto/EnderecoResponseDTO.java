package br.com.projetoIndiv.materiasfaculdade.security.dto;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Endereco;

public class EnderecoResponseDTO {

    private String logradouro;
    private String bairro;
    private String localidade;
    private String estado;
    private String cep;

    public Endereco toEntity() {
        Endereco endereco = new Endereco();

        endereco.setLogradouro(this.logradouro);
        endereco.setBairro(this.bairro);
        endereco.setLocalidade(this.localidade);
        endereco.setEstado(this.estado);
        endereco.setCep(this.cep);

        return endereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}