package fr.project.picom.initialisation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.random.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import fr.project.picom.dao.AnnonceDao;
import fr.project.picom.dao.ArretDao;
import fr.project.picom.dao.DiffusionDao;
import fr.project.picom.dao.TrancheHoraireDao;
import fr.project.picom.dao.ZoneDao;
import fr.project.picom.model.Administrateur;
import fr.project.picom.model.Annonce;
import fr.project.picom.model.Arret;
import fr.project.picom.model.Client;
import fr.project.picom.model.Diffusion;
import fr.project.picom.model.TrancheHoraire;
import fr.project.picom.model.Zone;
import fr.project.picom.service.AdministrateurService;
import fr.project.picom.service.ClientService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

	private final ArretDao arretDao;
	private final ZoneDao zoneDao;
	private final TrancheHoraireDao trancheHoraireDao;
	private final ClientService clientService;
	private final AdministrateurService administrateurService;
	private final AnnonceDao annonceDao;
	private final DiffusionDao diffusionDao;

	@Autowired
	private static Faker faker = new Faker(new Locale("fr-FR"));

	@Override
	public void run(String... args) throws Exception {
		ajouterZones();
		ajouterArret();
		ajouterTranchesHoraires();
		ajouterClient();
		ajouterAdmin();
		ajouterAnnonce();
		ajouterDiffusion();
	}

	private void ajouterDiffusion() {
		if (diffusionDao.count() == 0)
		{
			for (int i = 1; i <= 3; i++) {
				Diffusion diff = new Diffusion();
				diff.setDateHeureDiffusion(LocalDateTime.now());
				diff.setAnnonce(annonceDao.findById((long) i).get());
				diff.setArret(arretDao.findById((long) i).get());
				diffusionDao.save(diff);
			}
		}
	}

	private void ajouterAnnonce() {
		if (annonceDao.count() == 0) {
			List<Integer> listZones = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 5; j++) {
					listZones.add(j);
				}
			}

			List<Integer> listTrancheHoraire = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 5; j++) {
					listTrancheHoraire.add(j);
				}
			}
			List<Zone> zones = zoneDao.findAll();
			List<TrancheHoraire> trancheH = trancheHoraireDao.findAll();

			for (int i = 0; i < 5; i++) {

				Annonce annonce = new Annonce();
				annonce.setDateHeureCreation(LocalDateTime.now());
				annonce.setDateHeureDebut(LocalDateTime.now());
				annonce.setContenu(faker.commerce().productName());
				annonce.setNumeroCarte(faker.finance().creditCard());
				annonce.setAnneeExpiration(2000 + i);
				annonce.setMoisExpiration((byte) (Math.random() * (12 - 1 + 1) + 1));
				annonce.setCryptogramme("470" + i);
				annonce.setMontantRegleEnEuros((Math.random() * (999 - 001 + 1) + 1));
				annonce.setClient(clientService.recupererClient(1L));
				annonce.setZones(zones);
				annonce.setTranchesHoraires(trancheH);
				annonceDao.save(annonce);

			}
		}

	}

	private void ajouterAdmin() {
		Administrateur admin = new Administrateur();
		admin.setNom(faker.name().lastName());
		admin.setPrenom(faker.name().firstName());
		admin.setEmail("admin1@orsys.fr");
		admin.setMotDePasse("12345678");
		administrateurService.enregistrerAdministrateur(admin);
	}

	private void ajouterClient() {
		Client client = new Client();
		client.setNom(faker.name().lastName());
		client.setPrenom(faker.name().firstName());
		client.setEmail("client1@orsys.fr");
		client.setMotDePasse("12345678");
		client.setNumeroDeTelephone(faker.phoneNumber().cellPhone());
		clientService.enregistrerClient(client);
	}

	private void ajouterTranchesHoraires() {
		if (trancheHoraireDao.count() == 0) {
			for (int i = 6; i < 20; i++) {
				trancheHoraireDao.save(new TrancheHoraire(i));
			}
		}

	}

	private void ajouterArret() {
		if (arretDao.count() == 0) {
			List<Integer> listZones = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 5; j++) {
					listZones.add(j);
				}
			}
			Collections.shuffle(listZones);

			for (int i = 0; i < 20; i++) {
				List<Zone> zones = zoneDao.findAll();
				Arret arret = new Arret();
				arret.setNom(faker.name().firstName());
				arret.setLongitude(faker.number().randomDouble(6, 1, 60));
				arret.setLatitude(faker.number().randomDouble(6, 1, 60));
				arret.setZone(zones.get(listZones.get(i)));
				arretDao.save(arret);
			}

		}
	}

	private void ajouterZones() {
		if (zoneDao.count() == 0) {
			zoneDao.save(new Zone(faker.address().cityName()));
			zoneDao.save(new Zone(faker.address().cityName()));
			zoneDao.save(new Zone(faker.address().cityName()));
			zoneDao.save(new Zone(faker.address().cityName()));
			zoneDao.save(new Zone(faker.address().cityName()));
		}
	}
}
