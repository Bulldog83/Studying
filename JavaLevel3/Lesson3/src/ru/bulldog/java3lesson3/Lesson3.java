package ru.bulldog.java3lesson3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Lesson3 {

	public static void main(String[] args) {
		printFile("JavaLevel3/Lesson3/files/testprint.txt");
		joinFiles("JavaLevel3/Lesson3/files/comp_1.txt",
				"JavaLevel3/Lesson3/files/comp_2.txt",
				"JavaLevel3/Lesson3/files/comp_3.txt",
				"JavaLevel3/Lesson3/files/comp_4.txt",
				"JavaLevel3/Lesson3/files/comp_5.txt");
		readBook("JavaLevel3/Lesson3/files/last_watch.fb2");
	}

	private static void printFile(String path) {
		File file = new File(path);
		if (!checkFile(file)) return;
		try (InputStream fis = new FileInputStream(file)) {
			printData(fis);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	private static void joinFiles(String... paths) {
		List<InputStream> streams = new ArrayList<>();
		for (String path : paths) {
			File file = new File(path);
			if (!checkFile(file)) continue;
			try {
				streams.add(new FileInputStream(file));
			} catch (IOException ex) {
				ex.printStackTrace(System.out);
			}
		}
		try (SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(streams))) {
			int sym;
			while ((sym = sis.read()) != -1) {
				System.out.print((char) sym);
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	private static void readBook(String path) {
		File book = new File(path);
		if (!checkFile(book)) return;
		int pageSize = 1800;
		byte[] pageData = new byte[pageSize];
		try (RandomAccessFile raf = new RandomAccessFile(book, "r")) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Какую страницу открыть? ");
			int page = scanner.nextInt() - 1;
			if (page < 1) {
				System.out.println("Номер страницы должен быть > 0.");
				return;
			}
			raf.seek((long) page * pageSize);
			raf.read(pageData);
			System.out.println();
			System.out.println(new String(pageData, StandardCharsets.UTF_8));
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}

	private static void printData(InputStream stream) throws IOException {
		byte[] data = new byte[stream.available()];
		stream.read(data);
		System.out.println(new String(data, StandardCharsets.UTF_8));
	}

	private static boolean checkFile(File file) {
		if (!file.exists()) {
			System.out.println(file + " not exists!");
			return false;
		}
		return true;
	}
}
