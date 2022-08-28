package fr.project.picom.service.impl;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.project.picom.dao.ClientDao;
import fr.project.picom.model.Client;
import fr.project.picom.service.ClientService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientDao clientDao;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Client enregistrerClient(@Valid Client client) {
		client.setMotDePasse(passwordEncoder.encode(client.getMotDePasse()));
		clientDao.save(client);
		return client;
	}

	@Override
	public Boolean deleteClient(Long id) {
		Client client = recupererClient(id);
		if (client == null) {
			return false;
		} else {
			clientDao.delete(client);
			return true;
		}
	}

	@Override
	public Client recupererClient(Long id) {
		return clientDao.findById(id).orElse(null);
	}

	

}
