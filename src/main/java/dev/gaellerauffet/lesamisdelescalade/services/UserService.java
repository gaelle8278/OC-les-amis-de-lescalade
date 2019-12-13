package dev.gaellerauffet.lesamisdelescalade.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;

public interface UserService {

	User getUser(int i);
	
	List<User> getAllUsers();
	
	void add(User user);
	
	void updateUser(User user);

	void deleteUser(int id);

	Page<User> findAllPaginated(Pageable pageable);

	void update(int id, @Valid User user);

	User findUserByEmail(String name);

	
	
	//obtenir les sites d'un user
	//obtenir les topos d'un User
	//obtenir les pret fait par un user
	
	//obtenir les topos disponibles pour le pret

}
