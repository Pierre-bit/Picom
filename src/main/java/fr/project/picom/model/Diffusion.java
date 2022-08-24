package fr.project.picom.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Diffusion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@NotNull(message = "Veuillez renseigner l'heure de diffusion")
	private LocalDateTime dateHeureDiffusion;

	@NonNull
	@NotNull(message = "Veuillez renseigner l'annonce")
	@ManyToOne
	private Annonce annonce;

	@NonNull
	@NotNull(message = "Veuillez renseigner l'arret")
	@ManyToOne
	@JsonIgnore
	private Arret arret;
	
}
