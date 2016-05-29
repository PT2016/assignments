package Model;

public class Useful {

	public String toString(Polynomial pol) {
		
		
		if (pol.getDegree() == 0)
			return "" + pol.getThisCoeff(0);
		if (pol.getDegree() == 1)
			return pol.getThisCoeff(1) + "x + " + pol.getThisCoeff(1);
		String s = pol.getThisCoeff(pol.getDegree()) + "x^" + pol.getDegree();
		for (int i = pol.getDegree() - 1; i >= 0; i--) {
			if (pol.getThisCoeff(i) == 0)
				continue;
			else if (pol.getThisCoeff(i) > 0)
				s = s + " + " + (pol.getThisCoeff(i));
			else if (pol.getThisCoeff(i) < 0)
				s = s + " - " + (-pol.getThisCoeff(i));
			if (i == 1)
				s = s + "x";
			else if (i > 1)
				s = s + "x^" + i;
		}
		return s;
	}

}
