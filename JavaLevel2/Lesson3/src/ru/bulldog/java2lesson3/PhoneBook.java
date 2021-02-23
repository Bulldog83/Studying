package ru.bulldog.java2lesson3;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
	
	private final Map<String, String> phones = new HashMap<>();
	
	public PhoneBook() {}
	
	public void add(String name, String phone) {
		phones.put(phone, name);
	}
	
	public List<String> get(String name) {
		//return phones.entrySet().stream().filter(entry -> entry.getValue().equals(name))
		//		.map(Map.Entry::getKey).collect(Collectors.toList());
		List<String> numbers = new ArrayList<>();
		phones.forEach((number, owner) -> {
			if (owner.equals(name)) {
				numbers.add(number);
			}
		});
		return numbers;
	}
}
