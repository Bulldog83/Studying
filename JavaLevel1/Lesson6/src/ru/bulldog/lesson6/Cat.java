package ru.bulldog.lesson6;

public class Cat extends Animal {

	public Cat(String name) {
		super(name);
		this.maxRun = 200;
	}

	@Override
	public void swim(int distance) {
		System.out.println(name + " боится воды!");
	}
}
