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

		List<Spot> findByRegionContains(String region);

		List<Spot> findByRegionContainsAndNameContains(String region, String name);

		Page<Spot> findAllByUser(User user, Pageable pageable);

}


