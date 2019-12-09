package dev.gaellerauffet.lesamisdelescalade.site.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dev.gaellerauffet.lesamisdelescalade.model.User;

@Controller
public class AccountController {
	
	@GetMapping("/acces-compte")
	public String displayAccess() {
		return "account/access";
	}
	
	@GetMapping("/inscription")
	public String displaySignUpForm(User user) {
		return "account/signup";
	}
	
	@GetMapping("/connexion")
	public String displayConnectionForm() {
		return "account/connection";
	}
	
	@GetMapping("/admin")
	public String displayAdminHomePage() {
	    return "account/administration";
	}

}
