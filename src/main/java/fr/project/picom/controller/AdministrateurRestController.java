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

import fr.project.picom.dto.AdministrateurDto;
import fr.project.picom.model.Administrateur;
import fr.project.picom.service.AdministrateurService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@Validated
public class AdministrateurRestController {

	private final AdministrateurService administrateurService;

	@GetMapping(value = "admin/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Administrateur getAdministrateur(@PathVariable Long id) {
		return administrateurService.recupererAdministrateur(id);
	}

	@PostMapping(value = "admin")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Administrateur ajouteradministrateur(@Valid @RequestBody AdministrateurDto administrateurDto,
			BindingResult result) {
		Administrateur administrateur = new Administrateur();
		administrateur.setNom(administrateurDto.getNom());
		administrateur.setPrenom(administrateurDto.getPrenom());
		administrateur.setEmail(administrateurDto.getEmail());
		administrateur.setMotDePasse(administrateurDto.getMotDePasse());
		return administrateurService.enregistrerAdministrateur(administrateur);
	}

	@DeleteMapping("admin/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public boolean supprimerAdministrateur(@PathVariable("id") Long id) {
		Administrateur administrateur = getAdministrateur(id);
		if (administrateur == null) {
			return false;
		} else {
			administrateurService.deleteAdministrateur(id);
			return true;
		}
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
