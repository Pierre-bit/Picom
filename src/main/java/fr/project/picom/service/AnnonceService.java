package fr.project.picom.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import fr.project.picom.model.Annonce;

public interface AnnonceService {
	Annonce getAnnonce(Long id);
	
	List<Annonce> getAnnonces();
	
	Annonce createAnnonce(Annonce annonce, BindingResult bindingResult);
}
