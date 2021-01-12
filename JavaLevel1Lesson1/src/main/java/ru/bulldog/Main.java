package ru.bulldog;

public class Main {

	public static void main(String[] args) {
		byte b = 0;
		short s = 0;
		int i = -8;
		long l = 0L;

		float f = calculate(1.0f, 2.0f, 3.0f, 4.0f);
		double d = 0.0;

		boolean clamp = clampSumm(-5, 16);
		boolean sign = checkSignNegate(i);
		char c = 'c';

		printSign(i);
		sayHello("Moscow");

		int year = 2020;

		System.out.println("clamp: " + clamp);
		System.out.println("sign: " + sign);
		System.out.printf("Is %d leap: %s%n", year, isYearLeap(year));
	}

	private static float calculate(float a, float b, float c, float d) {
		return a * (b + (c / d));
	}

	private static boolean clampSumm(int i, int j) {
		int summ = i + j;
		return summ >= 10 && summ <= 20;
	}

	private static void printSign(int i) {
		System.out.println("Have got " + (i < 0 ? "negative" : "positive") + " integer.");
	}

	private static boolean checkSignNegate(int i) {
		return !(i > 0);
	}

	private static void sayHello(String name) {
		name = name == null || name.trim().equals("") ? "World" : name;
		System.out.printf("Hello, %s!%n", name);
	}

	private static boolean isYearLeap(int year) {
		if (year % 400 == 0) return true;
		if (year % 100 == 0) return false;
		return year % 4 == 0;
	}
}
