package fr.project.picom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.project.picom.dao.ZoneDao;
import fr.project.picom.exception.ElementNonTrouveException;
import fr.project.picom.model.Zone;
import fr.project.picom.service.ZoneService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ZoneServiceImpl implements ZoneService {

	ZoneDao zoneDao;

	@Override
	public Zone getZone(Long id) {
		return zoneDao.findById(id)
				.orElseThrow(() -> new ElementNonTrouveException("La zone d'ID " + id + " n'existe pas."));
	}

	@Override
	public List<Zone> getZones() {
		return zoneDao.findAll();
	}

	@Override
	public Zone createZone(Zone zone) {
		return zoneDao.save(zone);
	}

}
