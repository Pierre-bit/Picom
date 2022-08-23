package fr.project.picom.service;

import javax.validation.Valid;

import fr.project.picom.model.Client;

public interface ClientService {

	Client enregistrerClient(@Valid Client Client);
	
	Client ajouterClient(String nom,String prenom,String email, String motDePasse);
	
	Boolean deleteClient(Long id);
	
	Client recupererClient(Long id);
}
