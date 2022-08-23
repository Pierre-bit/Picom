package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Administrateur;

public interface AdministrateurDao extends JpaRepository<Administrateur, Long>{

}
