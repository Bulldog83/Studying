package ru.bulldog.java3lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestApp {

	private final static Comparator<Method> PRIORITY_COMPARATOR = (current, next) -> {
		int currentPriority = current.getAnnotation(Test.class).priority();
		currentPriority = currentPriority < 1 ? 1 : Math.min(currentPriority, 10);
		int nextPriority = next.getAnnotation(Test.class).priority();
		nextPriority = nextPriority < 1 ? 1 : Math.min(nextPriority, 10);
		return -1 * Integer.compare(currentPriority, nextPriority);
	};

	public static void start(Class<?> target) throws InvocationTargetException, IllegalAccessException {
		Method[] methods = target.getMethods();
		Method beforeSuite = null, afterSuite = null;
		List<Method> tests = new ArrayList<>();
		for (Method method : methods) {
			if (method.isAnnotationPresent(BeforeSuite.class)) {
				if (beforeSuite != null) {
					throw new RuntimeException("More than one BeforeSuite method present.");
				}
				beforeSuite = method;
			} else if (method.isAnnotationPresent(AfterSuite.class)) {
				if (afterSuite != null) {
					throw new RuntimeException("More than one AfterSuite method present.");
				}
				afterSuite = method;
			} else if (method.isAnnotationPresent(Test.class)) {
				tests.add(method);
			}
		}
		if (tests.size() > 0) {
			tests.sort(PRIORITY_COMPARATOR);
			if (beforeSuite != null) {
				beforeSuite.invoke(target);
			}
			for (Method test : tests) {
				test.invoke(target);
			}
			if (afterSuite != null) {
				afterSuite.invoke(target);
			}
		}
	}

	public static void start(String target) throws Exception {
		start(Class.forName(target));
	}
}
