package ru.bulldog.java2lesson1;

public class Course {

	private final Obstacle[] obstacles;
	private int currentObstacle = 0;

	public Course() {
		obstacles = new Obstacle[] {
			new RunningTrack(50),
			new Wall(1.5),
			new RunningTrack(75),
			new Wall(1),
			new RunningTrack(50),
			new Wall(2.5),
			new RunningTrack(100)
		};
	}

	public boolean hasNextObstacle() {
		return currentObstacle < obstacles.length;
	}

	public Obstacle getNextObstacle() {
		Obstacle current = obstacles[currentObstacle];
		currentObstacle++;
		return current;
	}

	public void reset() {
		currentObstacle = 0;
	}
}
