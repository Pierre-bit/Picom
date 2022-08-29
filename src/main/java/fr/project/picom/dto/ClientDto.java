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
	
	@NotBlank(message="Merci de renseigner votre nom")
	@NonNull
	String nom;
	
	@NotBlank(message="Merci de renseigner votre prénom")
	@NonNull
	String prenom;
	
	@Email(message="Merci de préciser une adresse email au bon format")
	@NotBlank(message="Merci de préciser une adresse email")
	@NonNull
	String email;

	@NotBlank(message = "Merci de préciser un mot de passe")
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min=8, message="Le mot de passe doit contenir 8 caractère minimum")
	@NonNull
	String motDePasse;
	
	@NotBlank(message ="Veuillez renseigner le numéro de téléphone.")
	@NonNull
	String numeroDeTelephone;
	

}
