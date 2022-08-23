package fr.project.picom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.project.picom.dao.DiffusionDao;
import fr.project.picom.exception.ElementNonTrouveException;
import fr.project.picom.model.Diffusion;
import fr.project.picom.service.DiffusionService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiffusionServiceImpl implements DiffusionService {

	DiffusionDao diffusionDao;

	@Override
	public Diffusion getDiffusion(Long id) {
		return diffusionDao.findById(id)
				.orElseThrow(() -> new ElementNonTrouveException("La diffusion d'ID " + id + " n'existe pas."));
	}

	@Override
	public List<Diffusion> getDiffusions() {
		return diffusionDao.findAll();
	}

	@Override
	public Diffusion createDiffusion(Diffusion diffusion) {
		return diffusionDao.save(diffusion);
	}

}
