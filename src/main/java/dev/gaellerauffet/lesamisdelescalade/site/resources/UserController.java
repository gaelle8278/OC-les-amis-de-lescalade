package dev.gaellerauffet.lesamisdelescalade.site.resources;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.gaellerauffet.lesamisdelescalade.model.Role;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.model.dto.AdminUpdateUserDTO;
import dev.gaellerauffet.lesamisdelescalade.services.RoleService;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

@Controller
public class UserController {

	@Autowired
    UserService userService;
	
	@Autowired 
	RoleService roleService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/user/{id}")
	public String displayUser(@PathVariable("id") int id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
        return "user/display";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/les-utilisateurs")
	public String listUsers(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<User> page = userService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "user/admin-list";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/adduser")
	public String displayUserAddForm(@ModelAttribute("userDto") AdminUpdateUserDTO userDto, Model model) {
		List<Role> listRoles = roleService.getRoles();
	    model.addAttribute("listRoles", listRoles);
		return "user/add";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/user/add")
	public String addUser( @Valid @ModelAttribute("userDto") AdminUpdateUserDTO userDto, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			List<Role> listRoles = roleService.getRoles();
		    model.addAttribute("listRoles", listRoles);
			return "user/add"; 
		}
		User user = convertToEntity(userDto);
		userService.add(user);
		
		//redirect
		return "redirect:/admin/les-utilisateurs";
	}
	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/user/edit/{id}")
	public String displayUserUpdateForm(@PathVariable("id") int id, Model model) {
		AdminUpdateUserDTO userDto = convertToDto(userService.getUser(id));
	    List<Role> listRoles = roleService.getRoles();
	    model.addAttribute("listRoles", listRoles);
	    model.addAttribute("userDto", userDto);
	    
	    return "user/edit";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") int id, @Valid @ModelAttribute("userDto") AdminUpdateUserDTO userDto, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	List<Role> listRoles = roleService.getRoles();
		    model.addAttribute("listRoles", listRoles);
	        return "user/edit";
	    }
	    User user = convertToEntity(userDto);
	    userService.update(user);
	    return "redirect:/admin/les-utilisateurs";
	}
	    
	@Secured("ROLE_ADMIN")
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
		userService.delete(id);
		return "redirect:/admin/les-utilisateurs";
	}
	
	
	private AdminUpdateUserDTO convertToDto(User user) {
		AdminUpdateUserDTO adminUpdateUserDto = modelMapper.map(user, AdminUpdateUserDTO.class);
	   
	    return adminUpdateUserDto;
	}
	
	private User convertToEntity(AdminUpdateUserDTO adminUpdateUserDto) {
	    User user = modelMapper.map(adminUpdateUserDto, User.class);
	    
	  
	    if (adminUpdateUserDto.getId() != null) {
	        User oldUser = userService.getUser(adminUpdateUserDto.getId());
	        user.setCheckedCGU(oldUser.isCheckedCGU());
	        user.setActive(oldUser.isActive());
	        user.setPassword(adminUpdateUserDto.getUpdatePassword(oldUser));
	    } else {
	    	user.setActive(true);
	    	user.setPassword(adminUpdateUserDto.getNewPassword());
	    }
	    return user;
	}
	
	
}
