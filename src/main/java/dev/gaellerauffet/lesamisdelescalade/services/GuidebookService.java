package dev.gaellerauffet.lesamisdelescalade.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;

public interface GuidebookService {

	Guidebook getGuidebook(int id);

	Page<Guidebook> findAllPaginated(Pageable pageable);

	void add(Guidebook gb);

	void deleteGuidebook(int id);

	void update(int idGb, @Valid Guidebook guidebook);

	Page<Guidebook> getUserGuidebooks(Pageable pageable);

	boolean isBookingAvalaible(Guidebook guidebook);

}
