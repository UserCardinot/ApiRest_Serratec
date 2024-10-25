package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
import br.com.projetoIndiv.materiasfaculdade.security.services.EmailService;
import br.com.projetoIndiv.materiasfaculdade.security.services.EnderecoService;
import br.com.projetoIndiv.materiasfaculdade.security.services.EstudanteDetailsImpl;
import br.com.projetoIndiv.materiasfaculdade.security.services.FotoService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	FotoService fotoService;

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
	EmailService emailService;

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
	public ResponseEntity<?> registerUser(@Valid @RequestPart SignupEstudRequestDTO signUpEstudRequest,
			@RequestPart MultipartFile foto) throws Exception {
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
		fotoService.cadastrarFoto(foto, estud);

		try {
			emailService.emailPersonalizadoSignUp(signUpEstudRequest);
		} catch (IOException e) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro ao enviar email"));
		}

		return ResponseEntity.ok(new MessageResponseDTO("Usuário registrado com sucesso!"));
	}

	@PostMapping("/signupFaculdade")
	public ResponseEntity<?> registerFaculdade(@Valid @RequestBody SignupFaculRequestDTO signUpFaculRequest) {

		if (faculRepository.existsByCampus(signUpFaculRequest.getCampus())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Campus já utilizado!"));
		}

		Faculdade facul = new Faculdade();
		facul.setNome(signUpFaculRequest.getNome());
		facul.setCampus(signUpFaculRequest.getCampus());
		facul.setCep(signUpFaculRequest.getCep());

		Set<Role> roles = new HashSet<>();
		Role usuarioRole = roleRepository.findByName(RoleEnum.ROLE_FACULDADE)
				.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
		roles.add(usuarioRole);
		facul.setFaculdadeRole(roles);

		facul = faculRepository.save(facul);

		EnderecoResponseDTO enderecoResponse = enderecoService.buscarEndereco(signUpFaculRequest.getCep());

		Endereco endereco = new Endereco();
		endereco.setCep(enderecoResponse.getCep());
		endereco.setLogradouro(enderecoResponse.getLogradouro());
		endereco.setBairro(enderecoResponse.getBairro());
		endereco.setLocalidade(enderecoResponse.getLocalidade());
		endereco.setEstado(enderecoResponse.getEstado());
		endereco.setFaculdade(facul);

		enderecoRepository.save(endereco);

		facul.getEnderecos().add(endereco);
		faculRepository.save(facul);

		return ResponseEntity.ok(new MessageResponseDTO("Faculdade registrada com sucesso!"));
	}

	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> consultarFoto(@PathVariable Integer id) throws Exception {

		byte[] foto = fotoService.getFoto(id);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(foto);
	}
}