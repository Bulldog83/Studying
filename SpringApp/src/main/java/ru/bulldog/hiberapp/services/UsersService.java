package ru.bulldog.hiberapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bulldog.hiberapp.dao.UserDAO;
import ru.bulldog.hiberapp.model.Product;
import ru.bulldog.hiberapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

	private final UserDAO userDAO;
	private ProductsService productsService;

	@Autowired
	public UsersService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setProductsService(ProductsService productsService) {
		this.productsService = productsService;
	}

	public Optional<User> findById(Long id) {
		return userDAO.getOne(id);
	}

	public List<User> getUsersBoughtProduct(Long productId) {
		Optional<Product> productOptional = productsService.findById(productId);
		if (productOptional.isPresent()) {
			return userDAO.getUsersBoughtProduct(productOptional.get());
		}
		return new ArrayList<>();
	}
}
