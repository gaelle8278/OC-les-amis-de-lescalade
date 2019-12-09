package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		User user = userRepository.findById(id);
				///.orElseThrow(() -> new IllegalArgumentException("Id non trouvé:" + id));
		
		userRepository.delete(user);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	
}
