package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.dto.MateriaDTO;
import br.com.projetoIndiv.materiasfaculdade.security.dto.MessageResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Materias;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.FaculdadeRepository;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.MateriaRepository;
import br.com.projetoIndiv.materiasfaculdade.security.services.FaculdadeService;
import br.com.projetoIndiv.materiasfaculdade.security.services.MateriaService;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @Autowired
    FaculdadeService faculdadeService;

    @Autowired
    FaculdadeRepository faculdadeRepository;

    @Autowired
    MateriaRepository materiaRepository;

    @GetMapping
    public List<Materias> getALLMaterias() {
        return materiaService.findAll();
    }

    @GetMapping("/{name}")
    public List<Materias> getMateriaByNome(String nome) {
        return materiaService.findByNome(nome);
    }

    @PostMapping("/createMateria")
    public ResponseEntity<?> saveMateria(MateriaDTO materiaDTO) {

        if (materiaRepository.existsByNome(materiaDTO.getNome())) {
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Materia já cadastrada!"));
        }

        Materias materia = new Materias();
        materia.setNome(materiaDTO.getNome());
        materia.setCargaHoraria(materiaDTO.getCargaHoraria());
        materia.setProfessor(materiaDTO.getProfessor());

        Set<Faculdade> faculdades = new HashSet<>();
        List<Faculdade> faculdadesList = faculdadeRepository.findByNome(materiaDTO.getNomeFaculdade());
        if (!faculdadesList.isEmpty()) {
            faculdades.addAll(faculdadesList);
        }

        materia.setFaculdades(faculdades);
        materiaRepository.save(materia);

        return ResponseEntity.ok(new MessageResponseDTO("Materia registrada com sucesso!"));
    }

    @PutMapping("/{id}")
    public Materias updateMateria(@PathVariable Integer id, @RequestBody MateriaDTO materiaDTO) {
        return materiaService.update(id, materiaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMateria(Integer id) {
        materiaService.delete(id);
    }

}
