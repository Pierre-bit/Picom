package fr.project.picom.service;

import java.util.List;

import fr.project.picom.model.Zone;

public interface ZoneService {
	Zone getZone(Long id);
	
	List<Zone> getZones();
	
	Zone createZone(Zone zone);
}
