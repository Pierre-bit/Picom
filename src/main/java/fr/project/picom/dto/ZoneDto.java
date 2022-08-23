package fr.project.picom.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZoneDto {
	@NonNull
	@NotBlank(message = "Veuillez renseigner le nom de la zone")
	private String nom;

	private List<Long> annonces;
	
	private List<Long> arrets;
}
