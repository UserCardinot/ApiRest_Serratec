package br.com.projetoIndiv.materiasfaculdade.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.EstudanteRepository;

@Service
public class EstudanteService {

    @Autowired
    EstudanteRepository estudanteRepository;

    public List<Estudante> findAll() {
        return estudanteRepository.findAll();
    }

    public Estudante findById(Integer id) {
        return estudanteRepository.findById(id).get();
    }

    public Estudante save(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public void delete(Integer id) {
        estudanteRepository.deleteById(id);
    }

    public Estudante update(Integer id, Estudante estudante) {
        return estudanteRepository.save(estudante);
    }
}
