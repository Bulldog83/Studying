package ru.bulldog.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class RMQClientApp {
	private final static Logger logger = LoggerFactory.getLogger(RMQClientApp.class);

	public static void main(String[] args) {
		boolean running = true;
		try(RMQConsumer consumer = new RMQConsumer().connect()) {
			Scanner scanner = new Scanner(System.in);
			while (running) {
				if (scanner.hasNext()) {
					String line = scanner.nextLine();
					if (line.equals("/stop")) {
						running = false;
					} else {
						if (line.startsWith("/scb")) {
							String topic = line.substring(5);
							consumer.subscribe(topic);
						} else if (line.startsWith("/uscb")) {
							String topic = line.substring(6);
							consumer.unsubscribe(topic);
						} else {
							System.out.println("Unknown command.");
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
