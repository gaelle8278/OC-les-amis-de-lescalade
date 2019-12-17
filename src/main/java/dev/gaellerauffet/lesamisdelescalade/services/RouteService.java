package dev.gaellerauffet.lesamisdelescalade.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
import dev.gaellerauffet.lesamisdelescalade.model.Route;

public interface RouteService {

	Route getRoute(int id);

	Page<Route> findAllPaginated(Pageable pageable);

	void deleteRoute(int id);

	int add(int areaId, Route route);

	List<Pitch> getListPitches(int routeId);


	Area getParentArea(int routeId);

	void update(Route route);

}
