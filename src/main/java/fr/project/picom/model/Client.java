package fr.project.picom.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Client extends Utilisateur {

	@NotBlank(message = "Veuillez renseigner le numéro de téléphone.")
	@NonNull
	private String numeroDeTelephone;

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Annonce> annonces;

}
