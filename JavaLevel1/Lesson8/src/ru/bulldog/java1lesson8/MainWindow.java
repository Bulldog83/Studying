package ru.bulldog.java1lesson8;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

	public MainWindow(int width, int height) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		int x = screenWidth / 2 - width / 2;
		int y = screenHeight / 2 - height / 2;

		this.setBounds(x, y, width, height);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);

		JButton btn = new JButton("Hello");
		btn.setBounds(width / 2 - 50, height / 2 + 100, 100, 40);

		JLabel helloLab = new JLabel();
		helloLab.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		helloLab.setHorizontalAlignment(SwingConstants.CENTER);
		helloLab.setBounds(width / 2 - 100, height / 2 - 100, 200, 40);

		this.add(btn);
		this.add(helloLab);

		btn.addActionListener(event -> helloLab.setText("Hey! Hello!"));

		this.setVisible(true);
	}
}
