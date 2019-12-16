package dev.gaellerauffet.lesamisdelescalade.site.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.services.AreaService;

@Controller
public class AreaController {
	@Autowired
    AreaService areaService;

	
	@GetMapping("/area/{id}")
	public String displayArea(@PathVariable("id") int id, Model model) {
		Area area = areaService.getArea(id);
		model.addAttribute("area", area);
        return "area/display";
	}
	
	
	
	// display add an area form
	@Secured("ROLE_USER")
	@GetMapping("/spots/{spotId}/areas")
    public String displayAddForm(@PathVariable("spotId") int spotId, Area area, Model model) {
		model.addAttribute("spotId", spotId);
		model.addAttribute("area",area);
        return "area/add";
    }
	
	//save a new area
	@Secured("ROLE_USER")
	@PostMapping("/spots/{spotId}/areas")
    public String addArea(@PathVariable("spotId") int spotId, @Valid Area area, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "area/add";
        }
        
        //save spot and redirect to area edition
        int areaId = areaService.add(spotId, area);
        return "redirect:/areas/edit/" + areaId;
    }
	
	// display an update area form
	@Secured("ROLE_USER")
	@GetMapping("/areas/edit/{areaId}")
	public String displayUpdateForm(@PathVariable("areaId") int areaId, Model model) {
	    Area area = areaService.getArea(areaId);
	    List<Route> listRoutes = areaService.getListRoutes(areaId);
	    //Spot spot = areaService.getParentSpot(areaId);
	    model.addAttribute("area", area);
	    model.addAttribute("listRoutes", listRoutes);
	    //model.addAttribute("spot", spot);
	    
	    model.addAttribute("spotId", area.getSpot().getId());
	    
	    return "area/edit";
	}
	
	//save updates
	@Secured("ROLE_USER")
	@PostMapping("/areas/update/{areaId}")
	public String updateArea(@PathVariable("areaId") int areaId, @Valid Area area, BindingResult result) {
	    if (result.hasErrors()) {
	    	area.setId(areaId);
	        return "area/edit";
	    }
	         
	    areaService.update(areaId, area);
	    
	    Spot spot = areaService.getParentSpot(areaId);
	    //model.addAttribute("spotId", spot.getId());
	 
	    //after update it return to spot parent edition
	    return "redirect:/spot/edit/" + spot.getId();
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/areas/delete/{areaId}")
	public String deleteArea(@PathVariable("areaId") int areaId) {
		Spot spot = areaService.getParentSpot(areaId);
		areaService.deleteArea(areaId);
		
		//after deletion its returns to it spot parent edition
	    return "redirect:/spot/edit/" + spot.getId();
	}
}
