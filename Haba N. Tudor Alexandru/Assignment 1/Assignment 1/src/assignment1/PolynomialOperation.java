package assignment1;
import java.util.Arrays;
import java.util.Scanner;

public class PolynomialOperation {
	private int size1;
	private int size2;
	private int[] p1;
	private int[] p2;
	private int[] pRes = new int[500];
	private double[] m;
	int x;

	public PolynomialOperation() {
		System.out.println("Read degree of polynom 1:");
		Scanner keyboard = new Scanner(System.in);
		x = keyboard.nextInt();
		size1 = x;
		p1 = readpoly(keyboard, size1);
		System.out.println("Read degree of polynom 2:");
		x = keyboard.nextInt();
		size2 = x;
		p2 = readpoly(keyboard, size2);
	}

	private int[] readpoly(Scanner keyboard, int size) {
		int[] polynom = new int[size + 1];

		for (int i = 0; i <= size; i++) {
			System.out.println("Read the coeff for x^" + i+":");
			if (keyboard.hasNext()) {
				int x2 = keyboard.nextInt();
				polynom[i] = x2;
			}
		}
		return polynom;
	}

	protected int[] addition() {
		int i;
		if (size1 > size2) {
			for (i = size1; i > size2; i--)
				pRes[i] = p1[i];
			for (i = size2; i >= 0; i--) {
				pRes[i] = p1[i] + p2[i];
			}
		} else if (size2 > size1) {
			for (i = size2; i > size1; i--)
				pRes[i] = p2[i];
			for (i = size1; i >= 0; i--) {
				pRes[i] = p1[i] + p2[i];
			}
		} else
			for (i = 0; i <= size1; i++) {
				pRes[i] = p1[i] + p2[i];
			}
		return pRes;
	}

	protected int[] substraction() {
		int i;
		if (size1 > size2) {
			for (i = size1; i > size2; i--)
				pRes[i] = p1[i];
			for (i = size2; i >= 0; i--) {
				pRes[i] = p1[i] - p2[i];
			}
		} else if (size2 > size1) {
			for (i = size2; i > size1; i--)
				pRes[i] = -p2[i];
			for (i = size1; i >= 0; i--) {
				pRes[i] = p1[i] - p2[i];
			}
			System.out.println("INTRI");
		} else
			for (i = 0; i <= size1; i++) {
				pRes[i] = p1[i] - p2[i];
			}
		return pRes;
	}

	protected int[] multiplication() {
		int i;
		int j;
		for (i = 0; i < size1 + size2; i++) {
			pRes[i] = 0;
		}
		for (i = 0; i <= size1; i++) {
			for (j = 0; j <= size2; j++) {
				int c = i + j;
				pRes[c] = pRes[i + j] + p1[i] * p2[j];
			}
		}
		return pRes;
	}

	protected double[] integrate() {
		int i;

		double[] m = Arrays.stream(p1).asDoubleStream().toArray();
		for (i = 0; i <= size1; i++) {
			m[i] = (double)p1[i] / (i + 1);
		}
		for (int j = size1+1; j >= 1; j--) {
			System.out.print(m[j-1] + "x^" + j + "+");
		}
		return m;
	}

	protected int[] derivate() {
		int i;
		for (i = size1; i>0; i--) {
			p1[i] = p1[i] * i;
			System.out.println("p["+i+"]="+p1[i]);
		}
		return p1;
	}
	
	protected int[] division() {
		int i;
		int j;
		for (i = 0; i < size1 + size2; i++) {
			pRes[i] = 0;
		}
		for (i = 0; i <= size1; i++) {
			for (j = 0; j <= size2; j++) {
				int c = i + j;
				pRes[c] = pRes[i + j] + p1[i] * p2[j];
			}
		}
		return pRes;
	}
	
	public int[] getP1() {
        return p1;
    }
	
	public int getSize1() {
        return size1;
    }
	
	public int[] getP2() {
        return p2;
    }
	
	public int[] getPres() {
        return pRes;
    }
	
	public int getSize2() {
        return size2;
    }
	
	public double[] getM() {
        return m;
    }

}
