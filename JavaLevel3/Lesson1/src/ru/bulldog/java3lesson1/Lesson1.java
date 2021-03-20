package ru.bulldog.java3lesson1;

import ru.bulldog.java3lesson1.fruits.Apple;
import ru.bulldog.java3lesson1.fruits.Fruit;
import ru.bulldog.java3lesson1.fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson1 {

	public static void main(String[] args) {
		Box<Fruit> emptyBox = new Box<>();
		Box<Apple> apples = new Box<>(new Apple(), new Apple(), new Apple());
		Box<Orange> oranges = new Box<>(new Orange(), new Orange(), new Orange(), new Orange());

		System.out.println("Oranges and apples: " + oranges.compare(apples));
		apples.putAll(new Apple(), new Apple(), new Apple());
		System.out.println("Apples and oranges after put more apples: " + apples.compare(oranges));
		System.out.println("====");
		System.out.println("Empty box weight: " + emptyBox.getWeight());
		System.out.println("Apples weight: " + apples.getWeight());
		System.out.println("Transfer apples....");
		apples.transferTo(emptyBox);
		System.out.println("Apples weight: " + apples.getWeight());
		System.out.println("Not empty box weight: " + emptyBox.getWeight());
	}

	public static <T> void swapElements(T[] array, int idx1, int idx2) {
		T tmp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = tmp;
	}

	@SafeVarargs
	public static <T> List<T> arrayToList(T... elements) {
		return new ArrayList<>(Arrays.asList(elements));
	}
}
