package ru.bulldog.java2lesson1;

public class Cat extends Athlete {

	private final String name;

	public Cat(String name) {
		super(200, 3.0);
		this.name = name;
	}

	@Override
	public boolean jump(double height) {
		if (height <= jumpHeight) {
			System.out.println(name + " jumped over a " + height + " meter high wall.");
			return true;
		}
		System.out.println(name + " can't jump " + height + " meters.");
		return false;
	}

	@Override
	public boolean run(int length) {
		System.out.println(name + " run " + Math.min(length, runLength));
		return length <= runLength;
	}
}
