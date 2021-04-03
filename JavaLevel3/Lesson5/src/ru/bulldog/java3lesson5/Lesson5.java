package ru.bulldog.java3lesson5;

public class Lesson5 {

	public static final int CARS_COUNT = 4;

	public static void main(String[] args) {
		System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
		Race race = new Race(new Road(60), new Tunnel(80), new Road(40));
		Car[] cars = new Car[CARS_COUNT];
		for (int i = 0; i < cars.length; i++) {
			Car newCar = new Car(20 + (int) (Math.random() * 10.0));
			newCar.setRace(race);
			cars[i] = newCar;
		}
		for (Car car : cars) {
			new Thread(car).start();
		}
		race.markReadiness();
		System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
		race.markReadiness();
		System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
	}
}
