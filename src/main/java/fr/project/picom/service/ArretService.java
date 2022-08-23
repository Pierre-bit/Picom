package fr.project.picom.service;

import java.util.List;

import fr.project.picom.model.Arret;

public interface ArretService {
	Arret getArret(Long id);
	
	List<Arret> getArrets();
	
	Arret createArret(Arret arret);
}
