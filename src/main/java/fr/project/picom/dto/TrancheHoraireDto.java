package fr.project.picom.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrancheHoraireDto {
	@NonNull
	@NotNull(message = "Veuillez renseigner une tranche horaire")
	private Integer debut;

	private List<Long> annonces;
}
