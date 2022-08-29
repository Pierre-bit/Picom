package fr.project.picom.service;

import fr.project.picom.model.Utilisateur;


public interface UtilisateurService {
	
	Utilisateur recupUser(Long id);

	Utilisateur getUtilisateurByEmail(String email);
}
