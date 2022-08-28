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

import fr.project.picom.dto.ZoneDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ZoneRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	private static ZoneDto z = new ZoneDto();

	@Test
	@Order(1)
	void testerAjouterZone() throws Exception {
		z.setNom("Zone test");

		String zone = om.writeValueAsString(z);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/zone").content(zone)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Zone test"))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@Order(2)
	void recupererLesZone() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/zones/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@Order(3)
	void recupererLaZone() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/zone/6")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Zone test"))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

}
