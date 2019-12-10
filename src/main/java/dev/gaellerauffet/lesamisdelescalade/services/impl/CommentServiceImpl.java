package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.persistance.CommentRepository;
import dev.gaellerauffet.lesamisdelescalade.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
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
	public void add(Comment comment) {
		commentRepository.save(comment);
		
	}

	@Override
	public void deleteComment(int id) {
		commentRepository.deleteById(id);
		
	}

}
