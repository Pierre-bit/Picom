package fr.project.picom.service;

import javax.validation.Valid;

import fr.project.picom.model.Administrateur;

public interface AdministrateurService {

	Administrateur enregistrerAdministrateur(@Valid Administrateur administrateur);

	Boolean deleteAdministrateur(Long id);

	Administrateur recupererAdministrateur(Long id);
}
