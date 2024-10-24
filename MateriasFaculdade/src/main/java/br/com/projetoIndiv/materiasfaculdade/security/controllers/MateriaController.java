package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.dto.MateriaDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Materias;
import br.com.projetoIndiv.materiasfaculdade.security.services.MateriaService;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @GetMapping
    public List<Materias> getALLMaterias() {
        return materiaService.findAll();
    }

    @GetMapping("/{name}")
    public List<Materias> getMateriaByNome(String nome) {
        return materiaService.findByNome(nome);
    }

    @PostMapping
    public Materias saveMateria(MateriaDTO materiaDTO) {
        return materiaService.save(materiaDTO);
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
