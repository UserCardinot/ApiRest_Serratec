package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;

@Repository("faculdade")
public interface FaculdadeRepository extends JpaRepository<Faculdade, Integer> {
	Optional<Faculdade> findByNome(String nome);

	Boolean existsByNome(String nome);

	Boolean existsByCampus(String campus);
}
