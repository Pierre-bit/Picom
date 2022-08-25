package fr.project.picom.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.project.picom.dto.ArretDto;
import fr.project.picom.model.Arret;
import fr.project.picom.service.ArretService;
import fr.project.picom.service.ZoneService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class ArretRestController {

	ArretService arretService;
	ZoneService zoneService;
	
	@GetMapping("arret/{id}")
	public Arret getArret(@PathVariable("id") Long id) {
		return arretService.getArret(id);
	}
	
	@GetMapping("arrets")
	public List<Arret> getArrets(){
		return arretService.getArrets();
	}
	
	@PostMapping("arret")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Arret createArret(@RequestBody @Valid ArretDto arretDto, BindingResult bindingResult) {
		return arretService.createArret(dtoToModel(arretDto));
	}
	
	private Arret dtoToModel(ArretDto arretDto) {
		Arret a = new Arret();
		a.setLatitude(arretDto.getLatitude());
		a.setLongitude(arretDto.getLongitude());
		a.setNom(arretDto.getNom());
		a.setZone(zoneService.getZone(arretDto.getZone()));
		return a;
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
	
}
