package dev.gaellerauffet.lesamisdelescalade.persistance;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
		Spot findById(int id);
		
		List<Spot> findByNameContains(String name);
		Page<Spot> findByNameContains(String name, Pageable pageable);

		List<Spot> findByRegionContains(String region);
		Page<Spot> findByRegionContains(String region, Pageable pageable);

		List<Spot> findByRegionContainsAndNameContains(String region, String name);
		Page<Spot> findByRegionContainsAndNameContains(String region, String name, Pageable pageable);

		Page<Spot> findAllByUser(User user, Pageable pageable);

		List<Spot> findByName(String name);

		Page<Spot> findByNbAreasGreaterThan(int nbAreas, Pageable pageable);

		Page<Spot> findByNbRoutesGreaterThan(int nbRoutes, Pageable pageable);

		Page<Spot> findByNbPitchesGreaterThan(int nbPitches, Pageable pageable);

		Page<Spot> findByMinHeightGreaterThan(int minHeight, Pageable pageable);

		Page<Spot> findByMaxHeightGreaterThan(int maxHeight, Pageable pageable);

		Page<Spot> findByMinGrade(String grade, Pageable pageable);

}


