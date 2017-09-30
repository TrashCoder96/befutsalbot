package ru.bifutsal.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itimofeev on 01.10.2017.
 */

@RestController
public class HelloController {

	@RequestMapping(value = "/")
	public String hello() {
		return "hello";
	}

}
