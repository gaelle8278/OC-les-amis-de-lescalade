package dev.gaellerauffet.lesamisdelescalade.site.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.gaellerauffet.lesamisdelescalade.model.Booking;
import dev.gaellerauffet.lesamisdelescalade.services.BookingService;

@Controller
public class BookingController {
	@Autowired
    BookingService bookingService;

	
	@GetMapping("/booking/{id}")
	public String displayBooking(@PathVariable("id") int id, Model model) {
		Booking booking = bookingService.getBooking(id);
		model.addAttribute("booking", booking);
        return "booking/display";
	}
	
	@GetMapping("/les-reservations")
	public String listBookings(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Booking> page = bookingService.findAllPaginated(pageable);
		model.addAttribute("page", page);
        return "booking/list";
	}
	
	
	@GetMapping("/guidebooks/{gbId}/bookings")
    public String displayAddForm(@PathVariable("gbId") int gbId,Booking booking, Model model) {
		//traitement pour ajouter la réservation
		bookingService.add(gbId, booking);
		 return "redirect:/guidebook/"+ gbId;
    }
	
	/*@PostMapping("/guidebooks/{gbId}/bookings")
    public String addBooking(@PathVariable("areaId") int areaId, @Valid Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "booking/add";
        }
        
        //save spot and redirect
        bookingService.add(booking);
        return "redirect:/les-reservations";
    }*/
	
	
	/*@GetMapping("/booking/edit/{id}")
	public String displayUpdateForm(@PathVariable("id") int id, Model model) {
	    Booking booking = bookingService.getBooking(id);
	    model.addAttribute("booking", booking);
	    
	    return "booking/edit";
	}
	
	@PostMapping("/booking/update/{id}")
	public String updateBooking(@PathVariable("id") int id, @Valid Booking booking, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        return "booking/edit";
	    }
	         
	    bookingService.add(booking);
	    return "redirect:/les-reservations";
	}*/
	
	@GetMapping("/booking/delete/{id}")
	public String deleteBooking(@PathVariable("id") int id, Model model) {
		bookingService.deleteBooking(id);
	    
	    return "redirect:/les-reservations";
	}

}
