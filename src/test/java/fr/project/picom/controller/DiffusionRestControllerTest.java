package fr.project.picom.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.project.picom.dto.AnnonceDto;
import fr.project.picom.dto.DiffusionDto;
import fr.project.picom.dto.ZoneDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DiffusionRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	private static DiffusionDto d = new DiffusionDto();
	private static AnnonceDto a = new AnnonceDto();

	@Test
	@Order(1)
	void testerAjouterDiffusion() throws Exception {

		a.setDateHeureDebut(LocalDateTime.now());
		a.setContenu("Contenu test");
		a.setNumeroCarte("Numéro carte test");
		a.setAnneeExpiration(2024);
		a.setMoisExpiration((byte) 05);
		a.setCryptogramme("994");
		a.setMontantRegleEnEuros(100D);
		a.setClient(1L);
		List<Long> th = new ArrayList<>();
		List<Long> zone = new ArrayList<>();
		th.add(1L);
		th.add(2L);
		zone.add(1L);
		zone.add(2L);
		a.setTranchesHoraires(th);
		a.setZones(zone);

		String annonce = om.writeValueAsString(a);

		MockHttpServletRequestBuilder requestAnnonceBuilder = MockMvcRequestBuilders.post("/api/annonce")
				.content(annonce).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestAnnonceBuilder);

		d.setAnnonce(1L);
		d.setArret(1L);
		d.setDateHeureDiffusion(LocalDateTime.now());

		String diffusion = om.writeValueAsString(d);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/diffusion").content(diffusion)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(2)
	void recupererLesDiffusions() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/diffusions/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(3)
	void recupererLaDiffusion() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/diffusion/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}
