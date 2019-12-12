package dev.gaellerauffet.lesamisdelescalade.site.resources;

import java.util.List;

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

import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

@Controller
public class UserController {

	@Autowired
    UserService userService;
	
	
	@GetMapping("/user/{id}")
	public String displayUser(@PathVariable("id") int id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
        return "user/display";
	}
	
	@GetMapping("/les-utilisateurs")
	public String listUsers(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<User> page = userService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "user/list";
	}
	
	
	@GetMapping("/adduser")
	public String displayUserAddForm(User user) {
		return "user/add";
	}
	
	@PostMapping("/user/add")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "user/add"; 
		}
		userService.add(user);
		
		//redirect
		return "redirect:/les-utilisateurs";
	}
	
	
	@GetMapping("/user/edit/{id}")
	public String displayUserUpdateForm(@PathVariable("id") int id, Model model) {
	    User user = userService.getUser(id);
		
	    model.addAttribute("user", user);
	    
	    return "user/edit";
	}
	
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") int id, @Valid User user, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	user.setId(id);
	        return "user/edit";
	    }
	         
	    userService.update(id, user);
	    return "redirect:/les-utilisateurs";
	}
	     
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
		userService.deleteUser(id);
		return "redirect:/les-utilisateurs";
	}
	
	
	
}
