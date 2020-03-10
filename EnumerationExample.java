package com.java.demo1;

public class EnumerationExample {

	public static void main(String[] args) {
	
		//gets all the enums
		Month[] months=Month.values();
		for(Month month:months)
		{
			System.out.println(month.valueOf(month.name()));
			month.display();
		}

	}

}

/**
 * Example of a enum storing variables and having constructors and methods
 * All enums inherit java.lang.Enum
 * @author satyajitdas
 *
 */

enum Month {
	SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(6);

	int value;

//enum constructor can have only private constructors
	//Enum cant be extended or implemented
	Month(int value) {
		this.value = value;
	}

	public void display() {
		System.out.println("Ordinal->" + this.ordinal() + "name-> " + this.name() + "value->" + value);
	}
}
