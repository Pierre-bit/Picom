package fr.project.picom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
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

import fr.project.picom.dto.ZoneDto;
import fr.project.picom.model.Annonce;
import fr.project.picom.model.Arret;
import fr.project.picom.model.Zone;
import fr.project.picom.service.AnnonceService;
import fr.project.picom.service.ArretService;
import fr.project.picom.service.ZoneService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class ZoneRestController {

	ZoneService zoneService; 
	AnnonceService annonceService;
	ArretService arretService;
	
	@GetMapping("zone/{id}")
	public Zone getZone(@PathVariable("id") Long id) {
		return zoneService.getZone(id);
	}
	
	@GetMapping("zones")
	public List<Zone> getZones(){
		return zoneService.getZones();
	}
	
	@PostMapping("zone")
	public Zone postZone(@RequestBody @Valid ZoneDto zoneDto, BindingResult bindingResult) {
		return zoneService.createZone(dtoToModel(zoneDto));
	}
	
	private Zone dtoToModel(ZoneDto zoneDto) {
		Zone z = new Zone();
		z.setNom(zoneDto.getNom());
		if(!CollectionUtils.isEmpty(zoneDto.getAnnonces())) {
			List<Annonce> annonces = new ArrayList<>();
			zoneDto.getAnnonces().forEach(item -> {
				annonces.add(annonceService.getAnnonce(item));
			});
			z.setAnnonces(annonces);
		}
		
		if(!CollectionUtils.isEmpty(zoneDto.getArrets())) {
			List<Arret> arrets = new ArrayList<>();
			zoneDto.getArrets().forEach(item -> {
				arrets.add(arretService.getArret(item));
			});
			z.setArrets(arrets);
		}
		return z;
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
