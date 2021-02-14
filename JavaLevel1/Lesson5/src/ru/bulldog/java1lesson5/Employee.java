package ru.bulldog.java1lesson5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Employee {

	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	private String firstName;
	private String lastName;
	private String patronymic;
	private String phone;
	private Date birthDate;
	private double salary;

	public Employee(String lastName, String firstName, String patronymic, String phone, String birthDate, double salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
		this.phone = phone;
		try {
			this.birthDate = DATE_FORMAT.parse(birthDate);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		this.salary = salary;
	}

	public int getAge() {
		LocalDate currentDate = LocalDate.now();
		LocalDate birthDate = this.birthDate.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
		Period diff = Period.between(birthDate, currentDate);
		return diff.getYears();
	}

	public String getFullName() {
		return lastName + " " + firstName + " " + patronymic;
	}

	public void printInfo() {
		System.out.println("Ф.И.О.: " + getFullName());
		System.out.println("Возраст: " + getAge());
		System.out.println("Телефон: " + phone);
		System.out.println("Оклад: " + salary);
		System.out.println();
	}
}
