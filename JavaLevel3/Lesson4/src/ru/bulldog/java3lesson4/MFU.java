package ru.bulldog.java3lesson4;

public class MFU {

	private final Object scannerMonitor = new Object();
	private final Object printerMonitor = new Object();

	public MFU() {}

	public void doCopy() {
		scanInternal();
		printInternal();
	}

	public void doPrint() {
		printInternal();
	}

	public void doScan() {
		scanInternal();
		sendToDest();
	}

	private void scanInternal() {
		synchronized (scannerMonitor) {
			System.out.println(Thread.currentThread().getName() + ": Scanning document...");
		}
	}
	private void printInternal() {
		synchronized (printerMonitor) {
			System.out.println(Thread.currentThread().getName() + ": Printing document...");
		}
	}
	private void sendToDest() {
		System.out.println(Thread.currentThread().getName() + ": Send document...");
	}
}
