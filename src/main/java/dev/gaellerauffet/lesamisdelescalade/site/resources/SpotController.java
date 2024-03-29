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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.form.SpotSearchForm;
import dev.gaellerauffet.lesamisdelescalade.services.SpotService;

@Controller
@SessionAttributes("spotsearchform")
public class SpotController {
	@Autowired
    SpotService spotService;
	
	@Autowired
    SpotSearchForm spotsearchform;
	
	@GetMapping("/spot/{id}")
	public String displaySpot(@PathVariable("id") int id, Comment comment, Model model) {
		Spot spot = spotService.getSpot(id);
		
		model.addAttribute("spot", spot);
		model.addAttribute("comment", comment);
        return "spot/display";
	}
	
	@GetMapping("/les-sites")
	public String listSpots(@PageableDefault(size = 10) Pageable pageable, Model model) {
		model.addAttribute("spotsearchform", new SpotSearchForm());
		
		List<String> listRegions =  spotService.getListRegionsForForm();
		model.addAttribute("listRegions", listRegions);
		
		Page<Spot> page = spotService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "spot/list";
	}
	
	@RequestMapping("/les-sites/filtered")
    public String listSpotsFiltered( @ModelAttribute("spotsearchform") SpotSearchForm spotsearchform, BindingResult result, @PageableDefault(size = 10) Pageable pageable, Model model) {
		//recherche des sites selon les critères de recherche
		Page<Spot> foundedSpots = spotService.getSpotsForSearchCriteria(spotsearchform, pageable);
		if(foundedSpots == null) {
			return "redirect:/les-sites";
		}
		model.addAttribute("page", foundedSpots);
		List<String> listRegions =  spotService.getListRegionsForForm();
		model.addAttribute("listRegions", listRegions);
		
        return "spot/list";
    }
	
	@Secured("ROLE_USER")
	@GetMapping("/membre/mes-sites")
	public String listUserSpots(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Spot> page = spotService.getUserSpots(pageable);
		model.addAttribute("page", page);
        return "spot/user-list";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/les-sites")
	public String listAdminSpots(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Spot> page = spotService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "spot/admin-list";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/addspot")
    public String displayAddForm(Spot spot, Model model) {
		List<String> listTypes =  spotService.getListTypesForForm();
		model.addAttribute("listTypes", listTypes);
        return "spot/add";
    }
	
	@Secured("ROLE_USER")
	@PostMapping("/spot/add")
    public String addSpot(@Valid Spot spot, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	List<String> listTypes =  spotService.getListTypesForForm();
    		model.addAttribute("listTypes", listTypes);
            return "spot/add";
        }
        
        //save spot and redirect to spot edition
        int id = spotService.add(spot);
        return "redirect:/spot/edit/" + id;
    }
	
	
	@Secured("ROLE_USER")
	@GetMapping("/spot/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
	    Spot spot = spotService.getSpot(id);
	    List<String> listTypes =  spotService.getListTypesForForm();
	    model.addAttribute("listTypes", listTypes);
	    model.addAttribute("spot", spot);
	    
	    return "spot/edit";
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/spot/update/{id}")
	public String updateSpot(@PathVariable("id") int id, @Valid Spot spot, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	List<String> listTypes =  spotService.getListTypesForForm();
	    	model.addAttribute("listTypes", listTypes);
	        return "spot/edit";
	    }
	         
	    spotService.update(spot);
	    return "redirect:/membre/mes-sites";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/spot/delete/{id}")
	public String deleteSpot(@PathVariable("id") int id, Model model) {
	    spotService.deleteSpot(id);
	    
	    return "redirect:/membre/mes-sites";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/spot/tag/add/{id}")
	public String addTagSpot(@PathVariable("id") int id) {
	    spotService.addTagSpot(id);
	    
	    return "redirect:/admin/les-sites";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/spot/tag/delete/{id}")
	public String deleteTagSpot(@PathVariable("id") int id) {
	    spotService.deleteTagSpot(id);
	    
	    return "redirect:/admin/les-sites";
	}
	
	 
}
