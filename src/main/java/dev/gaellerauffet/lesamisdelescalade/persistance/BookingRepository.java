package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	Booking findById(int id);
}
