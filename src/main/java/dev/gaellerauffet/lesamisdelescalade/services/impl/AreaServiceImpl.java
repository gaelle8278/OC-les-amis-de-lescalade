package dev.gaellerauffet.lesamisdelescalade.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
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

	@Override
	public Area getArea(int id) {
		Area area = areaRepository.findById(id);
		
		return area;
	}

	@Override
	public void add(Area area) {
		areaRepository.save(area);
		
	}

	@Override
	public Page<Area> findAllPaginated(Pageable pageable) {
		Page<Area> page = areaRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public void deleteArea(int id) {
		areaRepository.deleteById(id);
		
	}

	@Override
	public void add(int spotId, Area area) {
		Spot spot = spotRepository.findById(spotId);
		area.setSpot(spot);
		areaRepository.save(area);
		// TODO Auto-generated method stub
		
	}

}
