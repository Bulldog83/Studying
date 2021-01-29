package ru.bulldog;

public class Animal {

	protected String name;
	protected int maxRun;
	protected int maxSwim;

	public Animal(String name) {
		this.name = name;
	}

	public void run(int distance) {
		int toRun = Math.min(maxRun, distance);
		System.out.println(name + " пробежал(а) " + toRun + " м.");
	}

	public void swim(int distance) {
		int toSwim = Math.min(maxSwim, distance);
		System.out.println(name + " проплыл(а) " + toSwim + " м.");
	}
}
