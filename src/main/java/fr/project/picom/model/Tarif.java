package fr.project.picom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Tarif {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@NotNull(message = "Veuillez renseigner un prix")
	private Double prixEnEuros;

	@NonNull
	@NotNull(message = "Veuillez assigner un administrateur")
	@ManyToOne
	private Administrateur administrateur; 

	@NonNull
	@NotNull(message = "Veuillez renseigner une tranche horaire")
	@ManyToOne
	private TrancheHoraire trancheHoraire;

	@NonNull
	@NotNull(message = "Veuillez renseigner une zone")
	@ManyToOne
	private Zone zone;
	
	
	

}
