package fr.project.picom.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.project.picom.dao.AnnonceDao;
import fr.project.picom.exception.ElementNonTrouveException;
import fr.project.picom.model.Annonce;
import fr.project.picom.model.Utilisateur;
import fr.project.picom.service.AnnonceService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnnonceImplService implements AnnonceService {

	AnnonceDao annonceDao;

	@Override
	public Annonce getAnnonce(Long id) {
		return annonceDao.findById(id)
				.orElseThrow(() -> new ElementNonTrouveException("L'annonce d'ID " + id + " n'existe pas."));
	}

	@Override
	public List<Annonce> getAnnonces() {
		return annonceDao.findAll();
	}

	@Override
	public Annonce createAnnonce(Annonce annonce) {
		return annonceDao.save(annonce);
	}

	@Override
	public Page<Annonce> getAnnonces(Pageable pageable) {
		return annonceDao.findAll(pageable);
	}

	@Override
	public List<Annonce> getAnnonceByUti(Utilisateur utilisateur) {
		return annonceDao.findByClient(utilisateur);
	}

}
