package ru.bulldog.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public class RMQProducer implements AutoCloseable {
	private final static Logger logger = LoggerFactory.getLogger(RMQProducer.class);
	private final static String TOPICS_LIST_QUEUE = "topics_list_queue";
	private final static String TOPICS_LIST_EXCHANGE = "topics_list_exchange";
	private final static String TOPIC_EXCHANGE = "topic_exchange";

	private final ConnectionFactory connectionFactory;
	private Connection connection;
	private Channel channel;

	public RMQProducer() {
		this.connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
	}

	public RMQProducer connect() throws Exception {
		this.connection = connectionFactory.newConnection();
		Thread connectionThread = new Thread(() -> {
			try {
				this.channel = connection.createChannel();
				channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}, "ConnectionThread");
		connectionThread.setDaemon(true);
		connectionThread.start();

		logger.info("Service started.");

		return this;
	}

	public void publishTopics(Set<String> topics) {
		try (Channel topicChannel = connection.createChannel()) {
			topicChannel.exchangeDeclare(TOPICS_LIST_EXCHANGE, BuiltinExchangeType.DIRECT);
			topicChannel.queueDeclare(TOPICS_LIST_QUEUE, false, false, false, null);
			topicChannel.queueBind(TOPICS_LIST_QUEUE, TOPICS_LIST_EXCHANGE, "");
			for (String topic : topics) {
				topicChannel.basicPublish(TOPICS_LIST_EXCHANGE, "", null, topic.getBytes(StandardCharsets.UTF_8));
			}
		} catch (Exception ex) {
			logger.warn("Topics publish error.", ex);
		}
	}

	public void publish(String topic, String message) throws Exception {
		checkConnection();
		channel.basicPublish(TOPIC_EXCHANGE, topic, null, message.getBytes(StandardCharsets.UTF_8));
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
