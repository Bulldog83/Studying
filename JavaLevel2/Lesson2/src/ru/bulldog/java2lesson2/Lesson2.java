package ru.bulldog.java2lesson2;

import java.text.ParseException;

public class Lesson2 {

	public static void main(String[] args) {
		String[][] array = new String[][] {
			{ "5", "8", "-4", "12" },
			{ "15", "-10", "4", "10" },
			{ "-2", "1", "12", "9" },
			{ "1", "6", "8", "4" }
		};
		try {
			sumArray(array);
		} catch (ArrayLengthException | ArrayDataException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void sumArray(String[][] array) throws ArrayLengthException, ArrayDataException {
		if (array.length != 4) {
			throw new ArrayLengthException(array.length);
		}
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if (array[i].length != 4) {
				throw new ArrayLengthException(i, array.length);
			}
			for (int j = 0; j < 4; j++) {
				try {
					sum += Integer.parseInt(array[i][j]);
				} catch (ArrayIndexOutOfBoundsException ex) {
					throw new ArrayLengthException(i, j);
				} catch (NumberFormatException ex) {
					throw new ArrayDataException(i, j);
				}
			}
		}
		System.out.println("Array sum: " + sum);
	}
}
