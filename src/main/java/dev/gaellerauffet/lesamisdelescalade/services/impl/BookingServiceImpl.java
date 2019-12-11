package dev.gaellerauffet.lesamisdelescalade.services.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.model.Comment;
import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.Spot;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.persistance.BookingRepository;
import dev.gaellerauffet.lesamisdelescalade.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	//@Todo à mettre dans une classe dédié
	public final static String STATUS_EN_ATTENTE = "en_attente";
	
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
		booking.setStatus(STATUS_EN_ATTENTE);
		//save booking
		bookingRepository.save(booking);
		
	}

}
