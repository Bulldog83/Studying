package ru.bulldog.java3lesson6.main;

import java.util.ArrayList;
import java.util.List;

public class Lesson6 {

	public static int[] getAllAfterLast4(int... array) throws RuntimeException {
		if (array.length == 0) {
			throw new RuntimeException("Input can't be empty");
		}
		List<Integer> arrayList = fromArray(array);
		int idx = arrayList.lastIndexOf(4);
		if (idx == -1) {
			throw new RuntimeException("There is no 4 in the array.");
		}
		return toArray(arrayList.subList(idx + 1, array.length));
	}

	public static boolean validate1And4(int... array) {
		if (array.length == 0) return false;
		int one = 0, four = 0;
		for (int val : array) {
			if (val == 1 || val == 4) {
				if (val == 1) one++;
				else four++;
			} else {
				return false;
			}
		}
		return one > 0 && four > 0;
	}

	private static List<Integer> fromArray(int[] array) {
		List<Integer> arrayList = new ArrayList<>();
		for (int val : array) {
			arrayList.add(val);
		}
		return arrayList;
	}

	private static int[] toArray(List<Integer> list) {
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}
}
