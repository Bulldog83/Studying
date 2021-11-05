package ru.bulldog.patterns.database.storage;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcConnectionPool;
import ru.bulldog.patterns.database.Configuration;
import ru.bulldog.patterns.database.mapper.CategoryMapper;
import ru.bulldog.patterns.database.mapper.ProductMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class Storage {

	private static final String URL;
	private static final String LOGIN;
	private static final String PASSWORD;

	private final JdbcConnectionPool connectionPool;
	private final CategoryStorage categoryStorage;
	private final ProductStorage productStorage;

	public Storage() {
		initialize();
		this.connectionPool = JdbcConnectionPool.create(URL, LOGIN, PASSWORD);
		this.categoryStorage = new CategoryStorage(this, new CategoryMapper());
		this.productStorage = new ProductStorage(this, new ProductMapper(categoryStorage));
	}

	public Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}

	public CategoryStorage getCategoryStorage() {
		return categoryStorage;
	}

	public ProductStorage getProductStorage() {
		return productStorage;
	}

	public void initialize() {
		Flyway flyway = Flyway.configure().dataSource(URL, LOGIN, PASSWORD).load();
		flyway.migrate();
	}

	static {
		Configuration config = Configuration.getInstance();
		URL = config.getProperty("h2.url");
		LOGIN = config.getProperty("h2.login");
		PASSWORD = config.getProperty("h2.password");
	}
}
