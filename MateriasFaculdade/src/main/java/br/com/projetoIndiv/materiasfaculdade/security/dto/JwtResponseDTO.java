package br.com.projetoIndiv.materiasfaculdade.security.dto;

import java.util.List;

public class JwtResponseDTO {
	
	private String token;
	private String type = "Bearer";
	private Integer id;
	private String username;
	private Integer matricula;
	private List<String> roles;

	public JwtResponseDTO(String accessToken, Integer id, String username, Integer matricula, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.matricula = matricula;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}