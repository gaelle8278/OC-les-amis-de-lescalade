package dev.gaellerauffet.lesamisdelescalade.services.impl;

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
	
}
