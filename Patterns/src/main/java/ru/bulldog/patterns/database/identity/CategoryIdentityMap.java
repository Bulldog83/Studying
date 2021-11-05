package ru.bulldog.patterns.database.identity;

import com.google.common.collect.Maps;
import ru.bulldog.patterns.database.model.Category;

import java.util.Map;

public class CategoryIdentityMap {
	private static final CategoryIdentityMap instance;

	private final Map<Long, Category> categoryMap = Maps.newHashMap();

	public static Category get(Long key) {
		return instance.categoryMap.get(key);
	}

	public static void add(Long key, Category product) {
		instance.categoryMap.put(key, product);
	}

	static {
		instance = new CategoryIdentityMap();
	}
}
