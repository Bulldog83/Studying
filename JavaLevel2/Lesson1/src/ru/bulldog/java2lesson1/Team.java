package ru.bulldog.java2lesson1;

import java.util.Random;

public class Team {

	private final static String[] NAMES = new String[] {
		"Bernard", "Alex", "Yo Yo", "Junkie", "Sam",
		"Boris", "Fedor", "Jackson", "Lisa", "Marta",
		"Vasilis", "Anna", "Aleksander", "Jasmine",
		"Olivia", "Tom", "Jhon", "Lukas"
	};

	private final String name;
	private final Athlete[] team;
	private int succeed = 0;

	public Team(String name, int size) {
		this.name = name;
		Random rand = new Random();
		this.team = new Athlete[size];
		for (int i = 0; i < size; i++) {
			double chance = rand.nextDouble();
			String athleteName = NAMES[rand.nextInt(NAMES.length)];
			if (chance < 0.2) {
				team[i] = new Robot(athleteName);
			} else if (chance > 0.7) {
				team[i] = new Cat(athleteName);
			} else {
				team[i] = new Human(athleteName);
			}
		}
	}

	public void passCourse(Course course) {
		System.out.println("Team " + name + " start course!");
		for (Athlete athlete : team) {
			boolean success = true;
			while (course.hasNextObstacle()) {
				Obstacle nextObstacle = course.getNextObstacle();
				if (nextObstacle instanceof RunningTrack) {
					success = ((RunningTrack) nextObstacle).runAlong(athlete);
				} else {
					success = ((Wall) nextObstacle).jumpOver(athlete);
				}
				if (!success) break;
			}
			course.reset();
			if (success) succeed++;
		}
		System.out.println("Team " + name + " finish!");
		System.out.println();
	}

	public void printResult() {
		System.out.println(succeed + " athletes in the " + name + " team successfully passed course!");
		System.out.println();
	}
}
