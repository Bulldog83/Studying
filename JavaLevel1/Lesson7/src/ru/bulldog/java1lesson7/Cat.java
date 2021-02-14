package ru.bulldog.java1lesson7;

public class Cat {

	private final String name;
	private final int hunger;

	private boolean hungry = true;

	public Cat(String name, int hunger) {
		this.name = name;
		this.hunger = hunger;
	}

	public String getName() {
		return name;
	}

	public void eat(Plate plate) {
		if (hungry && plate.getFoodAmount() >= hunger) {
			plate.getFood(hunger);
			hungry = false;
		}
	}

	public boolean isHungry() {
		return hungry;
	}
}
