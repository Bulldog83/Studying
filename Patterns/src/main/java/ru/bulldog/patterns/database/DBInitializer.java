package ru.bulldog.patterns.database;

import org.flywaydb.core.Flyway;

public final class DBInitializer {

	public static void initialize(String url, String login, String password) {
		Flyway flyway = Flyway.configure().dataSource(url, login, password).load();
		flyway.migrate();
	}
}
