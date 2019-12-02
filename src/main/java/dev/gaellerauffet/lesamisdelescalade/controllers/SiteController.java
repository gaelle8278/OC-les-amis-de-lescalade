package dev.gaellerauffet.lesamisdelescalade.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {
	
	@RequestMapping("/les-sites")
    public String directory(Model model) {
		model.addAttribute("title", "Les sites");
        return "sites-list";
    }

	@RequestMapping("/recherche-site")
    public String search(Model model) {
		model.addAttribute("title", "Recherche de site");
        return "site-search";
    }
}
