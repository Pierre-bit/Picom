package fr.project.picom.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Zone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner le nom de la zone")
	private String nom;

	@ManyToMany(mappedBy = "zones")
	private List<Annonce> annonces;
	
	@OneToMany(mappedBy = "zone")
	private List<Arret> arrets;
}
