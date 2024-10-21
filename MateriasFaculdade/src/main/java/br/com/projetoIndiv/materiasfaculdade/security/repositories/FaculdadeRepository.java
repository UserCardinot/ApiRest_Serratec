package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Faculdade;

@Repository("faculdade")
public interface FaculdadeRepository extends JpaRepository<Faculdade, Integer> {
	Optional<Faculdade> findById(Integer id);

	List<Faculdade> findAll();

	Faculdade save(Faculdade faculdade);

	//Faculdade update(Integer id, Faculdade faculdade);

	// Faculdade delete(Integer id);
}
