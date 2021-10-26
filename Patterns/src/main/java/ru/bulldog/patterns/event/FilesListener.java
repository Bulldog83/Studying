package ru.bulldog.patterns.event;

public interface FilesListener extends ActionListener {
	void onFileStart(String direction, String fileName);
	void onFileProgress(double progress);
	void onFileReceived();
}
