package ru.bulldog.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@WebServlet(name = "WebApp", urlPatterns = "/products")
public class WebApp extends HttpServlet {

	private List<Product> products;
	private Random random;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder pageBuilder = new StringBuilder();
		pageBuilder.append("<!DOCTYPE html>")
				.append("<html><head>")
				.append("<meta charset=\"UTF-8\">")
				.append("<title>Random Products</title>")
				.append("</head>")
				.append("<body><ul>");
		for (int i = 0; i < 10; i++) {
			Product product = products.get(random.nextInt(products.size()));
			pageBuilder.append(String.format("<li id='%s'>", product.getId()))
					.append(product.getTitle())
					.append(" - ")
					.append(product.getCost())
					.append("</li>");
		}
		pageBuilder.append("</ul></body></html>");
		resp.getWriter().print(pageBuilder);
	}

	@Override
	public void init() throws ServletException {
		random = new Random();
		products = new ArrayList<>(Arrays.asList(
				new Product(1, "Blue Pen", 5.45),
				new Product(2, "Red Pen", 5.45),
				new Product(3, "Green Pen", 5.45),
				new Product(4, "Pencils Box", 20.0),
				new Product(5, "Paper Block", 10.25),
				new Product(6, "Notebook", 3.15),
				new Product(7, "Notebook", 3.15),
				new Product(8, "Notebook", 2.95),
				new Product(9, "Notebook", 2.85),
				new Product(10, "Notebook", 4.0),
				new Product(11, "Scratchpad", 2.15),
				new Product(12, "Scratchpad", 2.15),
				new Product(13, "Scratchpad", 2.2),
				new Product(14, "Scotch tape", 1.25),
				new Product(15, "Scotch tape", 1.75),
				new Product(16, "Scotch tape", 2.25),
				new Product(17, "Scotch tape", 4.5),
				new Product(18, "Paints", 3.0),
				new Product(19, "Paints", 3.55),
				new Product(20, "Paints", 4.5),
				new Product(21, "Paints", 5.25),
				new Product(22, "Paints", 6.15),
				new Product(23, "Set of pens", 12.45),
				new Product(24, "Set of pens", 13.15),
				new Product(25, "Set of pens", 13.5),
				new Product(26, "Set of pens", 13.8),
				new Product(27, "Ruler", 1.45),
				new Product(28, "Ruler", 1.5),
				new Product(29, "Ruler", 2.0),
				new Product(30, "Ruler", 2.45)
		));
	}

	@Override
	public void destroy() {
		products = null;
		random = null;
	}
}
