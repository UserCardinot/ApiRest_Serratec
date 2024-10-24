package br.com.projetoIndiv.materiasfaculdade.security.dto;

import java.util.List;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;

public class EnderecoFaculdadeDTO {

    private EnderecoResponseDTO endereco;

    private List<Faculdade> faculdades;

    public EnderecoFaculdadeDTO(EnderecoResponseDTO endereco, List<Faculdade> faculdades) {
        this.endereco = endereco;
        this.faculdades = faculdades;
    }

    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDTO endereco) {
        this.endereco = endereco;
    }

    public List<Faculdade> getFaculdades() {
        return faculdades;
    }

    public void setFaculdades(List<Faculdade> faculdades) {
        this.faculdades = faculdades;
    }
}