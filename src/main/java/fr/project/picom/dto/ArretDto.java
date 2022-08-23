package fr.project.picom.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.NonNull;

public class ArretDto {
	@NonNull
	@NotBlank(message = "Veuillez renseigner le nom de l'arret")
	private String nom;

	@NonNull
	@NotNull(message = "Veuillez renseigner la longitude")
	private Double longitude;

	@NonNull
	@NotNull(message = "Veuillez renseigner la latitude")
	private Double latitude;

	@NonNull
	@NotNull(message = "Veuillez renseigner la zone")
	@ManyToOne
	private Long zone;
}
