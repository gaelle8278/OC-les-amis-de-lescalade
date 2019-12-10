package dev.gaellerauffet.lesamisdelescalade.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Area;

public interface AreaService {

	Area getArea(int id);

	void add(Area area);

	Page<Area> findAllPaginated(Pageable pageable);

	void deleteArea(int id);

	void add(int spotId, Area area);

}
