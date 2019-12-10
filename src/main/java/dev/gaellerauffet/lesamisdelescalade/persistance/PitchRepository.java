package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Pitch;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, Integer> {
	Pitch findById(int id);
}
