package ru.bulldog.patterns.event;

public interface ActionListener {
	void onMessageReceived(String message);
	void onHandleError(String message);
	void onDisconnect();
	void onConnect();
}
