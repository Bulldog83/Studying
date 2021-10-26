package ru.bulldog.patterns.event;

import com.google.common.collect.Lists;

import java.util.List;

public final class EventsHandler implements FilesListener {

	private static EventsHandler instance;

	public static EventsHandler getInstance() {
		if (instance == null) {
			instance = new EventsHandler();
		}
		return instance;
	}

	private final List<ActionListener> registeredListeners = Lists.newArrayList();

	private EventsHandler() {}

	public void registerListener(ActionListener listener) {
		if (listener == this) {
			throw new IllegalArgumentException();
		}
		registeredListeners.add(listener);
	}

	public void removeListener(ActionListener listener) {
		registeredListeners.remove(listener);
	}

	@Override
	public void onConnect() {
		registeredListeners.forEach(ActionListener::onConnect);
	}

	@Override
	public void onDisconnect() {
		registeredListeners.forEach(ActionListener::onDisconnect);
	}

	@Override
	public void onMessageReceived(String message) {
		registeredListeners.forEach(listener -> listener.onMessageReceived(message));
	}

	@Override
	public void onHandleError(String message) {
		registeredListeners.forEach(listener -> listener.onHandleError(message));
	}

	@Override
	public void onFileReceived() {
		registeredListeners.forEach(listener -> {
			if (listener instanceof FilesListener) {
				((FilesListener) listener).onFileReceived();
			}
		});
	}

	@Override
	public void onFileStart(String direction, String fileName) {
		registeredListeners.forEach(listener -> {
			if (listener instanceof FilesListener) {
				((FilesListener) listener).onFileStart(direction, fileName);
			}
		});
	}

	@Override
	public void onFileProgress(double progress) {
		registeredListeners.forEach(listener -> {
			if (listener instanceof FilesListener) {
				((FilesListener) listener).onFileProgress(progress);
			}
		});
	}
}
