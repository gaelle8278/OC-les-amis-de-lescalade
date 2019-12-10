package dev.gaellerauffet.lesamisdelescalade.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Route;

public interface RouteService {

	Route getRoute(int id);

	Page<Route> findAllPaginated(Pageable pageable);

	void deleteRoute(int id);

	void add(@Valid Route route);

}
