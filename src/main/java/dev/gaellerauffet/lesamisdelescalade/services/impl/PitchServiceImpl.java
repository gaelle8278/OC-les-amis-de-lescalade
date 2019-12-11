package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.persistance.PitchRepository;
import dev.gaellerauffet.lesamisdelescalade.services.PitchService;

@Service
public class PitchServiceImpl implements PitchService {
	@Autowired
	PitchRepository picthRepository;
	
	@Autowired
	EntityManager em;

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

	@Override
	public void add(int routeId, Pitch pitch) {
		Route route = em.getReference(Route.class,routeId);
		pitch.setRoute(route);
		//save route
		picthRepository.save(pitch);
	}

	@Override
	public void update(int pitchId, Pitch pitchForm) {
		Pitch pitch = em.getReference(Pitch.class, pitchId);
		//pitchform.setCreatedDate(pitch.getCreatedDate());
		pitchForm.setRoute(pitch.getRoute());
		//update pitch
		picthRepository.save(pitchForm);
	}

	@Override
	public Route getParentRoute(int pitchId) {
		Pitch pitch = em.getReference(Pitch.class, pitchId);
		Route route = pitch.getRoute();
		
		return route;
	}
	
	

}
