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
	
	@GetMapping("/les-secteurs")
	public String listAreas(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Area> page = areaService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "area/list";
	}
	
	
	@GetMapping("/spots/{spotId}/areas")
    public String displayAddForm(@PathVariable("spotId") int spotId, Area area, Model model) {
		model.addAttribute("spotId", spotId);
		model.addAttribute("area",area);
        return "area/add";
    }
	
	@PostMapping("/spots/{spotId}/areas")
    public String addArea(@PathVariable("spotId") int spotId, @Valid Area area, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "area/add";
        }
        
        //save spot and redirect
        areaService.add(spotId, area);
        return "redirect:/spot/edit/" + spotId;
    }
	
	
	@GetMapping("/area/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
	    Area area = areaService.getArea(id);
	    model.addAttribute("area", area);
	    
	    return "area/edit";
	}
	
	@PostMapping("/area/update/{id}")
	public String updateArea(@PathVariable("id") int id, @Valid Area area, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        return "area/edit";
	    }
	         
	    areaService.add(area);
	    return "redirect:/les-secteurs";
	}
	
	@GetMapping("/area/delete/{id}")
	public String deleteArea(@PathVariable("id") int id, Model model) {
		areaService.deleteArea(id);
	    
	    return "redirect:/les-secteurs";
	}
}
