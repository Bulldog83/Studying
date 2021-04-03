package ru.bulldog.java3lesson5;

public class Car implements Runnable {

	private static int CARS_COUNT;

	private final int speed;
	private final String name;
	private Race race;

	public Car(int speed) {
		this.speed = speed;
		CARS_COUNT++;
		this.name = "Участник #" + CARS_COUNT;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public String getName() {
		return name;
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public void run() {
		try {
			System.out.println(name + " готовится");
			Thread.sleep(500 + (int) (Math.random() * 800.0));
			System.out.println(name + " готов");
			race.markReadiness();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
		for (int i = 0; i < race.getStages().size(); i++) {
			race.getStages().get(i).go(this);
		}
		if (race.checkFinish()) {
			System.out.println(name + " ПОБЕДИЛ!!!");
		} else {
			System.out.println(name + " финишировал!");
		}
		race.markReadiness();
	}

	static {
		CARS_COUNT = 0;
	}
}
