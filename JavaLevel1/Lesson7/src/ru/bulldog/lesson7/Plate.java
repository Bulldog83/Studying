package ru.bulldog.lesson7;

public class Plate {

	private final int maxFoodAmount;
	private int foodAmount;

	public Plate(int maxFoodAmount) {
		this.maxFoodAmount = maxFoodAmount;
	}

	public int getFoodAmount() {
		return foodAmount;
	}

	public void getFood(int amount) {
		foodAmount = amount < foodAmount ? foodAmount - amount : 0;
	}

	public void putFood(int amount) {
		foodAmount = Math.min(maxFoodAmount, amount);
	}
}
