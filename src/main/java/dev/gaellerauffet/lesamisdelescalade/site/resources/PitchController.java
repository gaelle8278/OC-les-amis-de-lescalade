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

import dev.gaellerauffet.lesamisdelescalade.model.Pitch;
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
	
	@GetMapping("/les-longueurs")
	public String listPitchs(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Pitch> page = pitchService.findAllPaginated(pageable);
		model.addAttribute("page", page);
		
        return "pitch/list";
	}
	
	
	@GetMapping("/addpitch")
    public String displayAddForm(Pitch pitch, Model model) {
        return "pitch/add";
    }
	
	@PostMapping("/pitch/add")
    public String addPitch(@Valid Pitch pitch, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pitch/add";
        }
        
        //save spot and redirect
        pitchService.add(pitch);
        return "redirect:/les-longueurs";
    }
	
	
	@GetMapping("/pitch/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
	    Pitch pitch = pitchService.getPitch(id);
	    model.addAttribute("pitch", pitch);
	    
	    return "pitch/edit";
	}
	
	@PostMapping("/pitch/update/{id}")
	public String updatePitch(@PathVariable("id") int id, @Valid Pitch pitch, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        return "pitch/edit";
	    }
	         
	    pitchService.add(pitch);
	    return "redirect:/les-longueurs";
	}
	
	@GetMapping("/pitch/delete/{id}")
	public String deletePitch(@PathVariable("id") int id, Model model) {
		pitchService.deletePitch(id);
	    
	    return "redirect:/les-longueurs";
	}
}
