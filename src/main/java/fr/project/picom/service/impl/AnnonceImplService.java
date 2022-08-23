package fr.project.picom.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import fr.project.picom.dao.AnnonceDao;
import fr.project.picom.model.Annonce;
import fr.project.picom.service.AnnonceService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnnonceImplService implements AnnonceService {

	AnnonceDao annonceDao;
	
	@Override
	public Annonce getAnnonce(Long id) {
		return this.annonceDao.findById(id).orElse(null);
	}

	@Override
	public List<Annonce> getAnnonces() {
		return this.annonceDao.findAll();
	}

	@Override
	public Annonce createAnnonce(@RequestBody @Valid Annonce annonce, BindingResult bindingResult) {
		return this.annonceDao.save(annonce);
	}

}
