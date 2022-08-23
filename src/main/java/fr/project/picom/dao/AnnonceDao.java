package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Annonce;

public interface AnnonceDao extends JpaRepository<Annonce, Long> {

}
