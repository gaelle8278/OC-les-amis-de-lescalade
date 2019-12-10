package dev.gaellerauffet.lesamisdelescalade.site.resources;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.form.SpotSearchForm;
import dev.gaellerauffet.lesamisdelescalade.services.SpotService;

@Controller
public class SpotController {
	@Autowired
    SpotService spotService;

	
	@GetMapping("/spot/{id}")
	public String displaySpot(@PathVariable("id") int id, Model model) {
		Spot spot = spotService.getSpot(id);
		model.addAttribute("spot", spot);
        return "spot/display";
	}
	
	@GetMapping("/les-sites")
	public String listSpots(@PageableDefault(size = 10) Pageable pageable, Model model) {
		//model.addAttribute("spots", spotService.getAllSpots());
		Page<Spot> page = spotService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "spot/list";
	}
	
	@GetMapping("/recherche-site")
    public String search( Model model) {
		model.addAttribute("spotsearchform", new SpotSearchForm());
		//liste des régions
		List<String> listRegions =  spotService.getListRegionsForForm();
		model.addAttribute("listRegions", listRegions);
        return "spot/search";
    }
	
	@PostMapping("/recherche-site")
    public String search( @Valid @ModelAttribute("spotsearchform") SpotSearchForm spotsearchform, BindingResult result, Model model) {
		//recherche des sites selon les critères de recherche
		List<Spot> foundedSpots = spotService.getSpotsForSearchCriteria(spotsearchform);
		model.addAttribute("foundedSpots", foundedSpots);
		//liste des régions
		List<String> listRegions =  spotService.getListRegionsForForm();
		model.addAttribute("listRegions", listRegions);
		
        return "spot/search";
    }
	
	@GetMapping("/addspot")
    public String displayAddForm(Spot spot, Model model) {
		List<String> listTypes =  spotService.getListTypesForForm();
		model.addAttribute("listTypes", listTypes);
        return "spot/add";
    }
	
	@PostMapping("/spot/add")
    public String addSpot(@Valid Spot spot, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	List<String> listTypes =  spotService.getListTypesForForm();
    		model.addAttribute("listTypes", listTypes);
            return "spot/add";
        }
        
        //save spot and redirect
        int id = spotService.add(spot);
        return "redirect:/spot/edit/" + id;
    }
	
	
	@GetMapping("/spot/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
	    Spot spot = spotService.getSpot(id);
	    List<String> listTypes =  spotService.getListTypesForForm();
		List<Area> listAreas = spotService.getListAreas(id);
	    model.addAttribute("listTypes", listTypes);
	    model.addAttribute("spot", spot);
	    model.addAttribute("listAreas", listAreas);
	    
	    return "spot/edit";
	}
	
	@PostMapping("/spot/update/{id}")
	public String updateSpot(@PathVariable("id") int id, @Valid Spot spot, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        //spot.setId(id);
	        return "spot/edit";
	    }
	         
	    spotService.add(spot);
	    return "redirect:/les-sites";
	}
	
	@GetMapping("/spot/delete/{id}")
	public String deleteSpot(@PathVariable("id") int id, Model model) {
	    spotService.deleteSpot(id);
	    
	    return "redirect:/les-sites";
	}
	
	 
}
