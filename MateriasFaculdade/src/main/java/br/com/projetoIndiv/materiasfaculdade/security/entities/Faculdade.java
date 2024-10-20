package br.com.projetoIndiv.materiasfaculdade.security.entities;

import java.util.HashSet;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Faculdades")
public class Faculdade {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Fac_cd_id")
	private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fac_fk_endereco")
    private Endereco fkEndereco;

    @NotBlank
    @Column(name = "Fac_txt_campus")
    private String campus;

    @NotBlank
    @Column(name = "Fac_txt_nome")
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "faculdade_roles", joinColumns = @JoinColumn(name = "estudante_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> faculdadeRole = new HashSet<>();

    public Faculdade() {
    }

    public Faculdade(Integer id, Endereco fkEndereco, String campus, String nome) {
        this.id = id;
        this.fkEndereco = fkEndereco;
        this.campus = campus;
        this.nome = nome;
    }

    public Faculdade(String nome) {
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

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setFaculdadeRole(Set<Role> faculdadeRole) {
        this.faculdadeRole = faculdadeRole;
    }

    public Set<Role> getFaculdadeRole() {
        return faculdadeRole;
    }
}