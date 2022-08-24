package fr.project.picom.initialisation;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import fr.project.picom.dao.ArretDao;
import fr.project.picom.dao.TrancheHoraireDao;
import fr.project.picom.dao.ZoneDao;
import fr.project.picom.model.Administrateur;
import fr.project.picom.model.Arret;
import fr.project.picom.model.Client;
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
	private static Random random = new Random();
//	private static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("fr-FR"),
//			new RandomService());
	@Autowired
	private static Faker faker = new Faker(new Locale("fr-FR"));

	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		ajouterZones();
		ajouterArret();
		ajouterTranchesHoraires();
		ajouterClient();
		ajouterAdmin();

	}

	private void ajouterAdmin() {
		Administrateur admin = new Administrateur();
		admin.setNom(faker.name().lastName());
		admin.setPrenom(faker.name().firstName());
		admin.setEmail("admin1@orsys.fr");
		admin.setMotDePasse(passwordEncoder.encode("12345678"));
		administrateurService.enregistrerAdministrateur(admin);
	}

	private void ajouterClient() {
		Client client = new Client();
		client.setNom(faker.name().lastName());
		client.setPrenom(faker.name().firstName());
		client.setEmail("client1@orsys.fr");
		client.setMotDePasse(passwordEncoder.encode("12345678"));
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
			for (int i = 0; i<20; i++)
			{
				List<Zone> zones = zoneDao.findAll();
				Arret arret = new Arret();
				arret.setNom(faker.name().firstName());
				arret.setLongitude(faker.number().randomDouble(6, 1, 60));
				arret.setLatitude(faker.number().randomDouble(6, 1, 60));
				arret.setZone(zones.get(faker.number().numberBetween(1, 5)));
				arretDao.save(arret);
			}
			
		}
	}

	private void ajouterZones() {
		if (zoneDao.count() == 0) {
			zoneDao.save(new Zone("gare"));
			zoneDao.save(new Zone("gare1"));
			zoneDao.save(new Zone("gare2"));
			zoneDao.save(new Zone("gare3"));
			zoneDao.save(new Zone("gare4"));

		}
	}
}
