package br.com.projetoIndiv.materiasfaculdade.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EstudanteDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.EstudanteRepository;

@Service
public class EstudanteService {

    @Autowired
    EstudanteRepository estudanteRepository;

    @Autowired
    PasswordEncoder encoder;

    public List<Estudante> findAll() {
        return estudanteRepository.findAll();
    }

    public Estudante findById(Integer id) {
        return estudanteRepository.findById(id).get();
    }

    public Estudante findByUsername(String username) {
        return estudanteRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }

    public Estudante save(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public void delete(Integer id) {
        estudanteRepository.deleteById(id);
    }

    public Estudante update(String username, EstudanteDTO estudanteDTO) {
        Optional<Estudante> estudanteUpdate = estudanteRepository.findByUsername(username);

        if (estudanteUpdate.isPresent()) {
            Estudante estudante = estudanteUpdate.get();
            estudante.setEmail(estudanteDTO.getEmail());
            estudante.setPassword(encoder.encode(estudanteDTO.getPassword()));

            return estudanteRepository.save(estudante);
        } else {
            throw new RuntimeException("Estudante não encontrado");
        }
    }
}