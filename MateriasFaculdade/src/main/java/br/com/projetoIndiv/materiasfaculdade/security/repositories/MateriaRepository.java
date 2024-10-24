package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Materias;

@Repository
public interface MateriaRepository extends JpaRepository<Materias, Integer> {
    List<Materias> findByNome(String nome);

    List<Materias> findAll();

    Boolean existsByNome(String nome);
}