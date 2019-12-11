package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.persistance.GuidebookRepository;
import dev.gaellerauffet.lesamisdelescalade.services.GuidebookService;

@Service
public class GuidebookServiceImpl implements GuidebookService {
	@Autowired
    GuidebookRepository guidebookRepository;
	
	@Autowired
	EntityManager em;
	
	@Override
	public Guidebook getGuidebook(int id) {
		Guidebook gb = guidebookRepository.findById(id);
		return gb;
	}

	@Override
	public Page<Guidebook> findAllPaginated(Pageable pageable) {
		Page<Guidebook> page = guidebookRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public void add(Guidebook gb) {
		
		guidebookRepository.save(gb);
		
	}

	@Override
	public void deleteGuidebook(int id) {
		guidebookRepository.deleteById(id);
		
	}

	@Override
	public void update(int idGb, Guidebook guidebookForm) {
		Guidebook gb = em.getReference(Guidebook.class,idGb);
		//on update of a guidebook information available status doesn't change
		//it change only during booking management
		guidebookForm.setAvailable(gb.isAvailable());
		
		guidebookRepository.save(guidebookForm);
		
		
	}

}
