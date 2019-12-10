package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
import dev.gaellerauffet.lesamisdelescalade.persistance.PitchRepository;
import dev.gaellerauffet.lesamisdelescalade.services.PitchService;

@Service
public class PitchServiceImpl implements PitchService {
	@Autowired
	PitchRepository picthRepository;

	@Override
	public Pitch getPitch(int id) {
		Pitch pitch = picthRepository.findById(id);
		return pitch;
	}

	@Override
	public Page<Pitch> findAllPaginated(Pageable pageable) {
		Page<Pitch> page = picthRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public void add(@Valid Pitch pitch) {
		picthRepository.save(pitch);
		
	}

	@Override
	public void deletePitch(int id) {
		picthRepository.deleteById(id);
		
	}

}
