package ru.bulldog.java2lesson1;

public class Lesson1 {

	public static void main(String[] args) {
		Course course = new Course();
		int teamSize = 5;
		Team[] teams = new Team[] {
			new Team("Stars", teamSize),
			new Team("Meteors", teamSize),
			new Team("Hounds", teamSize)
		};

		for (Team team : teams) {
			team.passCourse(course);
			team.printResult();
		}
	}
}
