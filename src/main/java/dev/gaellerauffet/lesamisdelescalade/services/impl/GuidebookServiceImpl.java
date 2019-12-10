package dev.gaellerauffet.lesamisdelescalade.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gaellerauffet.lesamisdelescalade.model.Guidebook;
import dev.gaellerauffet.lesamisdelescalade.persistance.GuidebookRepository;
import dev.gaellerauffet.lesamisdelescalade.services.GuidebookService;

@Service
public class GuidebookServiceImpl implements GuidebookService {
	@Autowired
    GuidebookRepository guidebookRepository;
	
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
		 /*SimpleDateFormat formaDate = new SimpleDateFormat("dd/MM/yyyy");
         String releaseDate = formaDate.format(gb.getReleaseDate());

         try {
			gb.setReleaseDate(formaDate.parse(releaseDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		guidebookRepository.save(gb);
		
	}

	@Override
	public void deleteGuidebook(int id) {
		guidebookRepository.deleteById(id);
		
	}

}
