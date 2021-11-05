package ru.bulldog.patterns.database.mapper;

import ru.bulldog.patterns.database.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper {

	private final String table = "categories";
	private final String id = "raw_id";
	private final String title = "title";
	private final String created = "created";
	private final String updated = "updated";

	private final String selectOneSql = String.format("SELECT * FROM %s WHERE %s=?", table, id);
	private final String createSql = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", table, title, created, updated);
	private final String updateSql = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?", table, title, updated, id);
	private final String deleteSql = String.format("DELETE FROM %s WHERE %s=? LIMIT 1", table, id);

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

	public Category map(ResultSet resultSet, Category category) throws SQLException {
		category.setId(resultSet.getLong(id));
		category.setTitle(resultSet.getString(title));
		category.setCreated(resultSet.getTimestamp(created).toLocalDateTime());
		category.setUpdated(resultSet.getTimestamp(updated).toLocalDateTime());
		return category;
	}
}
