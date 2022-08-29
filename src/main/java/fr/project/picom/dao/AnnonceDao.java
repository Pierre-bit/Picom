package fr.project.picom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Annonce;
import fr.project.picom.model.Utilisateur;

public interface AnnonceDao extends JpaRepository<Annonce, Long> {
	
	List<Annonce> findByClient(Utilisateur utilisateur);

}
