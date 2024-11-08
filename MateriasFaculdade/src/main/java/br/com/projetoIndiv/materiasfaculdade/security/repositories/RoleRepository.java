package br.com.projetoIndiv.materiasfaculdade.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Role;
import br.com.projetoIndiv.materiasfaculdade.security.enums.RoleEnum;

@Repository("role")
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);

	Boolean existsByName(RoleEnum name);
}