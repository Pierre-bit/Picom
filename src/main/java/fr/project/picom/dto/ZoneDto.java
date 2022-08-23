package fr.project.picom.dto;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.NonNull;

public class ZoneDto {
	@NonNull
	@NotBlank(message = "Veuillez renseigner le nom de la zone")
	private String nom;

	@ManyToMany	
	private List<Long> annonces;
	
	@OneToMany(mappedBy = "zone")
	private List<Long> arrets;
}
