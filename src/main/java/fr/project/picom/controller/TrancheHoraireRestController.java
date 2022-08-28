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

import fr.project.picom.dto.TrancheHoraireDto;
import fr.project.picom.model.Annonce;
import fr.project.picom.model.TrancheHoraire;
import fr.project.picom.service.AnnonceService;
import fr.project.picom.service.TrancheHoraireService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class TrancheHoraireRestController {

	TrancheHoraireService trancheHoraireService;
	AnnonceService annonceService;

	@GetMapping("trancheH/{id}")
	public TrancheHoraire getTh(@PathVariable("id") Long id) {
		return trancheHoraireService.getTrancheHoraire(id);
	}

	@GetMapping("/trancheHs")
	public List<TrancheHoraire> getThs() {
		return trancheHoraireService.getTrancheHoraires();
	}

	@PostMapping("/trancheH")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TrancheHoraire postTh(@RequestBody @Valid TrancheHoraireDto trancheHoraireDto, BindingResult bindingResult) {
		return trancheHoraireService.createTrancheHoraire(dtoToModel(trancheHoraireDto));
	}

	private TrancheHoraire dtoToModel(TrancheHoraireDto trancheHoraireDto) {
		TrancheHoraire t = new TrancheHoraire();
		t.setDebut(trancheHoraireDto.getDebut());
		if (!CollectionUtils.isEmpty(trancheHoraireDto.getAnnonces())) {
			List<Annonce> annonces = new ArrayList<>();
			trancheHoraireDto.getAnnonces().forEach(item -> {
				annonces.add(annonceService.getAnnonce(item));
			});
			t.setAnnonces(annonces);
		}
		return t;
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
