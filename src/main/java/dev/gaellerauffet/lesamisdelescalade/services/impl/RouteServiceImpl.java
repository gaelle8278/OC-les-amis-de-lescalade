package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.persistance.RouteRepository;
import dev.gaellerauffet.lesamisdelescalade.services.RouteService;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	EntityManager em;

	@Override
	public Route getRoute(int id) {
		Route route = routeRepository.findById(id);
		return route;
	}

	@Override
	public Page<Route> findAllPaginated(Pageable pageable) {
		Page<Route> page = routeRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public void deleteRoute(int id) {
		//@TODO suppression des longueurs filles avant suppression de la voie
		routeRepository.deleteById(id);;
		
	}

	@Override
	public int add(int areaId, Route route) {
		//set foreign dependency 
		Area area = em.getReference(Area.class,areaId);
		route.setArea(area);
		//save route
		Route savedRoute = routeRepository.save(route);
				
		return savedRoute.getId();
		
	}

	@Override
	public List<Pitch> getListPitches(int routeId) {
		Route route = routeRepository.findById(routeId);
		List<Pitch> listPitchs = route.getListPitchs();
		
		return listPitchs;
	}

	@Override
	public void update(int routeId, Route routeForm) {
		Route route = em.getReference(Route.class, routeId);
		//routeform.setCreatedDate(route.getCreatedDate());
		routeForm.setArea(route.getArea());
		//update area
		routeRepository.save(routeForm);
		
	}

	@Override
	public Area getParentArea(int routeId) {
		Route route = em.getReference(Route.class, routeId);
		Area area = route.getArea();
		
		return area;
	}

}
