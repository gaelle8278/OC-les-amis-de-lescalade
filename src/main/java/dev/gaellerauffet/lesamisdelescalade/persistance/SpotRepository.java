package dev.gaellerauffet.lesamisdelescalade.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gaellerauffet.lesamisdelescalade.model.Spot;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
		Spot findById(int id);
		
		List<Spot> findByNameContains(String name);

		List<Spot> findByRegionContains(String region);

		List<Spot> findByRegionContainsAndNameContains(String region, String name);

}


