package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.User;

@Repository
public interface GuidebookRepository extends JpaRepository<Guidebook, Integer> {
	Guidebook findById(int id);

	Page<Guidebook> findAllByUser(User user, Pageable pageable);
	
	
}
