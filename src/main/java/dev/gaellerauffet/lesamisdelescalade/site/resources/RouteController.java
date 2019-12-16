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
import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.services.AreaService;
import dev.gaellerauffet.lesamisdelescalade.services.RouteService;

@Controller
public class RouteController {
	@Autowired
    RouteService routeService;

	
	@GetMapping("/route/{id}")
	public String displayRoute(@PathVariable("id") int id, Model model) {
		Route route = routeService.getRoute(id);
		model.addAttribute("route", route);
        return "route/display";
	}
	
	
	@Secured("ROLE_USER")
	@GetMapping("/areas/{areaId}/routes")
    public String displayAddForm(@PathVariable("areaId") int areaId, Route route, Model model) {
		model.addAttribute("areaId", areaId);
		model.addAttribute("route",route);
        return "route/add";
    }
	
	@Secured("ROLE_USER")
	@PostMapping("/areas/{areaId}/routes")
    public String addRoute(@PathVariable("areaId") int areaId, @Valid Route route, BindingResult result) {
        if (result.hasErrors()) {
            return "route/add";
        }
        
        //save spot and redirect
        int routeId = routeService.add(areaId, route);
        return "redirect:/routes/edit/"+ routeId;
    }
	
	@Secured("ROLE_USER")
	@GetMapping("/routes/edit/{routeId}")
	public String displayUpdateForm(@PathVariable("routeId") int routeId, Model model) {
		Route route = routeService.getRoute(routeId);
	    model.addAttribute("route", route);
	    List<Pitch> listPitches = routeService.getListPitches(routeId);
	    model.addAttribute("listPitches", listPitches);
	    model.addAttribute("areaId", route.getArea().getId());
	    
	    return "route/edit";
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/routes/update/{routeId}")
	public String updateRoute(@PathVariable("routeId") int routeId, @Valid Route route, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	route.setId(routeId);
	        return "route/edit";
	    }
	         
	    routeService.update(routeId, route);
	    
	    Area area = routeService.getParentArea(routeId);
	    
	 
	    //after update it return to area parent edition
	    return "redirect:/areas/edit/" + area.getId();
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/routes/delete/{routeId}")
	public String deleteRoute(@PathVariable("routeId") int routeId, Model model) {
		Area area = routeService.getParentArea(routeId);
		routeService.deleteRoute(routeId);
		
		//after deletion its returns to it area parent edition
	    return "redirect:/areas/edit/" + area.getId();
	}

}
