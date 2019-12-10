package dev.gaellerauffet.lesamisdelescalade.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Comment;

public interface CommentService {

	Page<Comment> findAllPaginated(Pageable pageable);

	Comment getComment(int id);

	void add(@Valid Comment comment);

	void deleteComment(int id);

}
