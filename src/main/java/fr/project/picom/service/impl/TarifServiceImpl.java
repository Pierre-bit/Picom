package fr.project.picom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.project.picom.dao.TarifDao;
import fr.project.picom.exception.ElementNonTrouveException;
import fr.project.picom.model.Tarif;
import fr.project.picom.service.TarifService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TarifServiceImpl implements TarifService {

	TarifDao tarifDao;

	@Override
	public Tarif getTarif(Long id) {
		return tarifDao.findById(id)
				.orElseThrow(() -> new ElementNonTrouveException("Le tarif d'ID " + id + " n'existe pas."));
	}

	@Override
	public List<Tarif> getTarifs() {
		return tarifDao.findAll();
	}

	@Override
	public Tarif createTarif(Tarif tarif) {
		return tarifDao.save(tarif);
	}

}
