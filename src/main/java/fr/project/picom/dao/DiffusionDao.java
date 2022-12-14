package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Diffusion;

public interface DiffusionDao extends JpaRepository<Diffusion, Long> {

}
