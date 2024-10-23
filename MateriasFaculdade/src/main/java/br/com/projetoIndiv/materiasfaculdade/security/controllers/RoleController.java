package br.com.projetoIndiv.materiasfaculdade.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Role;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.RoleRepository;
import br.com.projetoIndiv.materiasfaculdade.security.services.RoleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	RoleService roleService;

	@Autowired
	RoleRepository roleRepository;

	@PostMapping
	public ResponseEntity<Role> save(@RequestBody Role role) {

		if (roleRepository.existsByName(role.getName())) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		Role newRole = roleService.save(role);
		if (newRole != null)
			return new ResponseEntity<>(newRole, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(newRole, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Role> delete(@PathVariable Integer id) {
		Role role = roleService.findById(id);
		if (role != null) {
			roleService.delete(role);
			return new ResponseEntity<>(role, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(role, HttpStatus.NOT_FOUND);
		}
	}
}