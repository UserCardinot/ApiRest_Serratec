package br.com.projetoIndiv.materiasfaculdade.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.FaculdadeRepository;

@Service
public class FaculdadeService {

    @Autowired
    private FaculdadeRepository faculdadeRepository;

    public List<Faculdade> findAll() {
        return faculdadeRepository.findAll();
    }

    public Faculdade findById(Integer id) {
        return faculdadeRepository.findById(id).get();
    }

    public Faculdade save(Faculdade faculdade) {
        return faculdadeRepository.save(faculdade);
    }

    // public Faculdade update(Integer id, Faculdade faculdade) {
    //     return faculdadeRepository.save(faculdade);
    // }

    // public void delete(Integer id) {
    //     faculdadeRepository.deleteById(id);
    // }
}