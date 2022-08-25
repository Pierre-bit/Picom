package fr.project.picom.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto {
	
	@NotBlank(message="{utilisateur.nom.manquant}")
	String nom;
	
	@NotBlank(message="{utilisateur.prenom.manquant}")
	String prenom;
	
	@Email(message="Merci de préciser une adresse email au bon format")
	@NotBlank(message="Merci de préciser une adresse email")
	String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min=8, message="{utilisateur.mot-de-passe.invalide}")
	String motDePasse;
	
	@NotBlank(message ="{client.numeroDeTelephone.manquant}")
	String numeroDeTelephone;
	

}
