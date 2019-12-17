package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.persistance.CommentRepository;
import dev.gaellerauffet.lesamisdelescalade.services.CommentService;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
    UserService userService;
	
	@Autowired
	EntityManager em;
	
	@Override
	public Page<Comment> findAllPaginated(Pageable pageable) {
		Page<Comment> page = commentRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public Comment getComment(int id) {
		Comment comment = commentRepository.findById(id);
		return comment;
	}

	

	@Override
	public void deleteComment(int id) {
		commentRepository.deleteById(id);
		
	}

	@Override
	public void update(Comment commentForm) {
		Comment comment = em.getReference(Comment.class, commentForm.getId());
		commentForm.setSpot(comment.getSpot());
		commentForm.setUser(comment.getUser());
		commentRepository.save(commentForm);
		
	}

	@Override
	public void add(int spotId,Comment comment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		comment.setUser(user);
		//set foreign dependency : fetch spot thanks to spotId parameter
		Spot spot = em.getReference(Spot.class,spotId);
		comment.setSpot(spot);
		//save comment
		commentRepository.save(comment);
		
	}

}
