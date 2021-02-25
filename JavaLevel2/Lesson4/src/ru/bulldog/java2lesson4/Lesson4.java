package ru.bulldog.java2lesson4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lesson4 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
			primaryStage.setTitle("Network Chat");
			primaryStage.setScene(new Scene(root, 1024, 768));
			primaryStage.show();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
