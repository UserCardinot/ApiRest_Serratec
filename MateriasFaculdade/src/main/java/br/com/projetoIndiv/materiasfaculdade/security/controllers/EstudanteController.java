package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EstudMateriaDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.EstudanteDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Materias;
import br.com.projetoIndiv.materiasfaculdade.security.services.EmailService;
import br.com.projetoIndiv.materiasfaculdade.security.services.EstudanteService;
import br.com.projetoIndiv.materiasfaculdade.security.services.MateriaService;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

	@Autowired
	EmailService emailService;

	@Autowired
	EstudanteService estudanteService;

	@Autowired
	MateriaService materiaService;

	@GetMapping
	public List<Estudante> getALLFaculdades() {
		return estudanteService.findAll();
	}

	@PutMapping("/{username}")
	public void updateFaculdade(@PathVariable String username, @RequestBody EstudanteDTO estudanteDTO) {
		estudanteService.update(username, estudanteDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteFaculdade(@PathVariable Integer id) {
		estudanteService.delete(id);
	}

	@PostMapping("/adicionarMateria")
	public ResponseEntity<?> adicionarMateriaEstudante(@RequestBody EstudMateriaDTO estudMateriaDTO) {

		Estudante estudante = estudanteService.findByUsername(estudMateriaDTO.getNomeEstudante());
		Optional<Estudante> estudanteOptional = Optional.ofNullable(estudante);

		if (!estudanteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudante não encontrado");
		}

		estudante = estudanteOptional.get();

		List<Materias> materiaList = materiaService.findByNome(estudMateriaDTO.getNomeMateria());
		if (materiaList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matéria não encontrada");
		}

		Materias materia = materiaList.get(0);

		estudante.getMaterias().add(materia);

		estudanteService.save(estudante);

		return ResponseEntity.ok("Matéria adicionada ao estudante com sucesso");
	}

	@GetMapping("/emailtest")
	public String estudante() {
		emailService.writerEmail();
		return "Email enviado com sucesso!";
	}

	@GetMapping("/testeemail2")
	public String testeemail2() {
		emailService.writerEmail2();
		return "Email enviado com sucesso!";
	}
}