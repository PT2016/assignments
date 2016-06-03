package Model;

public class Operations {

	/*
	 * This class contains different operations that can be done on polynomials
	 */

	/* Addition of 2 polynomials */
	public Polynomial addPolynomials(Polynomial p1, Polynomial p2) {
		int i;
		int[] addCoefficients;
		int diffLength = getDiffLength(p1.getDegree(), p2.getDegree());

		if (p1.getDegree() > p2.getDegree()) {
			addCoefficients = new int[p1.getDegree() + 1];// maximum length

			for (i = 0; i < diffLength; i++) {
				addCoefficients[i] = p1.getThisCoeff(i);
			}
			for (i = diffLength; i <= p1.getDegree(); i++) {
				addCoefficients[i] = p1.getThisCoeff(i) + p2.getThisCoeff(i - diffLength);
			}
		} else {
			addCoefficients = new int[p2.getDegree() + 1];
			for (i = 0; i < diffLength; i++) {
				addCoefficients[i] = p2.getThisCoeff(i);
			}
			for (i = diffLength; i <= p2.getDegree(); i++) {
				addCoefficients[i] = p2.getThisCoeff(i) + p1.getThisCoeff(i - diffLength);
			}
		}

		return new Polynomial(addCoefficients);

	}

	/*
	 * Subtraction of 2 polynomials: Subtract the second one from the first one.
	 */
	public Polynomial subtractPolynomials(Polynomial p1, Polynomial p2) {
		int i;
		int[] subCoefficients;
		int diffLength = getDiffLength(p1.getDegree(), p2.getDegree());

		if (p1.getDegree() >= p2.getDegree()) {
			subCoefficients = new int[p1.getDegree() + 1];// maximum length

			for (i = 0; i < diffLength; i++) {
				subCoefficients[i] = p1.getThisCoeff(i);
			}
			for (i = diffLength; i <= p1.getDegree(); i++) {
				subCoefficients[i] = p1.getThisCoeff(i) - p2.getThisCoeff(i - diffLength);
			}
		} else {
			subCoefficients = new int[p2.getDegree() + 1];
			for (i = 0; i < diffLength; i++) {
				subCoefficients[i] = -p2.getThisCoeff(i);
			}
			for (i = diffLength; i <= p2.getDegree(); i++) {
				subCoefficients[i] = p1.getThisCoeff(i - diffLength) - p2.getThisCoeff(i);
			}
		} /*
			 * else subCoefficients = new int[p1.getDegree() + 1]; for (i = 0; i
			 * <= p1.getDegree(); i++) { //subCoefficients[i] =
			 * p1.getThisCoeff(i) - p2.getThisCoeff(i); }
			 */

		return new Polynomial(subCoefficients);
	}

	/* Multiplication of two polynomials */
	public Polynomial multiplyPolynomials(Polynomial p1, Polynomial p2) {
		int i, j;
		int[] mulCoefficients = new int[p1.getDegree() + p2.getDegree() + 1];
		for (i = 0; i <= p1.getDegree(); i++) {
			for (j = 0; j <= p2.getDegree(); j++) {
				mulCoefficients[i + j] = mulCoefficients[i + j] + (p1.getThisCoeff(i) * p2.getThisCoeff(j));
			}
		}
		return new Polynomial(mulCoefficients);
	}

	/* Division of two polynomials */
	// work in progress........
	public Polynomial dividePolynomials(Polynomial p1, Polynomial p2) {

		Polynomial bottom, middle;
		middle = p1;
		Polynomial end = new Polynomial(0, 0);
		int n = p1.getDegree() - p2.getDegree();
		double[] coeff = new double[n + 1];
		int i;
		for (i = 0; i <= n; i++) {
			coeff[i] = 0;
		}
		do {
			coeff[n] = middle.getThisCoeff(middle.getDegree()) / p2.getThisCoeff(p2.getDegree());
			bottom = multiplyPolynomials(p2, new Polynomial(coeff[n], n));
			middle = subtractPolynomials(middle, bottom);
			n--;
		} while (!middle.equals(end) && n >= 0);
		return new Polynomial(coeff);

	}

	/// last value is discarded, because it always equals 1
	public Polynomial findDerivative(Polynomial p1) {
		int i;
		int[] derivativeCoeff = new int[p1.getDegree()];
		for (i = 0; i < p1.getDegree(); i++) {
			derivativeCoeff[i] = p1.getThisCoeff(i) * (p1.getDegree() - i);
		}
		return new Polynomial(derivativeCoeff);
	}

	public double evaluatePolynomial(Polynomial p, double x) {
		double result = 0;
		int i;
		for (i = 0; i <= p.getDegree(); i++) {
			result += p.getThisCoeff(i) * Math.pow(x, p.getDegree() - i);
		}
		return result;

	}

	// working on it..
	public Polynomial findIntegral(Polynomial p) {
		int[] integralCoeff = new int[p.getDegree() + 2];
		for (int i = 0; i <= p.getDegree(); i++) {
			integralCoeff[i] = (p.getThisCoeff(i) / (i + p.getDegree() + 1));
		}
		integralCoeff[p.getDegree() + 1] = 0;
		return new Polynomial(integralCoeff);
	}

	// return integral from a to b

	public double findDefiniteIntegral(double a, double b, Polynomial p) {
		Polynomial integral = findIntegral(p);
		return evaluatePolynomial(integral, b) - evaluatePolynomial(integral, a);
	}

	public int getDiffLength(int degree1, int degree2) {
		return Math.abs(degree1 - degree2);
	}

	public String toString(Polynomial p) {
		int degree = p.getDegree();

		if (degree == -1)
			return "0";
		if (degree == 0)
			return "" + p.getThisCoeff(0);
		if (degree == 1)
			return p.getThisCoeff(1) + "x + " + p.getThisCoeff(1);
		String s = p.getThisCoeff(degree) + "x^" + degree;
		for (int i = degree - 1; i >= 0; i--) {
			if (p.getThisCoeff(i) == 0)
				continue;
			else if (p.getThisCoeff(1) > 0)
				s = s + " + " + (p.getThisCoeff(1));
			else if (p.getThisCoeff(1) < 0)
				s = s + " - " + (-p.getThisCoeff(1));
			if (i == 1)
				s = s + "x";
			else if (i > 1)
				s = s + "x^" + i;
		}
		return s;
	}

}
