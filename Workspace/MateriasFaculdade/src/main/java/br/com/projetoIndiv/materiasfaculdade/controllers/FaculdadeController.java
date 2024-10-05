package br.com.projetoIndiv.materiasfaculdade.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculdade")
public class FaculdadeController {
	
	@GetMapping
	public String faculdade() {
		
		return "OLA MUNDO! ESTAMOS NA CLASSE FACULDADE";
	}
}
