package fr.project.picom.service;

import fr.project.picom.model.Utilisateur;

public interface UtilisateurService {
	Utilisateur getUtilisateurByEmail(String email);
}
