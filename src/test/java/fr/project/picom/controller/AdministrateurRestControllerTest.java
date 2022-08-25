package fr.project.picom.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.project.picom.dto.AdministrateurDto;
import fr.project.picom.service.AdministrateurService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdministrateurRestControllerTest {

	@Autowired
	private AdministrateurService administrateurService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	private static AdministrateurDto admin = new AdministrateurDto();

	@Test
	@Order(1)
	void testerAjouterAdministrateur() throws Exception {
		admin.setNom("toto");
		admin.setPrenom("test");
		admin.setEmail("test@test.fr");
		admin.setMotDePasse("14789478");
		om.disable(MapperFeature.USE_ANNOTATIONS);
		String ad = om.writeValueAsString(admin);
		System.out.println(ad);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/admin").content(ad)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(admin.getNom()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value(admin.getPrenom()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").isNotEmpty()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@Order(2)
	void recupererAdministrateur() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/3");
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(admin.getNom()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value(admin.getPrenom()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(admin.getEmail()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(3)
	void testerSupprimerAdministrateur() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/admin/3");
		mockMvc.perform(requestBuilder).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
	}

	

}
