package ru.bulldog.patterns.database.identity;

import com.google.common.collect.Maps;
import ru.bulldog.patterns.database.model.Product;

import java.util.Map;

public class ProductIdentityMap {

	private static final ProductIdentityMap instance;

	private final Map<Long, Product> productsMap = Maps.newHashMap();

	public static Product get(Long key) {
		return instance.productsMap.get(key);
	}

	public static void add(Long key, Product product) {
		instance.productsMap.put(key, product);
	}

	static {
		instance = new ProductIdentityMap();
	}
}
