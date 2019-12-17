package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.persistance.AreaRepository;
import dev.gaellerauffet.lesamisdelescalade.persistance.SpotRepository;
import dev.gaellerauffet.lesamisdelescalade.services.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	SpotRepository spotRepository;
	
	@Autowired
	EntityManager em;
	

	@Override
	public Area getArea(int id) {
		Area area = areaRepository.findById(id);
		
		return area;
	}


	@Override
	public Page<Area> findAllPaginated(Pageable pageable) {
		Page<Area> page = areaRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public void deleteArea(int id) {
		//@Todo suppression des voies filles avant la suppression du secteur parent
		areaRepository.deleteById(id);
		
	}

	@Override
	public int add(int spotId, Area area) {
		//set foreign dependency : fetch spot thanks to spotId parameter
		Spot spot = em.getReference(Spot.class,spotId);
		//Spot spot = spotRepository.findById(spotId);
		area.setSpot(spot);
		//save area
		Area savedArea = areaRepository.save(area);
		
		return savedArea.getId();
		
	}

	@Override
	public List<Route> getListRoutes(int areaId) {
		Area area = areaRepository.findById(areaId);
		List<Route> listRoutes = area.getListRoutes();
		
		return listRoutes;
	}


	@Override
	public void update(Area areaForm) {
		Area area = em.getReference(Area.class, areaForm.getId());
		areaForm.setSpot(area.getSpot());
		//update area
		areaRepository.save(areaForm);
		
		
	}


	@Override
	public Spot getParentSpot(int areaId) {
		Area area = em.getReference(Area.class, areaId);
		Spot spot = area.getSpot();
		
		return spot;
		
	}


	@Override
	public Page<Area> getListAreasOfSpot(int spotId, Pageable pageable) {
		Spot spot = em.getReference(Spot.class, spotId);
		Page<Area> page = areaRepository.findBySpot(spot, pageable);
		return page;
	}

}
