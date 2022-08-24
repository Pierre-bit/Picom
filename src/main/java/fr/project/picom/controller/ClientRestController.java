package fr.project.picom.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.project.picom.dto.ClientDto;
import fr.project.picom.model.Client;
import fr.project.picom.service.ClientService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@Validated
public class ClientRestController {

	private final ClientService clientService;

	@GetMapping(value = "client/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Client getClient(@PathVariable Long id) {
		return clientService.recupererClient(id);
	}

	@PostMapping(value = "client")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Client ajouterClient(@Valid @RequestBody ClientDto clientDto, BindingResult result) {
		Client client = new Client();
		client.setNom(clientDto.getNom());
		client.setPrenom(clientDto.getPrenom());
		client.setEmail(clientDto.getEmail());
		client.setMotDePasse(clientDto.getMotDePasse());
		client.setNumeroDeTelephone(clientDto.getNumeroDeTelephone());
		return clientService.enregistrerClient(client);
	}

	@DeleteMapping("client/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public boolean supprimerClient(@PathVariable("id") Long id) {
		Client client = getClient(id);
		if (client == null) {
			return false;
		} else {
			clientService.deleteClient(id);
			return true;
		}
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
	
}
