package dev.gaellerauffet.lesamisdelescalade.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
import dev.gaellerauffet.lesamisdelescalade.model.Route;

public interface PitchService {

	Pitch getPitch(int id);

	Page<Pitch> findAllPaginated(Pageable pageable);

	void add(Pitch pitch);

	void deletePitch(int id);

	void add(int pitchId, Pitch pitch);

	Route getParentRoute(int pitchId);

	void update(Pitch pitch);

}
