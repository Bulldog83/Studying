package ru.bulldog.java2lesson1;

public class Human extends Athlete {

	private final String name;

	public Human(String name) {
		super(100, 1.5);
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
