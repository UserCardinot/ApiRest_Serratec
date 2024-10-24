package br.com.projetoIndiv.materiasfaculdade.security.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoIndiv.materiasfaculdade.security.dto.MateriaDTO;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Materias;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.FaculdadeRepository;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.MateriaRepository;

@Service
public class MateriaService {

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    FaculdadeRepository faculdadeRepository;

    public List<Materias> findAll() {
        return materiaRepository.findAll();
    }

    public List<Materias> findByNome(String nome) {
        return materiaRepository.findByNome(nome);
    }

    public Materias save(MateriaDTO materiaDTO) {
        Materias materia = new Materias();
        materia.setNome(materiaDTO.getNome());
        materia.setCargaHoraria(materiaDTO.getCargaHoraria());
        materia.setProfessor(materiaDTO.getProfessor());

        return materiaRepository.save(materia);
    }

    public Materias createMateria(MateriaDTO materiaDTO) {
        Materias materia = new Materias();
        materia.setNome(materiaDTO.getNome());
        materia.setCargaHoraria(materiaDTO.getCargaHoraria());
        materia.setProfessor(materiaDTO.getProfessor());

        Integer faculdadeName = Integer.parseInt(materiaDTO.getNomeFaculdade());
        Faculdade faculdade = faculdadeRepository.findById(faculdadeName)
                .orElseThrow(() -> new RuntimeException("Faculdade não encontrada"));

        Set<Faculdade> faculdades = new HashSet<>();
        faculdades.add(faculdade);
        materia.setFaculdades(faculdades);

        return materiaRepository.save(materia);
    }

    public Materias update(Integer id, MateriaDTO materiaDTO) {
        Materias materiaUpdate = materiaRepository.findById(id).get();

        materiaUpdate.setNome(materiaDTO.getNome());
        materiaUpdate.setCargaHoraria(materiaDTO.getCargaHoraria());
        materiaUpdate.setProfessor(materiaDTO.getProfessor());

        return materiaRepository.save(materiaUpdate);
    }

    public void delete(Integer id) {
        materiaRepository.deleteById(id);
    }

}
