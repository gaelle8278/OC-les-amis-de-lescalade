package dev.gaellerauffet.lesamisdelescalade.site.pages;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

@Controller
public class AccountController {
	@Autowired
	UserService userService;
	
	@GetMapping("/acces-compte")
	public String displayAccess(User user) {
		return "account/access";
	}
	
	@GetMapping("/inscription")
	public String displaySignUpForm(User user) {
		return "account/signup";
	}
	
	@PostMapping("/inscription")
	public String createAccount(@Valid User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		User userExists = userService.findUserByEmail(user.getEmail());
		 if (userExists != null) {
			 result.rejectValue("email", "error.user",
	                            "L'adresse e-mail est déjà utilisé pour un compte existant");
	    }
		if(result.hasErrors()) {
			return "account/access"; 
		}
		userService.add(user, "ROLE_USER");
		
		redirectAttributes.addFlashAttribute("successMessage", "ok");
		//redirect
		return "redirect:/connexion";
	}
	
	@GetMapping("/connexion")
	public String displayConnectionForm() {
		return "account/connection";
	}
	
	@GetMapping("/admin/dashboard")
	public String displayAdminHomePage() {
	    return "account/administration";
	}
	
	@GetMapping("/membre/mon-compte")
	public String displayMemberAccountHomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        String username = user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")";
        model.addAttribute("username", username);
	    return "account/member_account_homepage";
	}

	
	@GetMapping("/default")
	public String defaultAfterLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN")) ) {
			return "redirect:/admin/dashboard";
		}
		return "redirect:/membre/mon-compte";
	    	
	}
	
	
	
}
