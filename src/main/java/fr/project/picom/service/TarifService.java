package fr.project.picom.service;

import java.util.List;

import fr.project.picom.model.Tarif;

public interface TarifService {
	Tarif getTarif(Long id);
		
	List<Tarif> getTarifs();
	
	Tarif createTarif(Tarif tarif);
}
