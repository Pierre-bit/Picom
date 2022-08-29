package fr.project.picom.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

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
	private Long zone;
}
