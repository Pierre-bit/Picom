package fr.project.picom.service;

import javax.validation.Valid;

import fr.project.picom.model.Client;

public interface ClientService {

	Client enregistrerClient(@Valid Client Client);
	
	Boolean deleteClient(Long id);
	
	Client recupererClient(Long id);
}
