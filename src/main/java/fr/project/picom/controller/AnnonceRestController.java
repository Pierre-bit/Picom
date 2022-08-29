package fr.project.picom.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import fr.project.picom.dto.AnnonceDto;
import fr.project.picom.model.Annonce;
import fr.project.picom.model.TrancheHoraire;
import fr.project.picom.model.Utilisateur;
import fr.project.picom.model.Zone;
import fr.project.picom.service.AnnonceService;
import fr.project.picom.service.ClientService;
import fr.project.picom.service.TrancheHoraireService;
import fr.project.picom.service.UtilisateurService;
import fr.project.picom.service.ZoneService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class AnnonceRestController {

	AnnonceService annonceService;
	ClientService clientService;
	TrancheHoraireService trancheHoraireService;
	ZoneService zoneService;
	UtilisateurService utilisateurService;

	@GetMapping("annonce/{id}")
	public Annonce recupererAnnonce(@PathVariable("id") Long id) {
		return annonceService.getAnnonce(id);
	}

	@GetMapping("annonces")
	public List<Annonce> recupererAnnonces() {
		return annonceService.getAnnonces();
	}
	
	@GetMapping("annoncesPage")
	public Page<Annonce> recupererPageAnnonces(@PageableDefault(size = 5, page = 0, sort = "id") Pageable pageable) {
		return annonceService.getAnnonces(pageable);
	}
	
	@GetMapping("annonceClient/{id}")
	public List<Annonce> recupererAnnonceClient(@PathVariable("id") Long id)
	{
		Utilisateur utilisateur = utilisateurService.recupUser(id);
		return annonceService.getAnnonceByUti(utilisateur);
	}

	@PostMapping("annonce")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Annonce postAnnonce(@RequestBody @Valid AnnonceDto annonceDto, BindingResult bindingResult) {
		return annonceService.createAnnonce(dtoToModel(annonceDto));
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
