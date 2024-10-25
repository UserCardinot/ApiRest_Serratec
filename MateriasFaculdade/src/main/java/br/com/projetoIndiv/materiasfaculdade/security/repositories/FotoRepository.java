package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Foto;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FotoRepository extends JpaRepository<Foto, Integer> {
    @Query(value = "select * from foto where usuario_id = :idEstudante", nativeQuery = true)
    Foto buscarFotoByIdUser(Integer idEstudante);

}