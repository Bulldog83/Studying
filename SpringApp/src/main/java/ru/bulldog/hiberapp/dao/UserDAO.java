package ru.bulldog.hiberapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bulldog.hiberapp.SessionManager;
import ru.bulldog.hiberapp.model.Product;
import ru.bulldog.hiberapp.model.User;
import ru.bulldog.hiberapp.model.UserProduct;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDAO implements DAO<User, Long> {

	private final SessionManager sessionManager;

	@Autowired
	public UserDAO(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}


	@Override
	public Optional<User> getOne(Long id) {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			User user = session.get(User.class, id);
			session.getTransaction().commit();
			return Optional.ofNullable(user);
		} catch (Exception ex) {
			return Optional.empty();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			List<User> users = (List<User>) session.createQuery("from User").getResultList();
			session.getTransaction().commit();
			return users;
		} catch (Exception ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public User save(User entity) {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
			return entity;
		} catch (Exception ex) {
			return entity;
		}
	}

	@Override
	public void delete(User entity) {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
		}
	}

	@Override
	public void delete(Long id) {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			session.createQuery("DELETE FROM User u WHERE u.raw_id = :id")
					.setParameter("id", id).executeUpdate();
			session.getTransaction().commit();
		}
	}

	public List<User> getUsersBoughtProduct(Product product) {
		Session session = sessionManager.getSession();
		try {
			session.beginTransaction();
			session.persist(product);
			return product.getUsers().stream().map(UserProduct::getUser).collect(Collectors.toList());
		} catch (EntityExistsException ex) {
			return product.getUsers().stream().map(UserProduct::getUser).collect(Collectors.toList());
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
