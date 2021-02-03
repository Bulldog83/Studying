package ru.bulldog.lesson6;

import java.util.Random;

public class Lesson6 {

	public static void main(String[] args) {
		String[] names = {
			"Шарик", "Пушок", "Матвей", "Пуговка", "Боб",
			"Ватрушка", "Васька", "Вантуз", "Альбина",
			"Красотуля", "Цветочек", "Гаечка", "Василек",
			"Тортик", "Нарцис", "Одуванчик", "Жорж", "Рыжик",
			"Снежок", "Филя", "Мотильда", "Ивашка"
		};

		Random rand = new Random();
		int animals = 0, cats = 0, dogs = 0;
		for (int i = 0; i < rand.nextInt(100); i++) {
			String name = names[rand.nextInt(names.length)];
			Animal animal = rand.nextInt(2) == 0 ? new Cat(name) : new Dog(name);
			animal.run(rand.nextInt(600));
			animal.swim(rand.nextInt(20));
			animals++;
			if (animal instanceof Cat) {
				cats++;
			} else {
				dogs++;
			}
		}
		System.out.println();
		System.out.println("В нашем приюте " + animals + " животных: " + cats + " кошек и " + dogs + " собак.");
	}
}
