package dev.gaellerauffet.lesamisdelescalade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.gaellerauffet.lesamisdelescalade.model.User;
import dev.gaellerauffet.lesamisdelescalade.persistance.UserRepository;



@SpringBootApplication
public class LesamisdelescaladeApplication {
	
	private static final Logger log = LoggerFactory.getLogger(LesamisdelescaladeApplication.class);

	public static void main(String[] args) {
	    SpringApplication.run(LesamisdelescaladeApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(UserRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new User("Luc", "Clinm", "luc@hotmail.com"));

	      // fetch all customers
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (User user : repository.findAll()) {
	        log.info(user.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      User user = repository.findById(1);
	      log.info("Customer found with findById(1):");
	      log.info("--------------------------------");
	      log.info(user.toString());
	      log.info("");

	      // fetch customers by last name
	      log.info("Customer found with findByLastName('Bauer'):");
	      log.info("--------------------------------------------");
	      repository.findByLastName("Bbuer").forEach(bauer -> {
	        log.info(bauer.toString());
	      });
	      // for (Customer bauer : repository.findByLastName("Bauer")) {
	      //  log.info(bauer.toString());
	      // }
	      log.info("");
	    };
	  }*/


}
