package fr.project.picom.service;

import java.util.List;

import fr.project.picom.model.TrancheHoraire;

public interface TrancheHoraireService {
	TrancheHoraire getTrancheHoraire(Long id);
	
	List<TrancheHoraire> getTrancheHoraires();
	
	TrancheHoraire createTrancheHoraire(TrancheHoraire trancheHoraire);
}
