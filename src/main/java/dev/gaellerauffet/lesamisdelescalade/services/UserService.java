package dev.gaellerauffet.lesamisdelescalade.services;

import java.util.List;

import dev.gaellerauffet.lesamisdelescalade.model.User;

public interface UserService {

	User getUser(int i);
	
	List<User> getAllUsers();
	
	void addUser(User user);
	
	
	void updateUser(User user);

	void deleteUser(int id);
	
	//obtenir les sites d'un user
	//obtenir les topos d'un User
	//obtenir les pret fait par un user
	
	//obtenir les topos disponibles pour le pret

}
