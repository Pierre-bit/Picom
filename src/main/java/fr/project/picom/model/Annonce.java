package fr.project.picom.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Annonce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime dateHeureCreation;
	
	private LocalDateTime dateHeureDebut;
	
	private String contenu;
	
	private String numeroCarte;
	
	private int anneeExpiration;
	
	private byte moisExpiration;
	
	private String cryptogramme;
	
	private double RegleEnEuros;
	
	@ManyToOne
	private Client client;
	
	@ManyToMany
	private List<TrancheHoraire> tranchesHoraires;
	
	@ManyToMany
	private List<Zone> zones;
	
}
