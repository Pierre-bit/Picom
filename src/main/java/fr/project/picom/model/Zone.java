package fr.project.picom.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Zone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner le nom de la zone")
	private String nom;

	@JsonIgnore
	@ManyToMany(mappedBy = "zones")
	private List<Annonce> annonces;

	@JsonIgnore
	@OneToMany(mappedBy = "zone")
	private List<Arret> arrets;
}
