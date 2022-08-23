package fr.project.picom.dto;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.NonNull;

public class DiffusionDto {
	@NonNull
	@NotNull(message = "Veuillez renseigner l'heure de diffusion")
	private LocalDateTime dateHeureDiffusion;

	@NonNull
	@NotNull(message = "Veuillez renseigner l'annonce")
	@ManyToOne
	private Long annonce;

	@NonNull
	@NotNull(message = "Veuillez renseigner l'arret")
	@ManyToOne
	private Long arret;
}
