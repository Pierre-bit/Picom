package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Arret;

public interface ArretDao extends JpaRepository<Arret, Long> {

}
