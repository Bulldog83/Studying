package ru.bulldog.patterns.database.storage;

import com.google.common.collect.Lists;
import ru.bulldog.patterns.database.identity.ProductIdentityMap;
import ru.bulldog.patterns.database.mapper.ProductMapper;
import ru.bulldog.patterns.database.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ProductStorage {
	
	public final Storage storage;
	public final ProductMapper mapper;

	public ProductStorage(Storage storage, ProductMapper mapper) {
		this.storage = storage;
		this.mapper = mapper;
	}

	public List<Product> getAll() throws SQLException {
		List<Product> products = Lists.newArrayList();
		try (Connection connection = storage.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(mapper.getSelectAllSql());
			while (resultSet.next()) {
				Product product = mapper.map(resultSet, new Product());
				Product current = ProductIdentityMap.get(product.getId());
				if (current == null) {
					ProductIdentityMap.add(product.getId(), product);
					products.add(product);
				} else {
					current.setId(product.getId());
					current.setTitle(product.getTitle());
					current.setPrice(product.getPrice());
					current.setCategory(product.getCategory());
					current.setCreated(product.getCreated());
					current.setUpdated(product.getUpdated());
					products.add(current);
				}
			}
		}
		return products;
	}

	public Product getOne(long productId) throws SQLException {
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getSelectOneSql());
			statement.setLong(1, productId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Product product = ProductIdentityMap.get(productId);
				if (product == null) {
					product = new Product();
					ProductIdentityMap.add(productId, product);
				}
				return mapper.map(resultSet, product);
			}
		}
		throw new SQLException("Product " + productId + "not found.");
	}

	public Product create(Product product) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getCreateSql());
			statement.setString(1, product.getTitle());
			statement.setBigDecimal(2, product.getPrice());
			statement.setLong(3, product.getCategory().getId());
			statement.setTimestamp(4, Timestamp.valueOf(now));
			statement.setTimestamp(5, Timestamp.valueOf(now));
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				product.setId(result.getLong(1));
				product.setCreated(now);
				product.setUpdated(now);
				return product;
			}
		}
		throw new SQLException("Product creation error: " + product);
	}

	public Product update(Product product) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getUpdateSql());
			statement.setString(1, product.getTitle());
			statement.setTimestamp(2, Timestamp.valueOf(now));
			statement.setLong(3, product.getId());
			if (statement.executeUpdate() > 0) {
				product.setUpdated(now);
				return product;
			}
		}
		throw new SQLException("Product update error: " + product);
	}

	public boolean delete(Product product) throws SQLException {
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getDeleteSql());
			statement.setLong(1, product.getId());
			return statement.executeUpdate() > 0;
		}
	}
}
