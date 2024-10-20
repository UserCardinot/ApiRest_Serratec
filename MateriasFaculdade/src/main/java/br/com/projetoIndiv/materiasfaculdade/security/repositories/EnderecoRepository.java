package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Optional<Endereco> findByRua(String rua);

    Boolean existsByRua(String rua);

    Boolean existsByBairro(String bairro);

    Boolean existsByCidade(String cidade);

    Boolean existsByEstado(String estado);

    Boolean existsByCep(String cep);
}
