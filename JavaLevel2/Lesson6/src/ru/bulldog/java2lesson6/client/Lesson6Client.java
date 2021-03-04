package ru.bulldog.java2lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Lesson6Client {

	private static boolean running = true;

	public static void main(String[] args) {
		Scanner consoleInput = new Scanner(System.in);
		try (Socket clientSocket = new Socket("localhost", 10000)) {
			System.out.println("Client started.");
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			new Thread(() -> {
				while (isRunning()) {
					try {
						String message = input.readUTF().trim();
						if (!message.equals("")) {
							System.out.println("Server: " + message);
							if (message.equals("/stop")) {
								stop();
								break;
							}
						}
					} catch (IOException ex) {
						System.out.println("Read message error: " + ex.getMessage());
						stop();
						break;
					}
				}
			}, "listener").start();
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			while (running) {
				if (consoleInput.hasNext()) {
					String message = consoleInput.nextLine().trim();
					if (!message.equals("")) {
						output.writeUTF(message);
						if (message.equals("/stop")) {
							clientSocket.close();
							stop();
							break;
						}
					}
				}
			}
		} catch (IOException ex) {
			System.out.println("Client start error: " + ex.getMessage());
			stop();
		}
	}

	private static boolean isRunning() {
		return running;
	}

	private static void stop() {
		running = false;
	}
}
