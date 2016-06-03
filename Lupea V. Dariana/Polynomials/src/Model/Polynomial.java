package Model;

public class Polynomial {

	private int degree; // the degree of the polynomial
	private int[] coeff; // array that holds the coefficients of the polynomial
	private double[] realCoeff;

	public Polynomial(int[] coeff) {
		this.coeff = coeff;
		degree = coeff.length - 1;
	}

	public Polynomial(double[] coeff) {
		realCoeff = coeff;
		degree = coeff.length - 1;
	}

	public Polynomial(double x, int n) {
		double[] c = new double[n + 1];
		c[n] = x;
		realCoeff = c;
		degree = n;
	}

	public int getThisCoeff(int position) {
		return coeff[position];

	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int[] getCoeff() {
		return coeff;
	}

	public void setCoeff(int[] coeff) {
		this.coeff = coeff;
	}

	public String toString() {
		if (degree == 0)
			return "" + coeff[0];
		if (degree == 1)
			return coeff[1] + "x + " + coeff[0];
		String s = coeff[degree] + "x^" + degree;
		for (int i = degree - 1; i >= 0; i--) {
			if (coeff[i] == 0)
				continue;
			else if (coeff[i] > 0)
				s = s + " + " + (coeff[i]);
			else if (coeff[i] < 0)
				s = s + " - " + (-coeff[i]);
			if (i == 1)
				s = s + "x";
			else if (i > 1)
				s = s + "x^" + i;
		}
		return s;
	}

	public int[] reverseCoefficients(int[] coeff) {
		for (int i = 0; i < coeff.length / 2; i++) {
			int temp = coeff[i];
			coeff[i] = coeff[coeff.length - i - 1];
			coeff[coeff.length - i - 1] = temp;
		}
		return coeff;
	}
}
