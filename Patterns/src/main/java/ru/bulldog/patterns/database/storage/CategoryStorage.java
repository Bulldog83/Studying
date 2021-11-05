package ru.bulldog.patterns.database.storage;

import com.google.common.collect.Lists;
import ru.bulldog.patterns.database.identity.CategoryIdentityMap;
import ru.bulldog.patterns.database.mapper.CategoryMapper;
import ru.bulldog.patterns.database.model.Category;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class CategoryStorage {

	private final Storage storage;
	private final CategoryMapper mapper;

	public CategoryStorage(Storage storage, CategoryMapper mapper) {
		this.storage = storage;
		this.mapper = mapper;
	}

	public List<Category> getAll() throws SQLException {
		List<Category> categories = Lists.newArrayList();
		try (Connection connection = storage.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(mapper.getSelectAllSql());
			while (resultSet.next()) {
				Category category = mapper.map(resultSet, new Category());
				Category current = CategoryIdentityMap.get(category.getId());
				if (current == null) {
					CategoryIdentityMap.add(category.getId(), category);
					categories.add(category);
				} else {
					current.setId(category.getId());
					current.setTitle(category.getTitle());
					current.setCreated(category.getCreated());
					current.setUpdated(category.getUpdated());
					categories.add(current);
				}
			}
		}
		return categories;
	}

	public Category getOne(long categoryId) throws SQLException {
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getSelectOneSql());
			statement.setLong(1, categoryId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Category category = CategoryIdentityMap.get(categoryId);
				if (category == null) {
					category = new Category();
					CategoryIdentityMap.add(categoryId, category);
				}
				return mapper.map(resultSet, category);
			}
		}
		throw new SQLException("Category " + categoryId + "not found.");
	}

	public Category create(Category category) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getCreateSql());
			statement.setString(1, category.getTitle());
			statement.setTimestamp(2, Timestamp.valueOf(now));
			statement.setTimestamp(3, Timestamp.valueOf(now));
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				category.setId(result.getLong(1));
				category.setCreated(now);
				category.setUpdated(now);
				return category;
			}
		}
		throw new SQLException("Category creation error: " + category);
	}

	public Category update(Category category) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getUpdateSql());
			statement.setString(1, category.getTitle());
			statement.setTimestamp(2, Timestamp.valueOf(now));
			statement.setLong(3, category.getId());
			if (statement.executeUpdate() > 0) {
				category.setUpdated(now);
				return category;
			}
		}
		throw new SQLException("Category update error: " + category);
	}

	public boolean delete(Category category) throws SQLException {
		try (Connection connection = storage.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(mapper.getDeleteSql());
			statement.setLong(1, category.getId());
			return statement.executeUpdate() > 0;
		}
	}
}
