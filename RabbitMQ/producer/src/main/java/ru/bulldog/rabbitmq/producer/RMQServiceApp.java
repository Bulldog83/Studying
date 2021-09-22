package ru.bulldog.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RMQServiceApp {
	private final static Logger logger = LoggerFactory.getLogger(RMQServiceApp.class);
	private final static Set<String> TOPICS_LIST;

	public static void main(String[] args) {
		boolean running = true;
		try(RMQProducer producer = new RMQProducer().connect()) {
			producer.publishTopics(TOPICS_LIST);
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
							if (!TOPICS_LIST.contains(topic)) {
								System.out.println("Wrong topic: " + topic);
								continue;
							}
							producer.publish(topic, message);
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	static {
		TOPICS_LIST = new HashSet<>();
		TOPICS_LIST.add("java.oop");
		TOPICS_LIST.add("java.stream-api");
		TOPICS_LIST.add("java.spring");
		TOPICS_LIST.add("php");
		TOPICS_LIST.add("python");
		TOPICS_LIST.add("scala");
		TOPICS_LIST.add("kotlin");
	}
}
