package dev.gaellerauffet.lesamisdelescalade.site.resources;

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
import dev.gaellerauffet.lesamisdelescalade.services.PitchService;

@Controller
public class PitchController {
	@Autowired
    PitchService pitchService;

	
	@GetMapping("/pitch/{id}")
	public String displayPitch(@PathVariable("id") int id, Model model) {
		Pitch pitch = pitchService.getPitch(id);
		model.addAttribute("pitch", pitch);
		
        return "pitch/display";
	}

	
	@Secured("ROLE_USER")
	@GetMapping("/routes/{routeId}/pitches")
    public String displayAddForm(@PathVariable("routeId") int routeId, Pitch pitch, Model model) {
		model.addAttribute("routeId", routeId);
		model.addAttribute("pitch", pitch);
        return "pitch/add";
    }
	
	@Secured("ROLE_USER")
	@PostMapping("/routes/{routeId}/pitches")
    public String addPitch(@PathVariable("routeId") int routeId, @Valid Pitch pitch, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pitch/add";
        }
        
        //save pitch and redirect to parent route 
        pitchService.add(routeId, pitch);
        return "redirect:/routes/edit/"+pitch.getRoute().getId();
    }
	
	@Secured("ROLE_USER")
	@GetMapping("/pitches/edit/{pitchId}")
	public String displayUpdateForm(@PathVariable("pitchId") int pitchId, Model model) {
	    Pitch pitch = pitchService.getPitch(pitchId);
	    model.addAttribute("pitch", pitch);
	    
	    model.addAttribute("routeId", pitch.getRoute().getId());
	    
	    return "pitch/edit";
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/pitches/update/{pitchId}")
	public String updatePitch(@PathVariable("pitchId") int pitchId, @Valid Pitch pitch, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	pitch.setId(pitchId);
	        return "pitch/edit";
	    }
	         
	    pitchService.update(pitchId, pitch);
	    
	    return "redirect:/routes/edit/"+pitchService.getParentRoute(pitchId).getId();
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/pitches/delete/{pitchId}")
	public String deletePitch(@PathVariable("pitchId") int pitchId, Model model) {
		Route route = pitchService.getParentRoute(pitchId);
		pitchService.deletePitch(pitchId);
	    
	    return "redirect:/routes/edit/"+route.getId();
	}
}
