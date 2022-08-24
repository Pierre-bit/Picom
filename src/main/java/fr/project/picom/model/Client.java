package fr.project.picom.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Client extends Utilisateur{
	
	@NotBlank(message = "merci d'indiquer un numéro")
	@NonNull
	private String numeroDeTelephone;
	
	@OneToMany(mappedBy = "client")
	private List<Annonce> annonces;

	
	
	
	

}
