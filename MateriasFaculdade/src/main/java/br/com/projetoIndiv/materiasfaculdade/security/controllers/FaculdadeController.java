package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EnderecoResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;
import br.com.projetoIndiv.materiasfaculdade.security.services.EnderecoService;
import br.com.projetoIndiv.materiasfaculdade.security.services.FaculdadeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/faculdade")
public class FaculdadeController {

	@Autowired
	EnderecoService enderecoService;

	@Autowired
    private FaculdadeService faculdadeService;
	
	@GetMapping
	public List<Faculdade> getALLFaculdades() {
		return faculdadeService.findAll();
	}

	 @GetMapping("/{id}")
	public Faculdade getFaculdade(@PathVariable Integer id) {
		return faculdadeService.findById(id);
	}

	@PostMapping
	public Faculdade createFaculdade(@RequestBody Faculdade faculdade) {
		return faculdadeService.save(faculdade);
	}

	// @PutMapping("/{id}")
	// public Faculdade updateFaculdade(@PathVariable Integer id, @RequestBody Faculdade faculdade) {
	// 	return faculdadeService.update(id, faculdade);
	// }

	// @DeleteMapping("/{id}")
	// public void deleteFaculdade(@PathVariable Integer id) {
	// 	faculdadeService.delete(id);
	// }

	@PostMapping("/BuscarCep")
	public EnderecoResponseDTO buscarCep(@RequestParam String cep) {
		return enderecoService.buscarEndereco(cep);
	}
}