package dev.gaellerauffet.lesamisdelescalade.persistance;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findByLastName(String lastName);
	User findById(int id);
}
