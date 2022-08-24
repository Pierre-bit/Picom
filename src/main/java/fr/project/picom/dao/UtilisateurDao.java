package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Utilisateur;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByEmail(String username);

}
