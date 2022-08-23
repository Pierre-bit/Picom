package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Client;

public interface ClientDao extends JpaRepository<Client, Long>{

}
