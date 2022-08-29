package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Tarif;
import fr.project.picom.model.TrancheHoraire;
import fr.project.picom.model.Zone;

public interface TarifDao extends JpaRepository<Tarif, Long> {
	Tarif findByTrancheHoraireAndZone(TrancheHoraire trancheHoraire, Zone zone);
}
