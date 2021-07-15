package ru.bulldog.hiberapp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "raw_id")
	private Long id;
	@Column
	private String title;
	@Column
	private double price;

	@ManyToMany
	@JoinTable(
		name = "users_products",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	public Product() {}

	public Product(String title, double price) {
		this.title = title;
		this.price = price;
	}

	public Product(long id, String title, double price) {
		this.id = id;
		this.title = title;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
