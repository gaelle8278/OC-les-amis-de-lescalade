package dev.gaellerauffet.lesamisdelescalade.services.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.model.form.SpotSearchForm;
import dev.gaellerauffet.lesamisdelescalade.persistance.SpotRepository;
import dev.gaellerauffet.lesamisdelescalade.services.SpotService;

@Service
public class SpotServiceImpl implements SpotService {
	@Autowired
    SpotRepository spotRepository;
	
	@Override
	public Spot getSpot(int id) {
		Spot spot = spotRepository.findById(id);
		
		return spot;
	}
	
	@Override
	public List<Spot> getAllSpots() {
		List<Spot> spots = spotRepository.findAll();
		return spots;
	}
	
	@Override
	public Page<Spot> findAllPaginated(Pageable pageable) {
		Page<Spot> page = spotRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public List<Spot> getSpotsByName(String name) {
		List<Spot> list = spotRepository.findByNameContains(name);
		
		return list;
	}

	@Override
	public List<String> getListRegionsForForm() {
		List<Spot> spots = spotRepository.findAll();
		List<String> regions = new ArrayList<String>();
		regions.add("- Choississez une région -");
		if(! spots.isEmpty()) {
			for(Spot spot : spots) {
				if(spot.getRegion() != null && ! regions.contains(spot.getRegion())) {
					regions.add(spot.getRegion());
				}
				
			}
			//List<String> listUniqueRegions = regions.stream().distinct().collect(Collectors.toList());
			Collections.sort(regions);
		}
		
		
		return regions;
	}

	@Override
	public List<Spot> getSpotsByRegion(String region) {
		List<Spot> list = spotRepository.findByRegionContains(region);
		
		return list;
	}

	@Override
	public List<Spot> getSpotsForSearchCriteria(SpotSearchForm spotsearchform) {
		List<Spot> foundedSpots = new ArrayList<Spot>();
		if(! spotsearchform.getRegion().contentEquals("- Choississez une région -") && ! spotsearchform.getName().contentEquals("")) {
			foundedSpots = spotRepository.findByRegionContainsAndNameContains(spotsearchform.getRegion(), spotsearchform.getName());
		} else if (! spotsearchform.getRegion().contentEquals("- Choississez une région -") ) {
			foundedSpots = spotRepository.findByRegionContains(spotsearchform.getRegion());
		} else if ( ! spotsearchform.getName().contentEquals("") ) {
			foundedSpots = spotRepository.findByNameContains(spotsearchform.getName());
		}
		return foundedSpots;
	}

	@Override
	public int add(Spot spot) {
		//@TODO before save get id of connected user
		Spot savedSpot = spotRepository.save(spot);
		return savedSpot.getId();
	}

	@Override
	public List<String> getListTypesForForm() {
		List<String> types = new ArrayList<String>();
		types.add("Falaise");
		types.add("Bloc");
		types.add("Bloc, Falaise");
		return types;
	}

	@Override
	public void deleteSpot(int id) {
		//@TODO supprimmer les secteurs fils avant de supprimer le site
		spotRepository.deleteById(id);
	}

	@Override
	public List<Area> getListAreas(int id) {
		Spot spot = spotRepository.findById(id);
		List<Area> listAreas = spot.getListArea();
		
		return listAreas;
	}

	@Override
	public void update(int id, Spot spotForm) {
		spotRepository.save(spotForm);
		
	}
}
