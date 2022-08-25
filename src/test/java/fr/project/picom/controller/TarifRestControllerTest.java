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

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.project.picom.dto.TarifDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TarifRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	private static TarifDto t = new TarifDto();

	@Test
	@Order(1)
	void testerAjouterTarif() throws Exception {
		MockHttpServletRequestBuilder requestLoginBuilder = MockMvcRequestBuilders.post("/login")
				.param("username", "admin1@orsys.fr")
				.param("password", "12345678")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestLoginBuilder).andDo(MockMvcResultHandlers.print());
		
		t.setPrixEnEuros(100D);
		t.setAdministrateur(2L);
		t.setTrancheHoraire(1L);
		t.setZone(1L);

		String tarif = om.writeValueAsString(t);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/tarif").content(tarif)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.prixEnEuros").value(100D))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	// @Test
	// @Order(2)
	// void recupererLesZone() throws Exception {
	// MockHttpServletRequestBuilder requestBuilder =
	// MockMvcRequestBuilders.get("/api/zones/")
	// .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
	// mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
	// .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	// }
	//
	// @Test
	// @Order(3)
	// void recupererLaZone() throws Exception {
	// MockHttpServletRequestBuilder requestBuilder =
	// MockMvcRequestBuilders.get("/api/zone/6")
	// .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
	// mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Zone
	// test"))
	// .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	// }

}
