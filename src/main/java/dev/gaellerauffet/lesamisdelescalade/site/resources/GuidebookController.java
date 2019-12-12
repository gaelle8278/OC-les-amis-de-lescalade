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

import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.services.GuidebookService;

@Controller
public class GuidebookController {
	@Autowired
	private GuidebookService guidebookService;
	
	@GetMapping("/guidebook/{id}")
	public String displayGuidebook(@PathVariable("id") int id, Model model) {
		Guidebook guidebook = guidebookService.getGuidebook(id);
		// calculer l'affichage du bouton de réservation et ajouter une variable dans le modèle indiquant le résutat du calcul 
		// pour afficher le bouton dans le modème
		model.addAttribute("guidebook", guidebook);
        return "guidebook/display";
	}
	
	@GetMapping("/les-topos")
	public String listGuidebooks(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Guidebook> page = guidebookService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "guidebook/list";
	}
	
	@GetMapping("/mes-topos")
	public String listUserGuidebooks(@PageableDefault(size = 10) Pageable pageable, Model model) {
		//@TODO user id est forcé pour les tests
		Page<Guidebook> page = guidebookService.getUserGuidebooks(1, pageable);
		//Page<Guidebook> page = guidebookService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "guidebook/user-list";
	}
	
	@GetMapping("/addguidebook")
    public String displayAddForm(Guidebook guidebook) {
        return "guidebook/add";
    }
	
	@PostMapping("/guidebook/add")
    public String addGuidebook(@Valid Guidebook guidebook, BindingResult result, Model model) {
		//System.out.print(guidebook.getReleaseDate());
        if (result.hasErrors()) {
        	return "guidebook/add";
        }
        
        //save spot and redirect
        guidebookService.add(guidebook);
        return "redirect:/les-topos";
    }
	
	
	@GetMapping("/guidebook/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
	   Guidebook guidebook = guidebookService.getGuidebook(id);
		
	    model.addAttribute("guidebook", guidebook);
	    
	    return "guidebook/edit";
	}
	
	@PostMapping("/guidebook/update/{id}")
	public String updateGuidebook(@PathVariable("id") int id, @Valid Guidebook guidebook, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	guidebook.setId(id);
	        return "guidebook/edit";
	    }
	         
	    guidebookService.update(id, guidebook);
	    return "redirect:/les-topos";
	}
	
	@GetMapping("/guidebook/delete/{id}")
	public String deleteGuidebook(@PathVariable("id") int id, Model model) {
		guidebookService.deleteGuidebook(id);
	    
	    return "redirect:/les-topos";
	}
	
}
