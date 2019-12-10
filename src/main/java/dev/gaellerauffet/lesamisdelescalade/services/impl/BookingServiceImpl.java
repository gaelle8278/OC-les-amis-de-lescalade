package dev.gaellerauffet.lesamisdelescalade.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.persistance.BookingRepository;
import dev.gaellerauffet.lesamisdelescalade.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	@Autowired
	BookingRepository bookingRepository;

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

}
