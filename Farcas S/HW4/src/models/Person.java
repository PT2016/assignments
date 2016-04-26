package models;

import java.io.Serializable;

public class Person implements Serializable {

	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Person))
			return false;
		Person p = (Person) obj;
		return p.getName().equals(this.name);
	}

	@Override
	public String toString() {
		return this.name + ", " + this.age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

}
