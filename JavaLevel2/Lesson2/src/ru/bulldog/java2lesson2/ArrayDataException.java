package ru.bulldog.java2lesson2;

public class ArrayDataException extends RuntimeException {

	public ArrayDataException(int row, int col) {
		super(String.format("Wrong array element at: [%d][%d]", row, col));
	}
}
