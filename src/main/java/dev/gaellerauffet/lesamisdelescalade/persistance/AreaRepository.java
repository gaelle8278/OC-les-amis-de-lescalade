package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
	Area findById(int id);
}

