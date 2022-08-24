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

import fr.project.picom.dto.ClientDto;
import fr.project.picom.service.ClientService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientRestControllerTest {

	@Autowired
	private ClientService clientService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	private static ClientDto cl = new ClientDto();

	@Test
	@Order(1)
	public void testerAjouterClient() throws Exception {
		cl.setNom("toto");
		cl.setPrenom("test");
		cl.setEmail("test@test.fr");
		cl.setMotDePasse("14789478");
		cl.setNumeroDeTelephone("0678747878");

		String client = om.writeValueAsString(cl);
		System.out.println(client);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/client").content(client)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(cl.getNom()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value(cl.getPrenom()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.numeroDeTelephone").value(cl.getNumeroDeTelephone()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(2)
	public void testerSupprimerClient() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/client/3");
		mockMvc.perform(requestBuilder).andExpect(status().isAccepted())

				.andDo(MockMvcResultHandlers.print());
	}

}
