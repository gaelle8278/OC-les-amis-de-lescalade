package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;

@Repository
public interface GuidebookRepository extends JpaRepository<Guidebook, Integer> {
	Guidebook findById(int id);
}
