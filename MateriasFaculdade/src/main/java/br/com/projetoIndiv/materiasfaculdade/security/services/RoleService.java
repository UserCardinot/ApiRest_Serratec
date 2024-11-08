package br.com.projetoIndiv.materiasfaculdade.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Role;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

	public Role save(Role role) {
		return roleRepository.save(role);
	}

	public Role findById(Integer id) {
		return roleRepository.findById(id).orElse(null);
	}

	public void delete(Role role) {
		roleRepository.delete(role);
	}
}