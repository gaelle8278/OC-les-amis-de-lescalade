package dev.gaellerauffet.lesamisdelescalade.site.resources;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.services.CommentService;
import dev.gaellerauffet.lesamisdelescalade.services.GuidebookService;
import dev.gaellerauffet.lesamisdelescalade.services.SpotService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SpotService spotService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/comment/{id}")
	public String displayComment(@PathVariable("id") int id, Model model) {
		Comment comment = commentService.getComment(id);
		model.addAttribute("comment", comment);
        return "comment/display";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/les-commentaires")
	public String listComments(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Comment> page = commentService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "comment/admin-list";
	}
	
	
	@Secured("ROLE_USER")
	@PostMapping("/spots/{spotId}/comments")
    public String addComment(@PathVariable("spotId") int spotId, @Valid Comment comment, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	Spot spot = spotService.getSpot(spotId);
        	model.addAttribute("spot", spot);
        	return "spot/display";
        }
        
        //save comment and redirect to spot page
        commentService.add(spotId, comment);
        return "redirect:/spot/" + spotId;
    }
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/comment/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
	   Comment comment = commentService.getComment(id);
		
	    model.addAttribute("comment", comment);
	    
	    return "comment/edit";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/comment/update/{id}")
	public String updateComment(@PathVariable("id") int id, @Valid Comment comment, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        return "comment/edit";
	    }
	         
	    commentService.update(comment);
	    return "redirect:/admin/les-commentaires";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/comment/delete/{id}")
	public String deleteComment(@PathVariable("id") int id, Model model) {
		commentService.deleteComment(id);
	    
	    return "redirect:/admin/les-commentaires";
	}
	
	
}
