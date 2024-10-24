package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EnderecoFaculdadeDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.EnderecoResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.FaculdadeDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.MessageResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.FaculdadeRepository;
import br.com.projetoIndiv.materiasfaculdade.security.services.EnderecoService;
import br.com.projetoIndiv.materiasfaculdade.security.services.FaculdadeService;

@RestController
@RequestMapping("/faculdade")
public class FaculdadeController {

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	FaculdadeService faculdadeService;

	@Autowired
	FaculdadeRepository faculdadeRepository;

	@GetMapping
	public List<Faculdade> getALLFaculdades() {
		return faculdadeService.findAll();
	}

	@GetMapping("/{id}")
	public Faculdade getFaculdadeById(@PathVariable Integer id) {
		return faculdadeService.findById(id);
	}

	@PutMapping("/{id}")
	public void updateFaculdade(@PathVariable Integer id, @RequestBody FaculdadeDTO faculdadeDTO) {
		faculdadeService.update(id, faculdadeDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteFaculdade(@PathVariable Integer id) {
		faculdadeService.delete(id);
	}

	@GetMapping("/EnderecoFaculByCep")
	public ResponseEntity<?> buscarCep(@RequestParam String cep) {
		EnderecoResponseDTO enderecoResponse = enderecoService.buscarEndereco(cep);

		if (enderecoResponse == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponseDTO("Erro: Endereço não encontrado!"));
		}

		List<Faculdade> faculdades = faculdadeRepository.findByEnderecoCep(cep);

		if (faculdades.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponseDTO("Erro: Nenhuma faculdade encontrada para o CEP!"));
		}

		EnderecoFaculdadeDTO resultado = new EnderecoFaculdadeDTO(enderecoResponse, faculdades);
		return ResponseEntity.ok(resultado);
	}
}