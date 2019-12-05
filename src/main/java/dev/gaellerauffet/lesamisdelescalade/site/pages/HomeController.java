package dev.gaellerauffet.lesamisdelescalade.site.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;
 
@Controller
public class HomeController {
 
	@Autowired
    UserService userService;
	
	@RequestMapping("/")
    public String index(Model model) {
		User user = userService.getUser(1);
		model.addAttribute("user", user);
        return "index";
    }
   
}
