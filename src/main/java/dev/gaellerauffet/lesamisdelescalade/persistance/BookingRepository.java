package dev.gaellerauffet.lesamisdelescalade.persistance;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	Booking findById(int id);
	
	Page<Booking> findAllByUser(User user, Pageable pageable);
	
	Page<Booking> findAllByGuidebookIn(List<Guidebook> listGuidebooks, Pageable pageable);
	
	List<Booking> findAllByUserAndGuidebook(User user, Guidebook guidebook);
}

