package ru.bulldog.hiberapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bulldog.hiberapp.dao.UserProductDAO;
import ru.bulldog.hiberapp.model.Product;
import ru.bulldog.hiberapp.model.User;
import ru.bulldog.hiberapp.model.UserProduct;

@Service
public class UserProductService {

	private final UserProductDAO userProductDAO;

	@Autowired
	public UserProductService(UserProductDAO userProductDAO) {
		this.userProductDAO = userProductDAO;
	}

	public void buyProduct(User user, Product product) {
		userProductDAO.buyProduct(new UserProduct(user, product));
	}
}
