package com.janpaces.modernjavatesting;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerFullServerTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	@Test
	public void testDefaultMessage() {
		//given
		String url = "http://localhost:" + port + "/hello";
		// when
		ResponseEntity<String> forEntity = testRestTemplate.getForEntity(url, String.class);
		//then
		assertThat(forEntity.getBody()).isEqualTo("Hello World!");
	}

	@Test
	public void testNameParameter() {
		//given
		String testString = "testString";
		String url = "http://localhost:" + port + "/hello?name=" + testString;
		// when
		ResponseEntity<String> forEntity = testRestTemplate.getForEntity(url, String.class);
		//then
		assertThat(forEntity.getBody()).isEqualTo("Hello " + testString + "!");
	}

}
