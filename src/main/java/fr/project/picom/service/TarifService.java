package fr.project.picom.service;

import java.util.List;

import fr.project.picom.model.Tarif;
import fr.project.picom.model.TrancheHoraire;
import fr.project.picom.model.Zone;

public interface TarifService {
	Tarif getTarif(Long id);
		
	List<Tarif> getTarifs();
	
	Tarif createTarif(Tarif tarif);
	
	Tarif getTarifByThAndZone(TrancheHoraire th, Zone zone);
	
	List<Tarif> createMultipleTarifs(List<Tarif> tarifs);
}
