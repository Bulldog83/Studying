package ru.bulldog.hiberapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.bulldog.hiberapp")
public class HiberApp {

	public static void main(String[] args) {
		SpringApplication.run(HiberApp.class, args);
	}
}
