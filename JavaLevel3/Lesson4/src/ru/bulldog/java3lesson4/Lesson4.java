package ru.bulldog.java3lesson4;

public class Lesson4 {

	private final static Object MONITOR_A = new Object();
	private final static Object MONITOR_B = new Object();
	private final static Object MONITOR_C = new Object();

	public static void main(String[] args) {
		//printA();

		MFU mfu = new MFU();
		new Thread(mfu::doCopy, "User1").start();
		new Thread(mfu::doPrint, "User2").start();
		new Thread(mfu::doPrint, "User3").start();
		new Thread(mfu::doScan, "User4").start();
		new Thread(mfu::doCopy, "User5").start();
		new Thread(mfu::doScan, "User6").start();
		new Thread(mfu::doPrint, "User7").start();
		new Thread(mfu::doCopy, "User8").start();
		new Thread(mfu::doScan, "User9").start();
		new Thread(mfu::doCopy, "User10").start();
	}

	private static void printA () {
		new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					System.out.print('A');
					synchronized (MONITOR_B) {
						MONITOR_B.notify();
					}
					if (i < 4) {
						synchronized (MONITOR_A) {
							MONITOR_A.wait();
						}
					}
				}
			} catch (InterruptedException ignored) {}
		}).start();

		printB();
	}

	private static void printB () {
		new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					System.out.print('B');
					synchronized (MONITOR_C) {
						MONITOR_C.notify();
					}
					if (i < 4) {
						synchronized (MONITOR_B) {
							MONITOR_B.wait();
						}
					}
				}
			} catch (InterruptedException ignored) {}
		}).start();

		printC();
	}

	private static void printC () {
		new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					System.out.print('C');
					synchronized (MONITOR_A) {
						MONITOR_A.notify();
					}
					if (i < 4) {
						synchronized (MONITOR_C) {
							MONITOR_C.wait();
						}
					}
				}
			} catch (InterruptedException ignored) {}
		}).start();
	}
}
