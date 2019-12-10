package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.persistance.RouteRepository;
import dev.gaellerauffet.lesamisdelescalade.services.RouteService;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	RouteRepository routeRepository;

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
		routeRepository.deleteById(id);;
		
	}

	@Override
	public void add(@Valid Route route) {
		routeRepository.save(route);
		
	}

}
