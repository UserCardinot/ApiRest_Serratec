package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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