package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Area;
import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.persistance.CommentRepository;
import dev.gaellerauffet.lesamisdelescalade.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
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
	public void update(int idComment, Comment commentForm) {
		Comment comment = em.getReference(Comment.class, idComment);
		commentForm.setSpot(comment.getSpot());
		commentRepository.save(commentForm);
		
	}

	@Override
	public void add(int spotId,Comment comment) {
		//set foreign dependency : fetch spot thanks to spotId parameter
		Spot spot = em.getReference(Spot.class,spotId);
		comment.setSpot(spot);
		//save comment
		commentRepository.save(comment);
		
	}

}
