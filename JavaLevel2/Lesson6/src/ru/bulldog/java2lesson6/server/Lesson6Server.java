package ru.bulldog.java2lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Lesson6Server {

	private static boolean running = true;

	public static void main(String[] args) {
		Scanner consoleInput = new Scanner(System.in);
		try (ServerSocket server = new ServerSocket(10000)) {
			System.out.println("Server started.");
			while (running) {
				Socket clientSocket = server.accept();
				System.out.println("Client connected.");
				DataInputStream input = new DataInputStream(clientSocket.getInputStream());
				new Thread(() -> {
					while (isRunning()) {
						try {
							String message = input.readUTF().trim();
							if (!message.equals("")) {
								System.out.println("Client: " + message);
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
						try {
							String message = consoleInput.nextLine().trim();
							if (!message.equals("")) {
								output.writeUTF(message);
								if (message.equals("/stop")) {
									clientSocket.close();
									stop();
									break;
								}
							}
						} catch (IOException ex) {
							System.out.println("Send message error: " + ex.getMessage());
							stop();
							break;
						}
					}
				}
			}
		} catch (IOException ex) {
			System.out.println("Server start error: " + ex.getMessage());
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
