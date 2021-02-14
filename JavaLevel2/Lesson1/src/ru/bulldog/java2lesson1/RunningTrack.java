package ru.bulldog.java2lesson1;

public class RunningTrack implements Obstacle {

	private final int length;

	public RunningTrack(int length) {
		this.length = length;
	}

	public boolean runAlong(Runner runner) {
		return runner.run(length);
	}
}
