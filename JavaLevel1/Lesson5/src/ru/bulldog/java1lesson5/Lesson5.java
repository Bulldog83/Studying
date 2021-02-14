package ru.bulldog.java1lesson5;

public class Lesson5 {

	public static void main(String[] args) {
		Employee[] employees = new Employee[5];

		employees[0] = new Employee("Иванов", "Иван", "Иванович", "84995553322", "15-08-1964", 45368.75);
		employees[1] = new Employee("Максимов", "Степан", "Петрович", "84996874512", "26-01-1978", 42987.25);
		employees[2] = new Employee("Зюзюкин", "Иван", "Робертович", "84995878322", "02-02-1992", 42894.32);
		employees[3] = new Employee("Островной", "Глеб", "Сергеевич", "84992223322", "15-03-1984", 52425.80);
		employees[4] = new Employee("Кожемякин", "Денис", "Андреевич", "84994356521", "06-11-1993", 43425.15);

		for (Employee employee : employees) {
			if (employee.getAge() > 40) {
				employee.printInfo();
			}
		}
	}
}
