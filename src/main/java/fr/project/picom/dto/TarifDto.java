package fr.project.picom.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.NonNull;

public class TarifDto {
	@NonNull
	@NotNull(message = "Veuillez renseigner un prix")
	private Double prixEnEuros;

	@NonNull
	@NotNull(message = "Veuillez assigner un administrateur")
	@ManyToOne
	private Long administrateur; 

	@NonNull
	@NotNull(message = "Veuillez renseigner une tranche horaire")
	@ManyToOne
	private Long trancheHoraire;

	@NonNull
	@NotNull(message = "Veuillez renseigner une zone")
	@ManyToOne
	private Long zone;
}
