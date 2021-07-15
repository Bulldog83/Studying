package ru.bulldog.hiberapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class SessionManager {

	private SessionFactory sessionFactory;

	public SessionManager() {}

	@PostConstruct
	private void init() {
		this.sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();

		try (Session session = sessionFactory.getCurrentSession()) {
			String schemaSql = Files.lines(Paths.get(getClass().getResource("/schema.sql").toURI())).collect(Collectors.joining(" "));
			session.beginTransaction();
			session.createNativeQuery(schemaSql).executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}

	@PreDestroy
	private void destroy() {
		sessionFactory.close();
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
