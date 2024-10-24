package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Foto;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FotoRepository extends JpaRepository<Foto, Integer> {

}