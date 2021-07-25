package ru.bulldog.hiberapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bulldog.hiberapp.dao.ProductDAO;
import ru.bulldog.hiberapp.model.Product;
import ru.bulldog.hiberapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

	private final ProductDAO productDAO;
	private UsersService usersService;

	@Autowired
	public ProductsService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public Optional<Product> findById(Long id) {
		return productDAO.getOne(id);
	}

	public List<? extends Object> getProductsBoughtByUser(Long userId) {
		Optional<User> userOptional = usersService.findById(userId);
		if (userOptional.isPresent()) {
			return productDAO.getProductsBoughtByUser(userOptional.get());
		}
		return new ArrayList<>();
	}
}
