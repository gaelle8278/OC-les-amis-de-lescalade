package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.model.Route;
import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.persistance.GuidebookRepository;
import dev.gaellerauffet.lesamisdelescalade.services.BookingService;
import dev.gaellerauffet.lesamisdelescalade.services.GuidebookService;
import dev.gaellerauffet.lesamisdelescalade.services.UserService;
import dev.gaellerauffet.lesamisdelescalade.utils.Constants;

@Service
public class GuidebookServiceImpl implements GuidebookService {
	@Autowired
    GuidebookRepository guidebookRepository;
	
	@Autowired
    BookingService bookingService;;
	
	@Autowired
    UserService userService;
	
	@Autowired
	EntityManager em;
	
	@Override
	public Guidebook getGuidebook(int id) {
		Guidebook gb = guidebookRepository.findById(id);
		return gb;
	}

	@Override
	public Page<Guidebook> findAllPaginated(Pageable pageable) {
		Page<Guidebook> page = guidebookRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public void add(Guidebook gb) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		gb.setUser(user);
		guidebookRepository.save(gb);
		
	}

	@Override
	public void deleteGuidebook(int id) {
		guidebookRepository.deleteById(id);
		
	}

	@Override
	public void update(int idGb, Guidebook guidebookForm) {
		Guidebook gb = em.getReference(Guidebook.class,idGb);
		//on update of a guidebook information available status doesn't change
		//it change only change during booking management ith asetStatus
		guidebookForm.setAvailable(gb.isAvailable());
		guidebookForm.setUser(gb.getUser());
		
		guidebookRepository.save(guidebookForm);
		
		
	}

	@Override
	public Page<Guidebook> getUserGuidebooks(Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Page<Guidebook> listGuidebooks = guidebookRepository.findAllByUser(user, pageable);
		return listGuidebooks;
	}

	@Override
	public boolean displayBookingButton(Guidebook guidebook) {
		boolean display = false;
		if(guidebook.isAvailable() && guidebook.isBookable() ) {
			//check if connected user own guidebook
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User connectedUser = userService.findUserByEmail(auth.getName());
			if( connectedUser.getId() != guidebook.getUser().getId() ) {
				//display = true;
				//chek is user has alreadu set a booking in pending
				List<Booking> listUserBooking = bookingService.getBoookingByUserAndGb(connectedUser,guidebook) ;
				if(listUserBooking.isEmpty()) {
					display = true;
				} else {
					for(int i=0; i < listUserBooking.size(); i++) {
						Booking booking = listUserBooking.get(i);
						//System.out.print(booking.getUser().getId());
						if( booking.getStatus() != Constants.BOOKING_APP_PENDING_STATUS) {
							 display = true;
							 break;
						}
					}
				}
			}
		}
		return display;
	}

}
