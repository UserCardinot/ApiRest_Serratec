package br.com.projetoIndiv.materiasfaculdade.security.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Faculdades")
public class Faculdade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Fac_cd_id")
    private Integer id;

    @Column(name = "Fac_txt_campus")
    private String campus;

    @Column(name = "Fac_txt_nome")
    private String nome;

    @Column(name = "Fac_txt_cep")
    private String enderecoCep;

    @OneToMany(mappedBy = "faculdade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "faculdade_roles", joinColumns = @JoinColumn(name = "faculdade_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonBackReference
    private Set<Role> faculdadeRole = new HashSet<>();

    public Faculdade() {
        this.enderecos = new ArrayList<>();
    }

    public Faculdade(String campus, String nome) {
        this.campus = campus;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCep() {
        return enderecoCep;
    }

    public void setCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }

    public void setFaculdadeRole(Set<Role> faculdadeRole) {
        this.faculdadeRole = faculdadeRole;
    }

    public Set<Role> getFaculdadeRole() {
        return faculdadeRole;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}