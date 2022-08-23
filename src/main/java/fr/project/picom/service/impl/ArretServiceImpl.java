package fr.project.picom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.project.picom.dao.ArretDao;
import fr.project.picom.model.Arret;
import fr.project.picom.service.ArretService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArretServiceImpl implements ArretService {

	ArretDao arretDao;
	
	@Override
	public Arret getArret(Long id) {
		return arretDao.findById(id).orElse(null);
	}

	@Override
	public List<Arret> getArrets() {
		return arretDao.findAll();
	}

	@Override
	public Arret createArret(Arret arret) {
		return arretDao.save(arret);
	}

}
