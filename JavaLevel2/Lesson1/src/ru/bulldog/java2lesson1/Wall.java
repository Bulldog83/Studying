package ru.bulldog.java2lesson1;

public class Wall implements Obstacle {

	private final double height;

	public Wall(double height) {
		this.height = height;
	}

	public boolean jumpOver(Jumper jumper) {
		return jumper.jump(height);
	}
}
