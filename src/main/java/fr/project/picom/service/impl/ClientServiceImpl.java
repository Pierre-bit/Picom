package fr.project.picom.service.impl;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;

import fr.project.picom.dao.ClientDao;
import fr.project.picom.model.Client;
import fr.project.picom.service.ClientService;

public class ClientServiceImpl implements ClientService {

	
	private final ClientDao clientDao;
	private final PasswordEncoder passwordEncoder;
	
	
	
	public ClientServiceImpl(ClientDao clientDao, PasswordEncoder passwordEncoder) {
		super();
		this.clientDao = clientDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Client enregistrerClient(@Valid Client Client) {
		Client.setMotDePasse(passwordEncoder.encode(Client.getMotDePasse()));
		clientDao.save(Client);
		return Client;
	}

	@Override
	public Client ajouterClient(String nom, String prenom, String email, String motDePasse) {
		Client Client = new Client();
		Client.setNom(nom);
		Client.setPrenom(prenom);
		Client.setEmail(email);
		Client.setMotDePasse(passwordEncoder.encode(motDePasse));
		
		return clientDao.save(Client);
	}

	@Override
	public Boolean deleteClient(Long id) {
		Client Client = recupererClient(id);
		if(Client == null)
		{
			return false;
		}
		else
		{
			clientDao.delete(Client);
			return true;
		}
	}

	@Override
	public Client recupererClient(Long id) {
		return clientDao.findById(id).orElse(null);
	}

}
