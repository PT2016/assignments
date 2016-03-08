package polynomials;

import java.util.ArrayList;
import java.util.Iterator;

public class Polynomial {

	private ArrayList<Monom> arrayMonoms;
	private int degree;

	public Polynomial() {
		arrayMonoms = new ArrayList<Monom>();
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public void addMonom(Monom m) {
		arrayMonoms.add(m);
	}

	public int getLength() {
		return arrayMonoms.size();
	}

	public ArrayList<Monom> getArray() {
		return arrayMonoms;
	}

	public int getDegree() {
		return degree;
	}

}
