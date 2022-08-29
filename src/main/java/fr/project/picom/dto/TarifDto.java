package fr.project.picom.dto;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TarifDto {
	@NonNull
	@NotNull(message = "Veuillez renseigner un prix")
	private Double prixEnEuros;

	@NonNull
	@NotNull(message = "Veuillez assigner un administrateur")
	private Long administrateur; 

	@NonNull
	@NotNull(message = "Veuillez renseigner une tranche horaire")
	private Long trancheHoraire;

	@NonNull
	@NotNull(message = "Veuillez renseigner une zone")
	private Long zone;
}
