package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
	Area findById(int id);

	Page<Area> findBySpot(Spot spot, Pageable pageable);
}

