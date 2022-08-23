package fr.project.picom.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.project.picom.dto.AnnonceDto;
import fr.project.picom.model.Annonce;
import fr.project.picom.model.TrancheHoraire;
import fr.project.picom.model.Zone;
import fr.project.picom.service.AnnonceService;
import fr.project.picom.service.ClientService;
import fr.project.picom.service.TrancheHoraireService;
import fr.project.picom.service.ZoneService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class AnnonceRestController {

	AnnonceService annonceService;
	ClientService clientService;
	TrancheHoraireService trancheHoraireService;
	ZoneService zoneService;

	@GetMapping("annonce/{id}")
	public Annonce recupererAnnonce(Long id) {
		return this.annonceService.getAnnonce(id);
	}

	@GetMapping("annonces")
	public List<Annonce> recupererAnnonces() {
		return this.annonceService.getAnnonces();
	}

	@PostMapping("annonce")
	public Annonce postAnnonce(@RequestBody @Valid AnnonceDto annonceDto, BindingResult bindingResult) {
		Annonce a = dtoToModel(annonceDto);
		return this.annonceService.createAnnonce(a);
	}

	private Annonce dtoToModel(AnnonceDto annonceDto) {
		Annonce a = new Annonce();
		a.setDateHeureCreation(LocalDateTime.now());
		a.setDateHeureDebut(annonceDto.getDateHeureDebut());
		a.setContenu(annonceDto.getContenu());
		a.setNumeroCarte(annonceDto.getNumeroCarte());
		a.setAnneeExpiration(annonceDto.getAnneeExpiration());
		a.setMoisExpiration(annonceDto.getMoisExpiration());
		a.setCryptogramme(annonceDto.getCryptogramme());
		a.setMontantRegleEnEuros(annonceDto.getMontantRegleEnEuros());
		a.setClient(clientService.recupererClient(annonceDto.getClient()));
		if (!CollectionUtils.isEmpty(annonceDto.getTranchesHoraires())) {
			List<TrancheHoraire> tranches = new ArrayList<>();
			annonceDto.getTranchesHoraires().forEach(item -> {
				tranches.add(trancheHoraireService.getTrancheHoraire(item));
			});
			a.setTranchesHoraires(tranches);
		}

		if (!CollectionUtils.isEmpty(annonceDto.getZones())) {
			List<Zone> zones = new ArrayList<>();
			annonceDto.getZones().forEach(item -> {
				zones.add(zoneService.getZone(item));
			});
			a.setZones(zones);
		}
		return a;
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
