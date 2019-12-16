package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Role;
import dev.gaellerauffet.lesamisdelescalade.persistance.RoleRepository;
import dev.gaellerauffet.lesamisdelescalade.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
    RoleRepository roleRepository;
	
	@Override
	public List<Role> getRoles() {
		List<Role> listRoles = roleRepository.findAll();
		
		return listRoles;
	}

}
