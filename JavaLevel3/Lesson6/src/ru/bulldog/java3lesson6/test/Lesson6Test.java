package ru.bulldog.java3lesson6.test;

import org.junit.Assert;
import org.junit.Test;
import ru.bulldog.java3lesson6.main.Lesson6;

public class Lesson6Test {

	@Test
	public void test1_1() {
		Assert.assertArrayEquals(new int[]{6, 7, 8}, Lesson6.getAllAfterLast4(1, 2, 3, 4, 4, 5, 4, 6, 7, 8));
	}

	@Test(expected = RuntimeException.class)
	public void test1_2() {
		Assert.assertArrayEquals(new int[] { 6, 7, 8 }, Lesson6.getAllAfterLast4(1, 2, 3, 5, 6, 7, 8));
	}

	@Test
	public void test1_3() {
		Assert.assertArrayEquals(new int[] { 1, 3 }, Lesson6.getAllAfterLast4(1, 4, 5, 5, 5, 5, 4, 4, 2, 3));
	}

	@Test
	public void test2_1() {
		Assert.assertTrue(Lesson6.validate1And4(1, 1, 1, 4, 1, 4, 1, 1));
	}

	@Test
	public void test2_2() {
		Assert.assertFalse(Lesson6.validate1And4(1, 1, 1, 4, 4, 4, 5, 1));
	}

	@Test
	public void test2_3() {
		Assert.assertFalse(Lesson6.validate1And4(1, 1, 1, 1, 1, 1, 1, 1));
	}

	@Test
	public void test2_4() {
		Assert.assertFalse(Lesson6.validate1And4(4, 4, 4, 4));
	}
}
