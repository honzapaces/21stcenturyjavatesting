package com.janpaces.modernjavatesting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HelloControllerTest {

	private HelloController helloController = new HelloController();

	@Test
	public void testHelloName() {
		//given - arrange
		String name = "TestName";
		//when - act
		String actualName = helloController.getHelloName(name);
		//then - assert
		assertThat(actualName).isEqualTo("Hello " + name + "!");
	}

}