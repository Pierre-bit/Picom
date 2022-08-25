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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.project.picom.dto.DiffusionDto;
import fr.project.picom.model.Diffusion;
import fr.project.picom.service.AnnonceService;
import fr.project.picom.service.ArretService;
import fr.project.picom.service.DiffusionService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class DiffusionRestController {

	DiffusionService diffusionService;
	AnnonceService annonceService;
	ArretService arretService;
	
	@GetMapping("diffusion/{id}")
	public Diffusion getDiffusion(@PathVariable("id") Long id) {
		return diffusionService.getDiffusion(id);
	}
	
	@GetMapping("diffusions")
	public List<Diffusion> getDiffusions(){
		return diffusionService.getDiffusions();
	}
	
	@PostMapping("diffusion")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Diffusion postDiffusion(@RequestBody @Valid DiffusionDto diffusionDto, BindingResult bindingResult) {
		return diffusionService.createDiffusion(dtoToModel(diffusionDto));
	}
	
	private Diffusion dtoToModel(DiffusionDto diffusionDto) {	
		Diffusion d = new Diffusion();
		d.setDateHeureDiffusion(diffusionDto.getDateHeureDiffusion());
		d.setAnnonce(annonceService.getAnnonce(diffusionDto.getAnnonce()));
		d.setArret(arretService.getArret(diffusionDto.getArret()));
		return d;
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
