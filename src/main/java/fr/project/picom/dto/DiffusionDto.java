package fr.project.picom.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DiffusionDto {
	@NonNull
	@NotNull(message = "Veuillez renseigner l'heure de diffusion")
	private LocalDateTime dateHeureDiffusion;

	@NonNull
	@NotNull(message = "Veuillez renseigner l'annonce")
	private Long annonce;

	@NonNull
	@NotNull(message = "Veuillez renseigner l'arret")
	private Long arret;
}
