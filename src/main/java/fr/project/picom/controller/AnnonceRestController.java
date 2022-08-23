package fr.project.picom.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.project.picom.dto.AnnonceDto;
import fr.project.picom.model.Annonce;
import fr.project.picom.service.AnnonceService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class AnnonceRestController {
	
	AnnonceService annonceService;
	
	@GetMapping("annonce")
	public Annonce recupererAnnonce(Long id) {
		return this.annonceService.getAnnonce(id);
	}
	
	@GetMapping("annonces")
	public List<Annonce> recupererAnnonces(){
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
		
		return a;
	}
}
