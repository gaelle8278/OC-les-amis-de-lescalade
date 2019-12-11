package dev.gaellerauffet.lesamisdelescalade.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
import dev.gaellerauffet.lesamisdelescalade.model.Route;

public interface PitchService {

	Pitch getPitch(int id);

	Page<Pitch> findAllPaginated(Pageable pageable);

	void add(@Valid Pitch pitch);

	void deletePitch(int id);

	void add(int pitchId, Pitch pitch);

	void update(int pitchId, Pitch pitch);

	Route getParentRoute(int pitchId);

}
