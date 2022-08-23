package fr.project.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.project.picom.model.TrancheHoraire;

public interface TranchHoraireDao extends JpaRepository<TrancheHoraire, Long> {

}
