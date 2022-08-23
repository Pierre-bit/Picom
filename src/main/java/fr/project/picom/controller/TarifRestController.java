package fr.project.picom.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.project.picom.dto.TarifDto;
import fr.project.picom.model.Tarif;
import fr.project.picom.service.TarifService;
import fr.project.picom.service.TrancheHoraireService;
import fr.project.picom.service.ZoneService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class TarifRestController {

	TarifService tarifService;
	TrancheHoraireService trancheHoraireService;
	ZoneService zoneService;
	
	@GetMapping("tarif/{id}")
	public Tarif getTarif(Long id) {
		return tarifService.getTarif(id);
	}
	
	@GetMapping("tarifs")
	public List<Tarif> getTarifs(){
		return tarifService.getTarifs();
	}
	
	@PostMapping("tarif")
	public Tarif postTarif(@RequestBody @Valid TarifDto tarifDto, BindingResult bindingResult) {
		return dtoToModel(tarifDto);
	}
	
	private Tarif dtoToModel(TarifDto tarifDto) {
		Tarif t = new Tarif();
		t.setPrixEnEuros(tarifDto.getPrixEnEuros());
		t.setTrancheHoraire(trancheHoraireService.getTrancheHoraire(tarifDto.getTrancheHoraire()));
		t.setZone(zoneService.getZone(tarifDto.getZone()));
		//TODO finish
		return t;
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
