package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EnderecoResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.JwtResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.LoginRequestDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.MessageResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.SignupEstudRequestDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.SignupFaculRequestDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Endereco;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Role;
import br.com.projetoIndiv.materiasfaculdade.security.enums.RoleEnum;
import br.com.projetoIndiv.materiasfaculdade.security.jwt.JwtUtils;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.EnderecoRepository;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.EstudanteRepository;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.FaculdadeRepository;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.RoleRepository;
import br.com.projetoIndiv.materiasfaculdade.security.services.EnderecoService;
import br.com.projetoIndiv.materiasfaculdade.security.services.EstudanteDetailsImpl;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	EstudanteRepository estudRepository;

	@Autowired
	FaculdadeRepository faculRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signinEstudante")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		EstudanteDetailsImpl userDetails = (EstudanteDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getMatricula(),
						roles));
	}

	@PostMapping("/signupEstudante")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupEstudRequestDTO signUpEstudRequest) {
		if (estudRepository.existsByUsername(signUpEstudRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Username já utilizado!"));
		}

		if (estudRepository.existsByEmail(signUpEstudRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Email já utilizado!"));
		}

		Estudante estud = new Estudante(signUpEstudRequest.getUsername(), signUpEstudRequest.getEmail(),
				encoder.encode(signUpEstudRequest.getPassword()), signUpEstudRequest.getMatricula(),
				signUpEstudRequest.getIdade());

		Set<Role> roles = new HashSet<>();

		Role usuarioRole = roleRepository.findByName(RoleEnum.ROLE_ESTUDANTE)
				.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
		roles.add(usuarioRole);

		estud.setRoles(roles);
		estudRepository.save(estud);

		return ResponseEntity.ok(new MessageResponseDTO("Usuário registrado com sucesso!"));
	}

	@PostMapping("/signupFaculdade")
	public ResponseEntity<?> registerFaculdade(@Valid @RequestBody SignupFaculRequestDTO signUpFaculRequest) {

		// Verificar se o campus já existe
		if (faculRepository.existsByCampus(signUpFaculRequest.getCampus())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Campus já utilizado!"));
		}

		// Criar a nova instância da Faculdade
		Faculdade facul = new Faculdade();
		facul.setNome(signUpFaculRequest.getNome());
		facul.setCampus(signUpFaculRequest.getCampus());
		facul.setCep(signUpFaculRequest.getCep());

		// Definir as roles da faculdade
		Set<Role> roles = new HashSet<>();
		Role usuarioRole = roleRepository.findByName(RoleEnum.ROLE_FACULDADE)
				.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
		roles.add(usuarioRole);
		facul.setFaculdadeRole(roles);

		// Salvar a faculdade para obter o ID
		facul = faculRepository.save(facul);

		// Buscar informações do endereço usando o CEP
		EnderecoResponseDTO enderecoResponse = enderecoService.buscarEndereco(signUpFaculRequest.getCep());

		// Criar uma nova instância de Endereco com as informações retornadas do ViaCEP
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoResponse.getCep());
		endereco.setLogradouro(enderecoResponse.getLogradouro());
		endereco.setBairro(enderecoResponse.getBairro());
		endereco.setLocalidade(enderecoResponse.getLocalidade());
		endereco.setEstado(enderecoResponse.getEstado());
		endereco.setFaculdade(facul);

		// Salvar o endereço no banco de dados
		enderecoRepository.save(endereco);

		// Adicionar o endereço à lista de endereços da faculdade e salvar a faculdade
		// novamente
		facul.getEnderecos().add(endereco);
		faculRepository.save(facul);

		return ResponseEntity.ok(new MessageResponseDTO("Faculdade registrada com sucesso!"));
	}

}