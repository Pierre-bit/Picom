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

import fr.project.picom.dto.ArretDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArretRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	private static ArretDto a = new ArretDto();

	@Test
	@Order(1)
	void testerAjouterArret() throws Exception {
		a.setLatitude(22.700D);
		a.setLongitude(1.290D);
		a.setNom("Arret test");
		a.setZone(1L);

		String arret = om.writeValueAsString(a);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/arret").content(arret)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Arret test"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(22.700D))
				.andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(1.290D)).andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(2)
	void recupererLesArrets() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/arrets")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(21))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(3)
	void recupererLArret() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/arret/21")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Arret test"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(22.700D))
				.andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(1.290D)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}
