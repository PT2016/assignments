package helpers;

import java.util.StringTokenizer;

import main.IntegerP;
import main.RealP;

public class Operations {

	private static RealP Remain = new RealP();

	private static int max(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	public static int evaluateI(IntegerP A, int x) {
		int result = 0, i, j, pow = 1;
		for (i = 0; i <= A.getN(); i++) {
			for (j = 0; j < i; j++)
				pow = pow * x;
			result += A.coef[i]*pow;
			pow = 1;
		}
		return result;
	}

	public static float evaluateR(RealP A, int x) {
		int i, j, pow = 1;
		float result = 0;
		for (i = 0; i <= A.getN(); i++) {
			for (j = 0; j < i; j++)
				pow = pow * x;
			result += A.coef[i]*pow;
			pow = 1;
		}
		return result;
	}

	public static RealP addP(RealP A, RealP B) {
		RealP Result = new RealP();
		int i, n = max(A.getN(), B.getN());
		for (i = 0; i <= n; i++) {
			Result.coef[i] = A.coef[i] + B.coef[i];
		}
		Result.setN(n);
		return Result;
	}

	public static RealP subP(RealP A, RealP B) {
		RealP Result = new RealP();
		int i;
		Result = A;
		for (i = 0; i <= B.getN(); i++)
			Result.coef[i] -= B.coef[i];
		i = max(A.getN(), B.getN());
		Result.setN(i);
		while (i >= 0 && Result.coef[i] == 0) {
			i--;
			Result.setN(i);
		}

		return Result;
	}

	public static RealP integrateR(RealP A, int C) {
		RealP Result = new RealP();
		float aux;
		if (A.getN() != 11) {
			int i;
			for (i = A.getN(); i >= 0; i--) {
				aux = A.coef[i];
				Result.coef[i + 1] = aux / (i + 1);
			}
			Result.coef[0] = C;
			Result.setN(A.getN() + 1);
		}
		return Result;
	}

	public static IntegerP derivateI(IntegerP A) {
		IntegerP Result = new IntegerP();
		int i;

		if (A.getN() > 0)
			for (i = 0; i < A.getN(); i++) {
				Result.coef[i] = A.coef[i + 1] * (i + 1);
			}
		else if (A.getN() == 0)
			Result.coef[0] = 0;
		if (A.getN() > 0)
			Result.setN(A.getN() - 1);
		return Result;
	}
	
	public static RealP derivateR(RealP A) {
		RealP Result = new RealP();
		int i;

		if (A.getN() > 0)
			for (i = 0; i < A.getN(); i++) {
				Result.coef[i] = A.coef[i + 1] * (i + 1);
			}
		else if (A.getN() == 0)
			Result.coef[0] = 0;
		if (A.getN() > 0)
			Result.setN(A.getN() - 1);
		return Result;
	}

	public static IntegerP toPolynomI(String S) {
		IntegerP A = new IntegerP();
		int coef, n = 0;
		boolean first = true;
		String Saux;

		S = S.replace(" ", "");
		S = S.replace("x", "X");
		S = S.replace("X^", "X");
		S = S.replace("-X", "-1X");
		S = S.replace("+X", "+1X");
		S = S.replace("-", "+-");
		S = S.replace("X+", "X1+");

		StringTokenizer Ss = new StringTokenizer(S, "+");

		while (Ss.hasMoreElements()) {
			Saux = Ss.nextToken();
			if (Saux.contains("X")) {
				if (Saux.startsWith("X") && Saux.endsWith("X")) {
					n = 1;
					A.coef[n] = 1;
				} else if (Saux.endsWith("X")) {
					n = 1;
					Saux = Saux.substring(0, Saux.length() - 1);
					A.coef[n] = Integer.parseInt(Saux);
				} else if (Saux.startsWith("X")) {
					Saux = Saux.substring(1);
					n = Integer.parseInt(Saux);
					A.coef[n] = 1;
				} else {
					StringTokenizer Sb = new StringTokenizer(Saux, "X");
					coef = Integer.parseInt(Sb.nextToken());
					n = Integer.parseInt(Sb.nextToken());
					A.coef[n] = coef;
				}
				if (first) {
					first = false;
					A.setN(n);
				}
			} else {
				A.coef[0] = Integer.parseInt(Saux);
			}
		}

		return A;

	}

	public static RealP toPolynomR(String S) {
		RealP A = new RealP();
		int n = 0;
		float coef;
		boolean first = true;
		String Saux;

		S = S.replace(" ", "");
		S = S.replace("x", "X");
		S = S.replace("X^", "X");
		S = S.replace("-X", "-1X");
		S = S.replace("+X", "+1X");
		S = S.replace("-", "+-");
		S = S.replace("X+", "X1+");

		StringTokenizer Ss = new StringTokenizer(S, "+");

		while (Ss.hasMoreElements()) {
			Saux = Ss.nextToken();
			if (Saux.contains("X")) {
				if (Saux.startsWith("X") && Saux.endsWith("X")) {
					n = 1;
					A.coef[n] = 1;
				} else if (Saux.endsWith("X")) {
					n = 1;
					Saux = Saux.substring(0, Saux.length() - 1);
					A.coef[n] = Float.parseFloat(Saux);
				} else if (Saux.startsWith("X")) {
					Saux = Saux.substring(1);
					n = Integer.parseInt(Saux);
					A.coef[n] = 1;
				} else {
					StringTokenizer Sb = new StringTokenizer(Saux, "X");
					coef = Float.parseFloat(Sb.nextToken());
					n = Integer.parseInt(Sb.nextToken());
					A.coef[n] = coef;
				}
				if (first) {
					first = false;
					A.setN(n);
				}
			} else {
				A.coef[0] = Float.parseFloat(Saux);
			}
		}

		return A;

	}
	
	private static String coefSel(int coef) {
		String S = "";
		if (coef > 0)
			if (coef == 1)
				S += "+X";
			else
				S += "+" + coef + "X";
		else if (coef < 0)
			if (coef == -1)
				S += "-X";
			else
				S += coef + "X";
		return S;
	}

	public static String toStringI(IntegerP A) {
		String s = "";
		if (A.getN() > 0) {
			for (int i = A.getN(); i >= 2; i--)
				if (A.coef[i] != 0)
					s += coefSel(A.coef[i]) + "^" + i;
			if (A.coef[1] != 0)
				s += coefSel(A.coef[1]);
			if (A.coef[0] > 0)
				s += "+" + A.coef[0];
			else if (A.coef[0] < 0)
				s += A.coef[0];

			if (A.coef[A.getN()] > 0)
				s = s.substring(1);
		} else
			s += A.coef[0];
		return s;
	}

	private static String coefSel(float coef) {
		String S = "";
		if (coef > 0)
			if (coef == 1)
				S += "+X";
			else
				S += "+" + coef + "X";
		else if (coef < 0)
			if (coef == -1)
				S += "-X";
			else
				S += coef + "X";
		return S;
	}

	public static String toStringR(RealP A) {
		String s = "";
		if (A.getN() > 0) {
			for (int i = A.getN(); i >= 2; i--)
				if (A.coef[i] != 0)
					s += coefSel(A.coef[i]) + "^" + i;
			if (A.coef[1] != 0)
				s += coefSel(A.coef[1]);
			if (A.coef[0] > 0)
				s += "+" + A.coef[0];
			else if (A.coef[0] < 0)
				s += A.coef[0];
			if (A.coef[A.getN()] > 0)
				s = s.substring(1);
		} else
			s += A.coef[0];
		return s;
	}

	public static RealP mulP(RealP A, RealP B) {
		RealP Result = new RealP();
		int i, j;
		for (i = 0; i <= 11; i++)
			Result.coef[i] = 0;
		for (i = 0; i <= A.getN(); i++)
			for (j = 0; j <= B.getN(); j++)
				Result.coef[i + j] += A.coef[i] * B.coef[j];
		Result.setN(A.getN() + B.getN());
		return Result;
	}

	public static RealP toRealP(IntegerP A) {
		RealP R = new RealP();
		for (int i = 0; i <= A.getN(); i++)
			R.coef[i] = A.coef[i];
		R.setN(A.getN());
		return R;
	}

	public static IntegerP toIntegerP(RealP A) {
		IntegerP R = new IntegerP();
		for (int i = 0; i <= A.getN(); i++)
			R.coef[i] = Math.round(A.coef[i]);
		R.setN(A.getN());
		return R;
	}

	public static RealP divP(RealP A, RealP B) {
		RealP C = new RealP();
		RealP Res = new RealP();
		if (B.getN() == 0 && B.coef[0] == 0)
			throw new RuntimeException("Divide by zero polynomial");
		if (A.getN() < B.getN()) {
			setRemain(A);
			return C;
		}
		C.setN(A.getN() - B.getN());
		C.coef[C.getN()] = A.coef[A.getN()] / B.coef[B.getN()];
		
		Res = mulP(C, B);
		Res = subP(A, Res);
		Res = divP(Res, B);
		Res = addP(C, Res);
		
		return Res;
	}
	
	public static RealP getRemain() {
		return Remain;
	}

	public static void setRemain(RealP remain) {
		Remain = remain;
	}
}
