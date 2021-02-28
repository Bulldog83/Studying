package ru.bulldog.java2lesson5;

import java.util.Arrays;

public class Lesson5 {

	public static void main(String[] args) {

		float[] array = new float[10000000];
		Arrays.fill(array, 1.0F);

		System.out.println("Start calculate...");
		calcBasic(array.clone());
		calcAsync(array.clone());
	}

	private static void calc(float[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = (float) (array[i] * Math.sin(0.2f + i / 5.0f) * Math.cos(0.2f + i / 5.0f) * Math.cos(0.4f + i / 2.0f));
		}
	}

	private static void calcBasic(float[] array) {
		long start = System.currentTimeMillis();
		calc(array);
		System.out.println("Basic calc time: " + (System.currentTimeMillis() - start));
	}

	private static void calcAsync(float[] array) {
		int len = array.length;
		int halfLen = len / 2;

		long start = System.currentTimeMillis();
		final float[] leftHalf = Arrays.copyOfRange(array, 0, halfLen);
		final float[] rightHalf = Arrays.copyOfRange(array, halfLen, len);
		Thread firstTask = new Thread(() -> {
			calc(leftHalf);
		});
		Thread secondTask = new Thread(() -> {
			calc(rightHalf);
		});

		firstTask.start();
		secondTask.start();
		try {
			firstTask.join();
			secondTask.join();
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		System.arraycopy(leftHalf, 0, array, 0, halfLen);
		System.arraycopy(rightHalf, 0, array, halfLen, halfLen);

		System.out.println("Async calc time: " + (System.currentTimeMillis() - start));
	}
}
