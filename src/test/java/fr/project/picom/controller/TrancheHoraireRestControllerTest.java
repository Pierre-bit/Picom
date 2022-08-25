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

import fr.project.picom.dto.TrancheHoraireDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrancheHoraireRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	private static TrancheHoraireDto th = new TrancheHoraireDto();

	@Test
	@Order(1)
	void testerAjouterTrancheHoraire() throws Exception {
		th.setDebut(24);

		String trancheH = om.writeValueAsString(th);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/trancheH").content(trancheH)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.debut").value(24))
				.andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(2)
	void recupererLesTranches() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/trancheHs")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(15))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(3)
	void recupererLArret() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/trancheH/15")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.debut").value(24))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}
