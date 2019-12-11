package dev.gaellerauffet.lesamisdelescalade.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;

public interface BookingService {

	Booking getBooking(int id);

	Page<Booking> findAllPaginated(Pageable pageable);

	void add(Booking booking);

	void deleteBooking(int id);

	void add(int gbId, Booking booking);

}
