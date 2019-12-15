package dev.gaellerauffet.lesamisdelescalade.services.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.model.form.SpotSearchForm;
import dev.gaellerauffet.lesamisdelescalade.persistance.SpotRepository;
import dev.gaellerauffet.lesamisdelescalade.services.SpotService;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

import dev.gaellerauffet.lesamisdelescalade.utils.Helpers;

@Service
public class SpotServiceImpl implements SpotService {
	@Autowired
    SpotRepository spotRepository;
	
	@Autowired
    UserService userService;
	
	@Autowired
	EntityManager em;
	
	
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
		regions.add(" ");
		if(! spots.isEmpty()) {
			for(Spot spot : spots) {
				if(spot.getRegion() != null && ! spot.getRegion().isEmpty() && ! regions.contains(spot.getRegion())) {
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
		if(! spotsearchform.getRegion().isEmpty() && ! spotsearchform.getName().isEmpty()) {
			foundedSpots = spotRepository.findByRegionContainsAndNameContains(spotsearchform.getRegion(), spotsearchform.getName());
		} else if (! spotsearchform.getRegion().isEmpty()) {
			foundedSpots = spotRepository.findByRegionContains(spotsearchform.getRegion());
		}
		if ( ! spotsearchform.getName().isEmpty() ) {
			foundedSpots = spotRepository.findByNameContains(spotsearchform.getName());
		}
		return foundedSpots;
	}
	
	@Override
	public Page<Spot> getSpotsForSearchCriteria(SpotSearchForm spotsearchform, Pageable pageable) {
		Page<Spot> foundedSpots = null;
		
		
		if ( ! StringUtils.isBlank(spotsearchform.getName()) ) {
			foundedSpots = spotRepository.findByNameContains(spotsearchform.getName(), pageable);
		} else if (! StringUtils.isBlank(spotsearchform.getRegion())) {
			foundedSpots = spotRepository.findByRegionContains(spotsearchform.getRegion(), pageable);
		} else if ( ! spotsearchform.getGrade().isEmpty() ) {
			foundedSpots = spotRepository.findByMinGrade(spotsearchform.getGrade(), pageable);
		} else if ( ! Helpers.isIntEmpty(spotsearchform.getNbAreas() ) ) {
			foundedSpots = spotRepository.findByNbAreasGreaterThan(spotsearchform.getNbAreas(), pageable);
			
		} else if ( ! Helpers.isIntEmpty(spotsearchform.getNbRoutes() ) ) {
			
			foundedSpots = spotRepository.findByNbRoutesGreaterThan(spotsearchform.getNbRoutes(), pageable);
			
		} else if ( ! Helpers.isIntEmpty(spotsearchform.getNbPitches()) ) {
			foundedSpots = spotRepository.findByNbPitchesGreaterThan(spotsearchform.getNbPitches(), pageable);
		} else if ( ! Helpers.isIntEmpty(spotsearchform.getMinHeight()) ) {
			foundedSpots = spotRepository.findByMinHeightGreaterThan(spotsearchform.getMinHeight(), pageable);
		}  else if ( ! Helpers.isIntEmpty(spotsearchform.getMaxHeight()) ) {
			foundedSpots = spotRepository.findByMaxHeightGreaterThan(spotsearchform.getMaxHeight(), pageable);
		}
		
		
		
		//@TODO créer des combinaisons => queryDSL , JPA API Criteria ?
		return foundedSpots;
	}


	@Override
	public int add(Spot spot) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		spot.setUser(user);
		
		Spot savedSpot = spotRepository.save(spot);
		return savedSpot.getId();
	}

	@Override
	public List<String> getListTypesForForm() {
		List<String> types = new ArrayList<String>();
		//@TODO in v2 add management of types in admin back-office
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
	public void update(int spotId, Spot spotForm) {
		Spot spot = em.getReference(Spot.class, spotId);
		spotForm.setUser(spot.getUser());
		spotRepository.save(spotForm);
		
	}

	@Override
	public List<Comment> getListComment(Spot spot) {
		List<Comment>listComments = spot.getListComment();
		return listComments;
	}

	@Override
	public Page<Spot> getUserSpots( Pageable pageable) {
		//User user = em.getReference(User.class, userId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Page<Spot> listSpots = spotRepository.findAllByUser(user, pageable);
		return listSpots;
	}

	@Override
	public void addTagSpot(int spotId) {
		Spot spot = em.getReference(Spot.class, spotId);
		spot.setTag(true);
		
		spotRepository.save(spot);
		
	}

	@Override
	public void deleteTagSpot(int spotId) {
		Spot spot = em.getReference(Spot.class, spotId);
		spot.setTag(false);
		
		spotRepository.save(spot);
		
	}
}
