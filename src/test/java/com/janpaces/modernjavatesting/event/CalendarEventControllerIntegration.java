package com.janpaces.modernjavatesting.event;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalendarEventControllerIntegration {

	@LocalServerPort
	int port;

	@Test
	public void test() {
		given().
				body(new CalendarEvent(LocalDate.now(), "header", "text")).
				contentType("application/json").
				when().
				post(String.format("http://localhost:%s/event", port)).
				then().
				statusCode(200).
				body("eventHeader", equalTo("header"), "eventText", equalTo("text"));
	}
}
