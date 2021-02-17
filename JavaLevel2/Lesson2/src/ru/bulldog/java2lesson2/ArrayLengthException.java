package ru.bulldog.java2lesson2;

public class ArrayLengthException extends RuntimeException {

	public ArrayLengthException(int length) {
		super("Wrong array length: " + length);
	}
	public ArrayLengthException(int row, int length) {
		super(String.format("Wrong array length at %d: %d", row, length));
	}
}
