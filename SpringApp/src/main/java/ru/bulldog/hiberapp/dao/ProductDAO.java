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
public class ProductDAO implements DAO<Product, Long> {

	private final SessionManager sessionManager;

	@Autowired
	public ProductDAO(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}


	@Override
	public Optional<Product> getOne(Long id) {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			Product product = session.get(Product.class, id);
			session.getTransaction().commit();
			return Optional.ofNullable(product);
		} catch (Exception ex) {
			return Optional.empty();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		try (Session session = sessionManager.getSession()) {
			session.beginTransaction();
			List<Product> users = (List<Product>) session.createQuery("from User").getResultList();
			session.getTransaction().commit();
			return users;
		} catch (Exception ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public Product save(Product entity) {
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
	public void delete(Product entity) {
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
			session.createQuery("DELETE FROM Product p WHERE p.raw_id = :id")
					.setParameter("id", id).executeUpdate();
			session.getTransaction().commit();
		}
	}

	public List<Product> getProductsBoughtByUser(User user) {
		Session session = sessionManager.getSession();
		try {
			session.beginTransaction();
			session.persist(user);
			return user.getProducts().stream().map(UserProduct::getProduct).collect(Collectors.toList());
		} catch (EntityExistsException ex) {
			return user.getProducts().stream().map(UserProduct::getProduct).collect(Collectors.toList());
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
