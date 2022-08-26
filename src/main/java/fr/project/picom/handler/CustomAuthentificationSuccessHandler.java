package fr.project.picom.handler;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthentificationSuccessHandler implements AuthenticationSuccessHandler {

	ObjectMapper om = new ObjectMapper();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpStatus.ACCEPTED.value());
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("user", authentication.getPrincipal());
		response.getOutputStream().println(om.writeValueAsString(hashMap));
	}

}
