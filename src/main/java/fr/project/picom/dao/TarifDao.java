package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Tarif;

public interface TarifDao extends JpaRepository<Tarif, Long> {

}
