package ru.bulldog.patterns.database.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
	private Long id;
	private String title;
	private BigDecimal price = BigDecimal.ZERO;
	private LocalDateTime created;
	private LocalDateTime updated;
	private Category category;

	public Product() {}

	public Product(String title, BigDecimal price) {
		this.title = title;
		this.price = price;
	}

	public Product(long id, String title, BigDecimal price) {
		this.id = id;
		this.title = title;
		this.price = price;
	}

	public Product(long id, String title, BigDecimal price, Category category) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		if (id == null) {
			return product.id == null && title.equals(product.title);
		}
		return id.equals(product.id) && title.equals(product.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}
}
