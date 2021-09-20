package ru.bulldog.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class RMQProducer implements AutoCloseable {
	private final static Logger logger = LoggerFactory.getLogger(RMQProducer.class);
	private final static String EXCHANGE_NAME = "topic_exchange";

	private final ConnectionFactory connectionFactory;
	private Connection connection;
	private Channel channel;

	public RMQProducer() {
		this.connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
	}

	public RMQProducer connect() {
		Thread connectionThread = new Thread(() -> {
			try {
				this.connection = connectionFactory.newConnection();
				this.channel = connection.createChannel();
				channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}, "ConnectionThread");
		connectionThread.setDaemon(true);
		connectionThread.start();

		logger.info("Service started.");

		return this;
	}

	public void publish(String topic, String message) throws Exception {
		checkConnection();
		channel.basicPublish(EXCHANGE_NAME, topic, null, message.getBytes(StandardCharsets.UTF_8));
	}

	private void checkConnection() {
		assert connection != null;
		assert connection.isOpen();
		assert channel != null;
		assert channel.isOpen();
	}

	@Override
	public void close() throws Exception {
		this.channel.close();
		this.connection.close();
	}
}
