package dev.gaellerauffet.lesamisdelescalade.site.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

@Controller
public class UserController {

	@Autowired
    UserService userService;
	
	@PostMapping("/user/add")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "compte/inscription"; 
		}
		userService.addUser(user);
		
		//redirect
		return "compte/connexion";
	}
	
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") int id, @Valid User user, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	       // user.setId(id);
	        return "user/update";
	    }
	         
	    model.addAttribute("user", user);
	    return "user/profile";
	}
	     
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
		userService.deleteUser(id);
	    return "compte/inscription";
	}
	
	@GetMapping("/admin")
	public String displayAdminBo() {
	    return "user/administration";
	}
	
}
