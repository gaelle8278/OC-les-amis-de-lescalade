package dev.gaellerauffet.lesamisdelescalade;

import org.modelmapper.ModelMapper;
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

	
	
}
