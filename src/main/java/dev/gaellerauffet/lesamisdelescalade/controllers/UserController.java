package dev.gaellerauffet.lesamisdelescalade.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/acces-compte")
    public String index(Model model) {
		model.addAttribute("title", "Les sites");
        return "acces-compte";
    }

}
