package ru.bulldog.patterns.database.mapper;

import ru.bulldog.patterns.database.storage.CategoryStorage;
import ru.bulldog.patterns.database.model.Category;
import ru.bulldog.patterns.database.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

	private final String table = "products";
	private final String id = "raw_id";
	private final String title = "title";
	private final String price = "price";
	private final String category = "category";
	private final String created = "created";
	private final String updated = "updated";

	private final String selectOneSql = String.format("SELECT * FROM %s WHERE %s=?", table, id);
	private final String createSql = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?)", table, title, price, category, created, updated);
	private final String updateSql = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?", table, title, price, category, updated, id);
	private final String deleteSql = String.format("DELETE FROM %s WHERE %s=? LIMIT 1", table, id);

	private final CategoryStorage categoryStorage;

	public ProductMapper(CategoryStorage categoryStorage) {
		this.categoryStorage = categoryStorage;
	}

	public String getSelectAllSql() {
		return "SELECT * FROM " + table;
	}

	public String getSelectOneSql() {
		return selectOneSql;
	}

	public String getCreateSql() {
		return createSql;
	}

	public String getUpdateSql() {
		return updateSql;
	}

	public String getDeleteSql() {
		return deleteSql;
	}

	public Product map(ResultSet resultSet, Product product) throws SQLException {
		product.setId(resultSet.getLong(id));
		product.setTitle(resultSet.getString(title));
		product.setPrice(resultSet.getBigDecimal(price));
		product.setCreated(resultSet.getTimestamp(created).toLocalDateTime());
		product.setUpdated(resultSet.getTimestamp(updated).toLocalDateTime());

		Category productCategory = categoryStorage.getOne(resultSet.getLong(category));
		product.setCategory(productCategory);

		return product;
	}
}
