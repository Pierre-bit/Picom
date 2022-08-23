package fr.project.picom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tarif {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private double prixEnEuros;
	
	@ManyToOne
	private Administrateur administrateur; 
	
	@ManyToOne
	private TrancheHoraire trancheHoraire;
	
	@ManyToOne
	private Zone zone;
	
	
	

}
