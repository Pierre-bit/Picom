package fr.project.picom.dto;

import java.time.LocalDateTime;
import java.util.List;

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
public class AnnonceDto {
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
	@NotBlank(message = "Veuillez renseigner le montant.")
	private Double montantRegleEnEuros;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner le client.")
	private Long client;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner les tranches horaires.")
	private List<Long> tranchesHoraires;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner les zones.")
	private List<Long> zones;
}
