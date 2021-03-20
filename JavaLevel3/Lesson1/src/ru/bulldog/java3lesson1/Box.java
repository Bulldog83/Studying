package ru.bulldog.java3lesson1;

import ru.bulldog.java3lesson1.fruits.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {

	private final List<T> fruits;

	public Box() {
		this.fruits = new ArrayList<>();
	}

	@SafeVarargs
	public Box(T... fruits) {
		this.fruits = Lesson1.arrayToList(fruits);
	}

	public void put(T fruit) {
		fruits.add(fruit);
	}

	@SafeVarargs
	public final void putAll(T... fruits) {
		this.fruits.addAll(Arrays.asList(fruits));
	}

	public void transferTo(Box<? super T> dest) {
		dest.fruits.addAll(fruits);
		fruits.clear();
	}

	public float getWeight() {
		if (fruits.size() == 0) return 0F;
		float fruitWeight = fruits.get(0).getWeight();
		return fruits.size() * fruitWeight;
	}

	public boolean compare(Box<?> another) {
		return Float.compare(getWeight(), another.getWeight()) == 0;
	}
}
