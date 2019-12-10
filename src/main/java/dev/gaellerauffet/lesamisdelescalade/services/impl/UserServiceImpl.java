package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.persistance.UserRepository;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserRepository userRepository;
	
	@Override
	public User getUser(int id) {
		User user = userRepository.findById(id);
		return user;
	}

	@Override
	public void add(User user) {
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

	
	
}
