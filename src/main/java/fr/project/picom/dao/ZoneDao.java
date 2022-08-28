package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.Zone;

public interface ZoneDao extends JpaRepository<Zone, Long> {

}
