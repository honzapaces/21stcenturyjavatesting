package com.janpaces.modernjavatesting;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerMockServerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetDefaultHello() throws Exception {
		this.mockMvc.perform(get("/hello")).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(equalTo("Hello World!")));
	}

	@Test
	public void testNamedHello() throws Exception {
		String testName = "name";
		this.mockMvc.perform(get("/hello?name={name}", testName)).andExpect(status().isOk()).andExpect(content().string(equalTo("Hello " + testName + "!")));
	}
}
