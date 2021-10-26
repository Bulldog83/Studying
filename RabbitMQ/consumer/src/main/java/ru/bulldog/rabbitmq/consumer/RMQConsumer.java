package ru.bulldog.rabbitmq.consumer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RMQConsumer implements AutoCloseable {
	private final static Logger logger = LoggerFactory.getLogger(RMQConsumer.class);
	private final static String TOPICS_LIST_QUEUE = "topics_list_queue";
	private final static String TOPICS_LIST_EXCHANGE = "topics_list_exchange";
	private final static String TOPIC_EXCHANGE = "topic_exchange";

	private final ConnectionFactory connectionFactory;
	private final Map<String, String> queues;
	private final DeliverCallback callback;
	private Connection connection;
	private Channel channel;

	public RMQConsumer() {
		this.queues = new HashMap<>();
		this.connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		this.callback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
			System.out.println("[*] MESSAGE [" + delivery.getEnvelope().getRoutingKey() + "]: " + message);
		};
	}

	public RMQConsumer connect() throws Exception {
		this.connection = connectionFactory.newConnection();
		Thread connectionThread = new Thread(() -> {
			try {
				this.channel = connection.createChannel();
				channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
				channel.exchangeDeclare(TOPICS_LIST_EXCHANGE, BuiltinExchangeType.DIRECT);
				channel.queueDeclare(TOPICS_LIST_QUEUE, false, false, false, null);
				channel.queueBind(TOPICS_LIST_QUEUE, TOPICS_LIST_EXCHANGE, "");
				channel.basicConsume(TOPICS_LIST_QUEUE, true, (consumerTag, delivery) -> {
					String theme = new String(delivery.getBody(), StandardCharsets.UTF_8);
					System.out.println("Topic available: " + theme);
				}, consumerTag -> {
				});
			} catch (Exception ex) {
				logger.error("Connection error.", ex);
			}
		}, "ConnectionThread");
		connectionThread.setDaemon(true);
		connectionThread.start();

		return this;
	}

	public void subscribe(String topic) throws IOException {
		String queue = channel.queueDeclare().getQueue();
		channel.queueBind(queue, TOPIC_EXCHANGE, topic);
		channel.basicConsume(queue, true, callback, consumerTag -> {});
		queues.put(topic, queue);
	}

	public void unsubscribe(String topic) throws IOException {
		String queue = queues.get(topic);
		if (queue != null) {
			channel.queueUnbind(queue, TOPIC_EXCHANGE, topic);
			queues.remove(topic);
		}
	}

	@Override
	public void close() throws Exception {
		this.channel.close();
		this.connection.close();
	}
}
