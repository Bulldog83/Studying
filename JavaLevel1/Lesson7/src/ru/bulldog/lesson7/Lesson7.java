package ru.bulldog.lesson7;

import java.util.Random;

public class Lesson7 {

	public static void main(String[] args) {
		Random rand = new Random();
		String[] names = {
				"Шарик", "Пушок", "Матвей", "Пуговка", "Боб",
				"Ватрушка", "Васька", "Вантуз", "Альбина",
				"Красотуля", "Цветочек", "Гаечка", "Василек",
				"Тортик", "Нарцис", "Одуванчик", "Жорж", "Рыжик",
				"Снежок", "Филя", "Мотильда", "Ивашка"
		};

		Plate bigPlate = new Plate(50);
		bigPlate.putFood(50);

		int catsLen = Math.max(3, rand.nextInt(10));
		Cat[] cats = new Cat[catsLen];
		for (int i = 0; i < catsLen; i++) {
			String name = names[rand.nextInt(names.length)];
			int hunger = Math.max(10, rand.nextInt(20));
			cats[i] = new Cat(name, hunger);
		}

		for (Cat cat : cats) {
			cat.eat(bigPlate);
			System.out.println("Is " + cat.getName() + " hungry? " + cat.isHungry());
		}
	}
}
