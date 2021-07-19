package ru.bulldog.hiberapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bulldog.hiberapp.SessionManager;
import ru.bulldog.hiberapp.model.Product;
import ru.bulldog.hiberapp.model.User;
import ru.bulldog.hiberapp.model.UserProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserProductDAO implements DAO<UserProduct, Long> {

	private final SessionManager sessionManager;

	@Autowired
	public UserProductDAO(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@Override
	public Optional<UserProduct> getOne(Long id) {
		try (Session session = sessionManager.getSession()) {
			return Optional.ofNullable(session.get(UserProduct.class, id));
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserProduct> findAll() {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			List<UserProduct> users = (List<UserProduct>) session.createQuery("FROM UserProduct").getResultList();
			session.getTransaction().commit();
			return users;
		} catch (Exception ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public UserProduct save(UserProduct entity) {
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
	public void delete(UserProduct entity) {
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
			session.createQuery("DELETE FROM UserProduct up WHERE up.raw_id = :id")
					.setParameter("id", id).executeUpdate();
			session.getTransaction().commit();
		}
	}

	public void buyProduct(UserProduct bought) {
		try (Session session = sessionManager.getSession();) {
			session.beginTransaction();
			session.saveOrUpdate(bought);
			session.getTransaction().commit();
		}
	}
}
