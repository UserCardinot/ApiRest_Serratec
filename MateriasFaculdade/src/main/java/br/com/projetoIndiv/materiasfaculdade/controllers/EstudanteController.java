package br.com.projetoIndiv.materiasfaculdade.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {
	
	@GetMapping
	public String estudante() {
		
		return "OLA MUNDO! ESTAMOS NA CLASSE ESTUDANTE";
	}
}