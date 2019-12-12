package dev.gaellerauffet.lesamisdelescalade.services;


import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.form.SpotSearchForm;


public interface SpotService {
	List<Spot> getAllSpots();

	Page<Spot> findAllPaginated(Pageable pageable);

	List<Spot> getSpotsByName(String name) ;

	List<String> getListRegionsForForm();

	List<Spot> getSpotsByRegion(String region);

	List<Spot> getSpotsForSearchCriteria(SpotSearchForm spotsearchform);

	int add(int userId, Spot spot);

	List<String> getListTypesForForm();

	Spot getSpot(int id);

	void deleteSpot(int id);

	List<Area> getListAreas(int id);

	void update(int id, @Valid Spot spot);

	List<Comment> getListComment(Spot spot);

	Page<Spot> getUserSpots(int i, Pageable pageable);
	
}
