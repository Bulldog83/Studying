package ru.bulldog.java2lesson1;

public class Robot extends Athlete {

	private final String name;

	public Robot(String name) {
		super(1000, 0.0);
		this.name = name;
	}

	@Override
	public boolean jump(double height) {
		System.out.println(name + " can't jump.");
		return false;
	}

	@Override
	public boolean run(int length) {
		System.out.println(name + " run " + Math.min(length, runLength));
		return length <= runLength;
	}
}
