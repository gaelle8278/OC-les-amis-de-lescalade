package dev.gaellerauffet.lesamisdelescalade.site.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dev.gaellerauffet.lesamisdelescalade.model.User;

@Controller
public class AccountController {
	
	@GetMapping("/acces-compte")
	public String displayAccess() {
		return "compte/acces-compte";
	}
	
	@GetMapping("/inscription")
	public String displayMemberForm(User user) {
		return "compte/inscription";
	}
	
	@GetMapping("/connexion")
	public String displayConnexionForm() {
		return "compte/connexion";
	}

}
