package fr.project.picom.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class Annonce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@NotNull(message = "Erreur date de création.")
	private LocalDateTime dateHeureCreation;
	
	@NonNull
	@NotNull(message = "Veuillez renseigner la date de début.")
	private LocalDateTime dateHeureDebut;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner le contenu de l'annonce.")
	private String contenu;

	@NonNull
	@NotBlank(message = "Veuillez renseigner le numéro de carte bancaire.")
	private String numeroCarte;

	@NonNull
	@NotNull(message = "Veuillez renseigner l'année d'expiration de la carte bancaire.")
	private Integer anneeExpiration;
	
	@NonNull
	@NotNull(message = "Veuillez renseigner le mois d'expiration de la carte bancaire.")
	private Byte moisExpiration;

	@NonNull
	@NotBlank(message = "Veuillez renseigner le CVV de la carte bancaire.")
	private String cryptogramme;
	
	@NonNull
	@NotNull(message = "Veuillez renseigner le montant.")
	private Double montantRegleEnEuros;

	
	@NonNull
	@NotNull(message = "Veuillez renseigner le client.")
	@ManyToOne
	@JsonIgnore
	private Client client;
	
	@NonNull
	@NotEmpty(message = "Veuillez renseigner les tranches horaires.")
	@ManyToMany
	@JsonIgnore
	private List<TrancheHoraire> tranchesHoraires;
	
	@NonNull
	@NotEmpty(message = "Veuillez renseigner les zones.")
	@ManyToMany
	@JsonIgnore
	private List<Zone> zones;
	
}
