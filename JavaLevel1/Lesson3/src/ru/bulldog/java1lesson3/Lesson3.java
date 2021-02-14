package ru.bulldog.java1lesson3;

import java.util.Random;
import java.util.Scanner;

public class Lesson3 {

	private final static Scanner SCANNER = new Scanner(System.in);
	private final static Random RANDOM = new Random();

	public static void main(String[] args) {
		System.out.println("Select the game! Available games:\n1: Numbers\n2: Words");
		System.out.print("Your choice (enter a number): ");
		if (SCANNER.hasNextInt()) {
			int game = SCANNER.nextInt();
			switch (game) {
				case 1: numbersGame(); break;
				case 2: wordsGame(); break;
			}
		}
		System.out.println();
	}

	private static void numbersGame() {
		System.out.print("I thought of a number from 0 to 9, try to guess it in 3 tries.\nFirst try: ");
		int attempt = 0;
		int number = RANDOM.nextInt(9);
		do {
			attempt++;
			if (SCANNER.hasNextInt()) {
				int variant = SCANNER.nextInt();
				if (variant == number) {
					System.out.println("Yes, that's it!");
					break;
				} else if (attempt < 3) {
					boolean greater = variant > number;
					System.out.println("You are wrong! Your number too " + (greater ? "big!" : "small!" + " Try again!"));
				}
			}
			if (attempt > 2) {
				System.out.println("Sorry, but you loose!");
			} else {
				System.out.print("Next try: ");
			}
			SCANNER.nextLine();
		} while (attempt < 3);

		System.out.print("Do you want play again? (1 - yes, 0 - no): ");
		if (SCANNER.hasNextInt() && SCANNER.nextInt() == 1) {
			numbersGame();
		}
	}

	private static void wordsGame() {
		String[] words = {
				"apple", "orange", "lemon",
				"banana", "apricot", "avocado",
				"broccoli", "carrot", "cherry",
				"garlic", "grape", "melon", "leak",
				"kiwi", "mango", "mushroom", "nut",
				"olive", "pea", "peanut", "pear",
				"pepper", "pineapple", "pumpkin",
				"potato" };
		
		char[] stencil = "###############".toCharArray();
		String currentWord = words[RANDOM.nextInt(words.length - 1)];
		String answer = "";
		System.out.print("I thought of some word, try to guess it!\nFirst try: ");
		while (true) {
			answer = SCANNER.next();
			if (answer.equals(currentWord)) {
				System.out.println("Yes! That's it!");
				break;
			} else if (answer.equals("stop")) {
				break;
			}
			int stop = Math.min(currentWord.length(), answer.length());
			for (int i = 0; i < stop; i++) {
				char ansLetter = answer.charAt(i);
				if (currentWord.charAt(i) == ansLetter) {
					stencil[i] = ansLetter;
				}
			}
			System.out.println("No, you are wrong! Try again!");
			System.out.println("Or write 'stop' to exit, if you want.");
			System.out.println("May be, it helps?: " + new String(stencil));
			System.out.print("Next try: ");
		}

		System.out.print("Do you want play again? (1 - yes, 0 - no): ");
		if (SCANNER.hasNextInt() && SCANNER.nextInt() == 1) {
			wordsGame();
		}
	}
}
