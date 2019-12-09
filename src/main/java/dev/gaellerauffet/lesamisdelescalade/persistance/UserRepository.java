package dev.gaellerauffet.lesamisdelescalade.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByLastName(String lastName);
	User findById(int id);
}
