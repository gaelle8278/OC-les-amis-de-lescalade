package dev.gaellerauffet.lesamisdelescalade.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;

public interface AreaService {

	Area getArea(int id);

	Page<Area> findAllPaginated(Pageable pageable);

	void deleteArea(int id);

	int add(int spotId, Area area);

	List<Route> getListRoutes(int areaId);

	void update(int areaId, Area area);

	Spot getParentSpot(int areaId);

}
