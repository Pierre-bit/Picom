package fr.project.picom.dto;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.NonNull;

public class TrancheHoraireDto {
	@NonNull
	@NotNull(message = "Veuillez renseigner une tranche horaire")
	private Integer debut;

	@ManyToMany
	private List<Long> annonces;
}
