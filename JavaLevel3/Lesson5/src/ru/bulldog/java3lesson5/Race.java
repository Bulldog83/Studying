package ru.bulldog.java3lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {

	private final List<Stage> stages;
	private final CyclicBarrier barrier;
	private final AtomicInteger finish;

	public List<Stage> getStages() {
		return stages;
	}

	public Race(Stage... stages) {
		this.stages = new ArrayList<>(Arrays.asList(stages));
		this.barrier = new CyclicBarrier(Lesson5.CARS_COUNT + 1);
		this.finish = new AtomicInteger(0);
	}

	public void markReadiness() {
		try {
			barrier.await();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}

	public boolean checkFinish() {
		return finish.incrementAndGet() == 1;
	}
}
