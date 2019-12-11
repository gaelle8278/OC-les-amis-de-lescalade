package dev.gaellerauffet.lesamisdelescalade.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Comment;

public interface CommentService {

	Page<Comment> findAllPaginated(Pageable pageable);

	Comment getComment(int id);


	void deleteComment(int id);

	void update(int id, Comment comment);

	void add(int spotId, @Valid Comment comment);

}
