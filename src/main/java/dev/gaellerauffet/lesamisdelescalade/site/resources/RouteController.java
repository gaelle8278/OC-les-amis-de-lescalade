package dev.gaellerauffet.lesamisdelescalade.site.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
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
	
	@GetMapping("/les-routes")
	public String listRoutes(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Route> page = routeService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "route/list";
	}
	
	
	@GetMapping("/addroute")
    public String displayAddForm(Route route, Model model) {
        return "route/add";
    }
	
	@PostMapping("/route/add")
    public String addRoute(@Valid Route route, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "route/add";
        }
        
        //save spot and redirect
        routeService.add(route);
        return "redirect:/les-voies";
    }
	
	
	@GetMapping("/route/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
		Route route = routeService.getRoute(id);
	    model.addAttribute("route", route);
	    
	    return "route/edit";
	}
	
	@PostMapping("/route/update/{id}")
	public String updateRpute(@PathVariable("id") int id, @Valid Route route, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        return "route/edit";
	    }
	         
	    routeService.add(route);
	    return "redirect:/les-voies";
	}
	
	@GetMapping("/route/delete/{id}")
	public String deleteRoute(@PathVariable("id") int id, Model model) {
		routeService.deleteRoute(id);
		
		return "redirect:/les-voies";
	}

}
