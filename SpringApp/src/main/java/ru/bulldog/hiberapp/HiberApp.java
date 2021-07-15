package ru.bulldog.hiberapp;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("ru.bulldog.hiberapp")
public class HiberApp {

	public static void main(String[] args) {
		SpringApplication.run(HiberApp.class, args);
	}
}
