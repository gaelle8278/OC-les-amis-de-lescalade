package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    UserService userService;
	
	@Autowired
	GuidebookRepository guidebookRepository;

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
		//User user = em.getReference(User.class, 1);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		booking.setUser(user);
		Guidebook guidebook = em.getReference(Guidebook.class,guidebookId);
		booking.setGuidebook(guidebook);
		
		//set status
		booking.setStatus(Constants.BOOKING_DB_PENDING_STATUS);
		//save booking
		bookingRepository.save(booking);
		
		
	}

	@Override
	public Page<Booking> findUserBookings(Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Page<Booking> listBookings = bookingRepository.findAllByUser(user, pageable);
		return listBookings;
	}

	@Override
	public Page<Booking> findUserBookingsToManage(Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Guidebook> listGuidebook = user.getListGuidebooks();
		
		Page<Booking> listBookings = bookingRepository.findAllByGuidebookIn(listGuidebook, pageable);
		return listBookings;
	}
	
	@Override
	 public List<Booking> getBoookingByUserAndGb(User user, Guidebook gb) {
		 return bookingRepository.findAllByUserAndGuidebook(user, gb);
	 }

	@Override
	public void manageStatus(int bookingId, String status) {
		Booking booking = em.getReference(Booking.class, bookingId);
		booking.setStatus(status);
		
		Guidebook gb = em.getReference(Guidebook.class, booking.getGuidebook().getId());
		System.out.println("toto " + gb.isAvailable());
		System.out.println(gb.getId());
		if(status.equals(Constants.BOOKING_DB_APPROVED_STATUS)) {
			gb.setAvailable(false);
			System.out.println("toto " + gb.isAvailable());
			guidebookRepository.save(gb);
			
		} 
		if (status.equals(Constants.BOOKING_DB_FINISHED_STATUS) || status.equals(Constants.BOOKING_DB_REJECTED_STATUS)) {
			gb.setAvailable(true);
			guidebookRepository.save(gb);
		}
		
		bookingRepository.save(booking);
	}

}
