package br.com.projetoIndiv.materiasfaculdade.security.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "End_cd_id")
    private Integer id;

    @Column(name = "End_txt_logradouro")
    private String logradouro;

    @Column(name = "End_txt_bairro")
    private String bairro;

    @Column(name = "End_txt_localidade")
    private String localidade;

    @Column(name = "End_txt_estado")
    private String estado;

    @Column(name = "End_txt_cep")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "Fac_cd_id", nullable = false)
    private Faculdade faculdade;

    public Endereco() {
    }

    public Endereco(Integer id, String logradouro, String bairro, String localidade, String estado, String cep, Faculdade faculdade) {
        this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.estado = estado;
        this.cep = cep;
        this.faculdade = faculdade;
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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

    public Faculdade getFaculdade() {
        return faculdade;
    }
}