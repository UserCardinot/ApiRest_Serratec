package br.com.projetoIndiv.materiasfaculdade.security.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String cep;

    @OneToMany(mappedBy = "faculdade")
    private List<Endereco> fkEndereco;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "faculdade_roles", joinColumns = @JoinColumn(name = "faculdade_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> faculdadeRole = new HashSet<>();

    public Faculdade() {
    }

    public Faculdade(String campus, String nome, String cep) {
        this.campus = campus;
        this.nome = nome;
        this.cep = cep;
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
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setFaculdadeRole(Set<Role> faculdadeRole) {
        this.faculdadeRole = faculdadeRole;
    }

    public Set<Role> getFaculdadeRole() {
        return faculdadeRole;
    }

    public List<Endereco> getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(List<Endereco> fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}