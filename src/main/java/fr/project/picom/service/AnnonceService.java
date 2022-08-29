package fr.project.picom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.project.picom.model.Annonce;
import fr.project.picom.model.Utilisateur;

public interface AnnonceService {
	Annonce getAnnonce(Long id);
	
	List<Annonce> getAnnonces();
	
	Annonce createAnnonce(Annonce annonce);
	
	Page<Annonce> getAnnonces(Pageable pageable);
	
	List<Annonce> getAnnonceByUti(Utilisateur utilisateur);
}
