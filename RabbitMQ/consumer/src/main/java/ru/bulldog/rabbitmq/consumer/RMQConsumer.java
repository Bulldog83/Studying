package ru.bulldog.rabbitmq.consumer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RMQConsumer implements AutoCloseable {
	private final static Logger logger = LoggerFactory.getLogger(RMQConsumer.class);
	private final static String EXCHANGE_NAME = "topic_exchange";

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
			System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");

		};
	}

	public RMQConsumer connect() throws Exception {
		this.connection = connectionFactory.newConnection();
		this.channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

		return this;
	}

	public void subscribe(String topic) throws IOException {
		String queue = channel.queueDeclare().getQueue();
		channel.queueBind(queue, EXCHANGE_NAME, topic);
		channel.basicConsume(queue, true, callback, consumerTag -> {});
		queues.put(topic, queue);
	}

	public void unsubscribe(String topic) throws IOException {
		String queue = queues.get(topic);
		if (queue != null) {
			channel.queueUnbind(queue, EXCHANGE_NAME, topic);
			queues.remove(topic);
		}
	}

	@Override
	public void close() throws Exception {
		this.channel.close();
		this.connection.close();
	}
}
