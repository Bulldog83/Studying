package ru.bulldog.java3lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

	private final Semaphore semaphore;

	public Tunnel(int length) {
		this.length = length;
		this.description = "Тоннель " + length + " метров";
		this.semaphore = new Semaphore(Lesson5.CARS_COUNT / 2);
	}

	@Override
	public void go(Car c) {
		try {
			try {
				System.out.println(c.getName() + " готовится к этапу (ждет): " + description);
				semaphore.acquire();
				System.out.println(c.getName() + " начал этап: " + description);
				Thread.sleep(length / c.getSpeed() * 1000L);
				semaphore.release();
			} catch (InterruptedException ex) {
				ex.printStackTrace(System.out);
			} finally {
				System.out.println(c.getName() + " закончил этап: " + description);
			}
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
}
