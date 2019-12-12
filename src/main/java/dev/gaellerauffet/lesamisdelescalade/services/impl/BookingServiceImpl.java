package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.persistance.BookingRepository;
import dev.gaellerauffet.lesamisdelescalade.persistance.GuidebookRepository;
import dev.gaellerauffet.lesamisdelescalade.services.BookingService;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;
import dev.gaellerauffet.lesamisdelescalade.utils.Constants;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	EntityManager em;

	@Override
	public Booking getBooking(int id) {
		Booking booking = bookingRepository.findById(id);
		return booking;
	}

	@Override
	public Page<Booking> findAllPaginated(Pageable pageable) {
		Page<Booking> page = bookingRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public void add(Booking booking) {
		bookingRepository.save(booking);
		
	}

	@Override
	public void deleteBooking(int id) {
		bookingRepository.deleteById(id);
		
	}
	@Override
	public void add(int guidebookId,Booking booking) {
		//@TODO before save get id of connected user
		User user = em.getReference(User.class, 1);
		booking.setUser(user);
		Guidebook guidebook = em.getReference(Guidebook.class,guidebookId);
		booking.setGuidebook(guidebook);
		
		//set status
		booking.setStatus(Constants.BOOKING_DB_PENDING_STATUS);
		//save booking
		bookingRepository.save(booking);
		
		//guidebook.setAvailable(false);
		//guidebookRepository.save(guidebook);
		
	}

	@Override
	public Page<Booking> findUserBookings(int userId, Pageable pageable) {
		User user = em.getReference(User.class, userId);
		//User user = userService.getUser(userId);
		Page<Booking> listBookings = bookingRepository.findAllByUser(user, pageable);
		return listBookings;
	}

	@Override
	public Page<Booking> findUserBookingsToManage(int userId, Pageable pageable) {
		User user = em.getReference(User.class, userId);
		List<Guidebook> listGuidebook = user.getListGuidebooks();
		
		Page<Booking> listBookings = bookingRepository.findAllByGuidebookIn(listGuidebook, pageable);
		return listBookings;
	}

}
