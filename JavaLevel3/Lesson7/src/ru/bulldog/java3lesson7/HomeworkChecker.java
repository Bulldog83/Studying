package ru.bulldog.java3lesson7;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;

public class HomeworkChecker {

	private final static File CLASSES_DIR = new File("compiled");
	private final static FileFilter CLASS_FILTER = file -> file.getName().endsWith(".class");

	public static void main(String[] args) {
		try {
			check();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void check() throws Exception {
		if (!CLASSES_DIR.exists() || !CLASSES_DIR.isDirectory()) {
			throw new RuntimeException("'compiled' directory not exists or not directory.");
		}
		checkDir(CLASSES_DIR);
	}

	private static void checkDir(File directory) throws Exception {
		if (!directory.isDirectory()) return;
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					checkDir(file);
				} else {
					checkFile(file);
				}
			}
		}
	}

	private static void checkFile(File file) throws Exception {
		if (!CLASS_FILTER.accept(file)) return;
		String className = file.getName().split("\\.")[0];
		URL classURL = file.toURI().toURL();
		Class<?> checkClass = URLClassLoader.newInstance(new URL[] { classURL }).loadClass(className);
		Constructor<?> constructor = checkClass.getConstructor();
		Object obj = constructor.newInstance();
		Method[] methods = checkClass.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getReturnType() == Integer.class && method.getParameterCount() == 2) {
				Parameter[] parameters = method.getParameters();
				if (parameters.length == 0) continue;
				boolean valid = true;
				for (Parameter parameter : parameters) {
					valid = parameter.getType() == Integer.class;
					if (!valid) break;
				}
				if (valid) {
					System.out.printf("Result for %s::%s = %d%n\n", className, method.getName(),
							(int) method.invoke(obj, 2, 2));
				}
			}
		}
	}
}
