package ru.bulldog.hiberapp.model;

import javax.persistence.*;

@Entity
@Table(name = "users_products")
public class UserProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "raw_id")
	private Long id;
	@Column
	private double price;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public UserProduct() {}

	public UserProduct(User user, Product product) {
		this.user = user;
		this.product = product;
		this.price = product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
