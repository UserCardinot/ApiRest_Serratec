package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EstudanteDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;
import br.com.projetoIndiv.materiasfaculdade.security.services.EmailService;
import br.com.projetoIndiv.materiasfaculdade.security.services.EstudanteService;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

	@Autowired
	EmailService emailService;

	@Autowired
	EstudanteService estudanteService;

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