package dev.gaellerauffet.lesamisdelescalade.site.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.services.BookingService;

@Controller
public class BookingController {
	@Autowired
    BookingService bookingService;

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/booking/{id}")
	public String displayBooking(@PathVariable("id") int id, Model model) {
		Booking booking = bookingService.getBooking(id);
		model.addAttribute("booking", booking);
        return "booking/display";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/les-reservations")
	public String listBookings(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Booking> page = bookingService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "booking/admin-list";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/guidebooks/{gbId}/bookings")
    public String addBooking(@PathVariable("gbId") int gbId,Booking booking, Model model) {
		//traitement pour ajouter la réservation
		bookingService.add(gbId, booking);
		return "redirect:/guidebook/"+ gbId;
    }
	
	@Secured("ROLE_USER")
	@GetMapping("/membre/mes-reservations")
	public String listUserBookings(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Booking> page = bookingService.findUserBookings(pageable);
		model.addAttribute("page", page);
        return "booking/user-list";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/membre/gestion-reservations")
	public String listUserBookingsToManage(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Booking> page = bookingService.findUserBookingsToManage(pageable);
		model.addAttribute("page", page);
        return "booking/management-user-list";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/mybookings/{bookingId}/manage-status/{status}")
	public String manageMyBookingStatus(@PathVariable("bookingId") int bookingId, @PathVariable("status") String status, Model model) {
		bookingService.manageStatus(bookingId, status);
		
		return "redirect:/membre/mes-reservations";
		
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/bookings/{bookingId}/manage-status/{status}")
	public String manageBookingStatus(@PathVariable("bookingId") int bookingId, @PathVariable("status") String status, Model model) {
		bookingService.manageStatus(bookingId, status);
		
		return "redirect:/membre/gestion-reservations";
		
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/booking/delete/{id}")
	public String deleteBooking(@PathVariable("id") int id, Model model) {
		bookingService.deleteBooking(id);
	    
	    return "redirect:/les-reservations";
	}

}
