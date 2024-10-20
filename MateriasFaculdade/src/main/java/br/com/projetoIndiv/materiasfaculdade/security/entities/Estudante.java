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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Estudantes")
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Est_cd_id")
	private int id;

	@NotBlank
	@Column(name = "Est_txt_nome")
	private String nome;

	@NotBlank
	@Column(name = "Est_txt_username")
	private String username;

	@NotBlank
	@Column(name = "Est_txt_password")
	private String password;
	
	@NotBlank
	@Email
	@Column(name = "Est_txt_email")
	private String email;

    @NotBlank
    @Size(max = 20)
	@Column(name = "Est_int_matricula")
    private int matricula;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "estudante_roles", joinColumns = @JoinColumn(name = "estudante_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> roles = new HashSet<>();

	public Estudante() {
	}

	public Estudante(String nome, String username, String password, String email, int matricula) {
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.email = email;
        this.matricula = matricula;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

    public int getMatricula() {
        return matricula;
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}