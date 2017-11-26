package ru.bifutsal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BitfutsalBotApplication {

	private static ApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(BitfutsalBotApplication.class, args);
	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

}
