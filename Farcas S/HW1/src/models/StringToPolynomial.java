package models;

import java.util.*;

/**
 * the class responsible for generating polynomials given a string of
 * coefficents
 *
 */
public class StringToPolynomial {

	/**
	 * this method translated an input line containing coefficients in array of
	 * integer coefficients and ultimately in a polynomial
	 * 
	 * @param line
	 *            -- line inputed by the user
	 * @return -- a polynomial with the coefficients inputed by the user
	 */
	public static Polynomial stringToIntPolynomial(String line) {
		String[] coefficientsAsString = line.split(" ");
		int i, degree = coefficientsAsString.length - 1;
		Coefficient[] coefficients = new Coefficient[degree + 1];
		for (i = 0; i < coefficientsAsString.length; i++) {
			CoefficientInt c = new CoefficientInt(Integer.parseInt(coefficientsAsString[i]));
			coefficients[i] = c;
		}
		return new Polynomial(eraseLeadingZeros(coefficients, degree));
	}

	/**
	 * this method translated an input line containing coefficients in array of
	 * real coefficients and ultimately in a polynomial
	 * 
	 * @param line
	 *            -- line inputed by the user
	 * @return -- a polynomial with the coefficients inpiuted by the user
	 */
	public static Polynomial stringToRealPolynomial(String line) {
		String[] coefficientsAsString = line.split(" ");
		int i, degree = coefficientsAsString.length - 1;
		Coefficient[] coefficients = new Coefficient[degree + 1];
		for (i = 0; i < coefficientsAsString.length; i++) {
			CoefficientReal c = new CoefficientReal(Double.parseDouble(coefficientsAsString[i]));
			coefficients[i] = c;
		}
		return new Polynomial(eraseLeadingZeros(coefficients, degree));
	}

	/**
	 * this method erases any leading zeros from an array of coefficients for a
	 * certain degree
	 * 
	 * @param coefficients
	 *            -- coefficients to be refined
	 * @param degree
	 *            -- the degree of the polynomial with the sepcified
	 *            coefficients
	 * @return -- the refined coefficients
	 */
	public static Coefficient[] eraseLeadingZeros(Coefficient[] coefficients, int degree) {
		int i = -1;
		double x = 0;
		while (i < degree && x == 0) {
			i++;
			if (coefficients[0].getType() == CoefficientType.INT) {
				x = ((CoefficientInt) coefficients[i]).getCoefficient();
			} else {
				x = ((CoefficientReal) coefficients[i]).getCoefficient();
			}
		}
		if (i == degree + 1) {
			coefficients = Arrays.copyOfRange(coefficients, degree, degree + 1);
		} else {
			coefficients = Arrays.copyOfRange(coefficients, i, degree + 1);
		}
		return coefficients;
	}

}
