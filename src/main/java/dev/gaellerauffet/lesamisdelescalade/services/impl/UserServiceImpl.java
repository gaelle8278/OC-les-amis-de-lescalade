package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Role;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.persistance.RoleRepository;
import dev.gaellerauffet.lesamisdelescalade.persistance.UserRepository;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	EntityManager em;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User getUser(int id) {
		
		User user = userRepository.findById(id);
		return user;
	}

	@Override
	public void add(User user, String role) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepository.findByRole(role);
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public void add(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public Page<User> findAllPaginated(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		return page;
	}

	@Override
	public void update(User userForm) {		
		userRepository.save(userForm);
		
	}
	@Override
	public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	

	
	
}
