package fr.project.picom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.project.picom.dao.TrancheHoraireDao;
import fr.project.picom.exception.ElementNonTrouveException;
import fr.project.picom.model.TrancheHoraire;
import fr.project.picom.service.TrancheHoraireService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrancheHoraireServiceImpl implements TrancheHoraireService {

	TrancheHoraireDao trancheHoraireDao;

	@Override
	public TrancheHoraire getTrancheHoraire(Long id) {
		return trancheHoraireDao.findById(id)
				.orElseThrow(() -> new ElementNonTrouveException("La tranche horaire d'ID " + id + " n'existe pas."));
	}

	@Override
	public List<TrancheHoraire> getTrancheHoraires() {
		return trancheHoraireDao.findAll();
	}

	@Override
	public TrancheHoraire createTrancheHoraire(TrancheHoraire trancheHoraire) {
		return trancheHoraireDao.save(trancheHoraire);
	}

}
