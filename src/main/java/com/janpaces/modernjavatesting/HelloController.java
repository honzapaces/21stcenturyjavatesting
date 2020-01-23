package com.janpaces.modernjavatesting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("hello")
	public String getHelloName(@RequestParam(required = false, defaultValue = "World") String name) {
		return "Hello " + name + "!";
	}
}
