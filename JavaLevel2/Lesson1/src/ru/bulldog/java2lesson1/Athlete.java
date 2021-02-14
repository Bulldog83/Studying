package ru.bulldog.java2lesson1;

public abstract class Athlete implements Runner, Jumper {

	protected final int runLength;
	protected final double jumpHeight;

	public Athlete(int runLength, double jumpHeight) {
		this.runLength = runLength;
		this.jumpHeight = jumpHeight;
	}
}
