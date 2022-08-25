package fr.project.picom.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto {
	
	@NotBlank(message="{utilisateur.nom.manquant}")
	@NonNull
	String nom;
	
	@NotBlank(message="{utilisateur.prenom.manquant}")
	@NonNull
	String prenom;
	
	@Email(message="Merci de préciser une adresse email au bon format")
	@NotBlank(message="Merci de préciser une adresse email")
	@NonNull
	String email;
	
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min=8, message="{utilisateur.mot-de-passe.invalide}")
	@NonNull
	String motDePasse;
	
	@NotBlank(message ="{client.numeroDeTelephone.manquant}")
	@NonNull
	String numeroDeTelephone;
	

}
