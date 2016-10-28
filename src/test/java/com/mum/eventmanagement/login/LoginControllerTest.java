package com.mum.eventmanagement.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class LoginControllerTest {
	@Test
	public void testLoginPage() throws Exception {
		LoginControllerTest controller = new LoginControllerTest();
		MockMvc mockMvc = standaloneSetup(controller).build();
		System.out.println(mockMvc.toString());
		mockMvc.perform(get("/login")).andExpect(view().name("login"));
	}

}
