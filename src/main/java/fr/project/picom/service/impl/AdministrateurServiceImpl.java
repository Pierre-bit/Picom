package fr.project.picom.service.impl;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.project.picom.dao.AdministrateurDao;
import fr.project.picom.model.Administrateur;
import fr.project.picom.service.AdministrateurService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdministrateurServiceImpl implements AdministrateurService {

	private final AdministrateurDao administrateurDao;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Administrateur enregistrerAdministrateur(@Valid Administrateur administrateur) {
		administrateur.setMotDePasse(passwordEncoder.encode(administrateur.getMotDePasse()));
		return administrateurDao.save(administrateur);
	}

	@Override
	public Boolean deleteAdministrateur(Long id) {
		Administrateur administrateur = recupererAdministrateur(id);
		if (administrateur == null) {
			return false;
		} else {
			administrateurDao.delete(administrateur);
			return true;
		}
	}

	@Override
	public Administrateur recupererAdministrateur(Long id) {
		return administrateurDao.findById(id).orElse(null);
	}
	
	

}
