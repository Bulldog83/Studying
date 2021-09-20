package ru.bulldog.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class RMQServiceApp {
	private final static Logger logger = LoggerFactory.getLogger(RMQServiceApp.class);

	public static void main(String[] args) {
		boolean running = true;
		try(RMQProducer producer = new RMQProducer().connect()) {
			Scanner scanner = new Scanner(System.in);
			while (running) {
				if (scanner.hasNext()) {
					String line = scanner.nextLine();
					if (line.equals("/stop")) {
						running = false;
					} else {
						int topicIdx = line.indexOf(' ');
						if (topicIdx <= 0) {
							continue;
						}
						String topic = line.substring(0, topicIdx).trim();
						String message = line.substring(topicIdx + 1).trim();
						if (!topic.equals("") && !message.equals("")) {
							producer.publish(topic, message);
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
