package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Optional<Endereco> findByLogradouro(String logradouro);

    Optional<Endereco> findByBairro(String bairro);

    Optional<Endereco> findByLocalidade(String localidade);

    Optional<Endereco> findByEstado(String estado);

    Optional<Endereco> findByCep(String cep);   
}
