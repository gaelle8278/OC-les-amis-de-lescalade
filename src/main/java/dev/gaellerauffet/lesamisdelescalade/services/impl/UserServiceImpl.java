package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.Role;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
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
	public void add(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepository.findByRole("ROLE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		//@Todo before check foreign key constraint to delete manually depenencies 
		//or add delete dependencies on foreign key in database 
		userRepository.deleteById(id);
	}

	@Override
	public void updateUser(User user) {
		
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
	public void update(int idUser, User userForm) {
		User user = em.getReference(User.class,idUser);
		//on update of a user information checkCGU status doesn't change
		//active state doesn't change, it change only during user activation process
		userForm.setCheckedCGU(user.isCheckedCGU());
		userForm.setActive(user.isActive());
		
		userRepository.save(userForm);
		
	}
	@Override
	public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	
	
}
