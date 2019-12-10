package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Comment findById(int id);
}
