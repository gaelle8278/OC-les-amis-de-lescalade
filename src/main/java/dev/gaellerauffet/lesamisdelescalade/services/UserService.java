package dev.gaellerauffet.lesamisdelescalade.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.User;

public interface UserService {

	User getUser(int i);
	
	List<User> getAllUsers();

	void delete(int id);

	Page<User> findAllPaginated(Pageable pageable);

	void update(User user);

	User findUserByEmail(String name);

	void add(User user, String role);
	void add(User user);


}
