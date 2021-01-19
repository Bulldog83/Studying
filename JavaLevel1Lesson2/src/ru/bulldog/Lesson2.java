package ru.bulldog;

import java.util.Arrays;
import java.util.Random;

public class Lesson2 {

	public static void main(String[] args) {
		//1
		System.out.println("1.");
		int[] sequence = { 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1 };
		printArray(sequence);
		invertIntArray(sequence);
		printArray(sequence);
		System.out.println();

		//2
		System.out.println("2.");
		int[] emptyArray = new int[8];
		for (int i = 0; i < 8; i++) {
			emptyArray[i] = i * 3;
		}
		printArray(emptyArray);
		System.out.println();

		//3
		System.out.println("3.");
		int[] randomSequence = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
		System.out.println(Arrays.toString(randomSequence));
		for (int i = 0; i < randomSequence.length; i++) {
			if (randomSequence[i] < 6) {
				randomSequence[i] *= 2;
			}
		}
		printArray(randomSequence);
		System.out.println();

		//4
		System.out.println("4.");
		int[][] matrix = new int[5][5];
		int j = matrix.length - 1;
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][i] = 1;
			matrix[i][j - i] = 1;
		}
		printMatrix(matrix);
		System.out.println();

		//5
		System.out.println("5.");
		int[] randomArray = createRandomIntArray(5, 10, 100);
		printArray(randomArray);
		findMinAndMaxValue(randomArray);
		System.out.println();

		//6
		System.out.println("6.");
		findBalancedArray(5, 10, 100);
		System.out.println();

		//7
		System.out.println("7.");
		int[] arrayToShift = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		shiftArray(arrayToShift, -3);
		System.out.println();
		shiftArray(arrayToShift, 5);
	}

	private static void invertIntArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i] == 0 ? 1 : 0;
		}
	}

	private static void printMatrix(int[][] matrix) {
		for (int[] ints : matrix) {
			printArray(ints);
		}
	}

	private static int[] createRandomIntArray(int minSize, int maxSize, int valuesBound) {
		Random rand = new Random();
		int [] randomArray = new int[randomInt(minSize, maxSize)];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = rand.nextInt(valuesBound);
			double chance = Math.random();
			if (chance < 0.4) {
				randomArray[i] *= -1;
			}
		}
		return randomArray;
	}

	private static int randomInt(int minVal, int maxVal) {
		Random rand = new Random();
		int randomInt = rand.nextInt(maxVal);
		while (randomInt < minVal) {
			randomInt = rand.nextInt(maxVal);
		}
		return randomInt;
	}

	private static void findMinAndMaxValue(int[] array) {
		int[] minAndMax = { array[0], array[0] };
		for (int i = 1; i < array.length; i++) {
			minAndMax[0] = Math.min(array[i], minAndMax[0]);
			minAndMax[1] = Math.max(array[i], minAndMax[1]);
		}
		System.out.println("Min value: " + minAndMax[0]);
		System.out.println("Max value: " + minAndMax[1]);
	}

	private static boolean checkIfArrayBalanced(int[] array) {
		for (int step = 1; step < array.length; step++) {
			int last = array.length - step;
			int leftSum = 0, rightSum = 0;
			int[] leftArray = new int[last];
			int[] rightArray = new int[array.length - last];
			for (int i = 0; i < last; i++) {
				leftArray[i] = array[i];
				leftSum += array[i];
			}
			for (int i = last; i < array.length; i++) {
				rightArray[i - last] = array[i];
				rightSum += array[i];
			}
			if (leftSum == rightSum) {
				System.out.println("\nLeft side: " + Arrays.toString(leftArray));
				System.out.println("Right side: " + Arrays.toString(rightArray));
				System.out.println("Sum: " + leftSum);
				return true;
			}
		}
		return false;
	}

	private static void findBalancedArray(int minSize, int maxSize, int valuesBound) {
		int[] array;
		do {
			array = createRandomIntArray(minSize, maxSize, valuesBound);
			System.out.println(Arrays.toString(array));
		} while (!checkIfArrayBalanced(array));
	}

	private static void shiftArray(int[] array, int shift) {
		printArray(array);
		if (shift < 0) {
			leftArrayShift(array, -shift);
		} else {
			rightArrayShift(array, shift);
		}
		printArray(array);
	}

	private static void leftArrayShift(int[] array, int shift) {
		int lastElem = array.length - 1;
		for (int step = 0; step < shift; step++) {
			int buff = array[0];
			for (int i = 0; i < array.length; i++) {
				array[i] = i < lastElem ? array[i + 1] : buff;
			}
		}
	}
	private static void rightArrayShift(int[] array, int shift) {
		int lastElem = array.length - 1;
		for (int step = 0; step < shift; step++) {
			int buff = array[lastElem];
			for (int i = lastElem; i >= 0; i--) {
				array[i] = i == 0 ? buff : array[i - 1];
			}
		}
	}

	private static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
}
