package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;

@Repository("estudante")
public interface EstudanteRepository extends JpaRepository<Estudante, Integer> {
	Optional<Estudante> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}