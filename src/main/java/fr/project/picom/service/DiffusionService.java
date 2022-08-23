package fr.project.picom.service;

import java.util.List;

import fr.project.picom.model.Diffusion;

public interface DiffusionService {
	Diffusion getDiffusion(Long id);
	
	List<Diffusion> getDiffusions();
	
	Diffusion createDiffusion(Diffusion diffusion);
}
