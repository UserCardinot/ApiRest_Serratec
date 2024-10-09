package br.com.projetoIndiv.materiasfaculdade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.services.EmailService;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

	@Autowired
	EmailService emailService;
	
	@GetMapping
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