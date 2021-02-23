package ru.bulldog.java2lesson3;

import java.util.HashMap;
import java.util.Map;

public class Lesson3 {

	public static void main(String[] args) throws Exception {

		String[] words = new String[] {
			"lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipisicing", "elit", "magnam", "quaerat",
			"distinctio", "veniam", "soluta", "sit", "laboriosam", "enim", "labore", "ea", "temporibus", "dignissimos",
			"ex", "modi", "sint", "deleniti", "autem", "aliquam", "quis", "id", "ut", "quaerat", "molestiae",
			"exercitationem", "est", "consequuntur", "eum", "atque", "blanditiis", "necessitatibus", "labore",
			"cumque", "laudantium", "repudiandae", "ex", "accusamus", "dolorum", "molestias", "tenetur", "at",
			"tenetur", "consequatur", "beatae", "at", "esse", "laboriosam", "voluptas", "id", "vitae", "quam", "ex",
			"eum", "illum", "optio", "corporis", "quam", "architecto", "similique", "dolorem", "excepturi",
			"repudiandae", "in", "nisi", "ipsam", "totam", "consequuntur", "eveniet", "aspernatur", "voluptas",
			"velit", "repellat", "repellendus", "consequatur", "molestiae", "at", "neque", "dolore", "odio", "quaerat",
			"aut", "facere", "fugit", "impedit", "consequatur", "laborum", "libero", "culpa", "vel", "consequuntur",
			"eos", "ipsam", "itaque"
		};

		Map<String, Integer> wordsMap = new HashMap<>();
		for (String word : words) {
			int count = wordsMap.getOrDefault(word, 0);
			wordsMap.put(word, ++count);
		}
		System.out.println(wordsMap);

		PhoneBook phoneBook = new PhoneBook();
		phoneBook.add("Adam", "5554433");
		phoneBook.add("Adam", "5554422");
		phoneBook.add("Frodo", "1234567");
		phoneBook.add("Baggings", "7654321");
		phoneBook.add("Artur", "8246571");

		System.out.println("Adam: " + phoneBook.get("Adam"));
		System.out.println("Artur: " + phoneBook.get("Artur"));
	}
}
