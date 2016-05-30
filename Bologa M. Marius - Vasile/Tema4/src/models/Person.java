package models;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class Person implements Observer, Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

	public Person(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Account a = (Account) arg0;
		Double sum = (double) arg1;

		if (sum > 0)
			JOptionPane.showMessageDialog(null,
					name + ", " + Double.toString(sum) + " has been deposited to your account " + a.getId());
		else

			JOptionPane.showMessageDialog(null,
					name + ", " + Double.toString(-sum) + " has been withdrawn from your account " + a.getId());
	}

	@Override
	public boolean equals(Object pers) {
		if (!(pers instanceof Person))
			return false;
		else {
			Person p = (Person) pers;
			if (p.getId() != id)
				return false;
			if (!p.getName().equals(name))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int code = 0;
		code += id;
		int nameCode = 0;
		for (int i = 0; i < name.length(); i++) {
			nameCode += name.charAt(i);
		}

		code += nameCode;
		return code;
	}

}
