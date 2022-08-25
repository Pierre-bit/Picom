package fr.project.picom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Arret {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner le nom de l'arret")
	private String nom;

	@NonNull
	@NotNull(message = "Veuillez renseigner la longitude")
	private Double longitude;

	@NonNull
	@NotNull(message = "Veuillez renseigner la latitude")
	private Double latitude;

	@JsonIgnore
	@NonNull
	@NotNull(message = "Veuillez renseigner la zone")
	@ManyToOne
	private Zone zone;
}
