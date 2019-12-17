package dev.gaellerauffet.lesamisdelescalade.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.User;

public interface BookingService {

	Booking getBooking(int id);

	Page<Booking> findAllPaginated(Pageable pageable);

	void add(Booking booking);

	void deleteBooking(int id);

	void add(int gbId, Booking booking);

	Page<Booking> findUserBookings(Pageable pageable);

	Page<Booking> findUserBookingsToManage(Pageable pageable);

	List<Booking> getBoookingByUserAndGbAndStatus(User user, Guidebook gb, String status);

	void manageStatus(int bookingId, String status);

}
