package br.com.projetoIndiv.materiasfaculdade.security.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

@Entity
@Table(name = "Estudantes")
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Est_cd_id")
	private int id;

	@Column(name = "Est_txt_username")
	private String username;

	@Column(name = "Est_txt_password")
	private String password;

	@Column(name = "Est_txt_email")
	private String email;

	@Column(name = "Est_int_matricula")
	private int matricula;

	@Column(name = "Est_int_idade")
	private int idade;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "estudante_roles", joinColumns = @JoinColumn(name = "estudante_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonBackReference
	private Set<Role> roles = new HashSet<>();

	public Estudante() {
	}

	public Estudante(String username, String email, String password, int matricula, int idade) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.matricula = matricula;
		this.idade = idade;
	}

	public int getId() {
		return id;
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

	public int getIdade() {
		return idade;
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

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}