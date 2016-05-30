package main;

public class IntegerP extends Polynom{
	public int[] coef = new int[30];

	public IntegerP() {
		initP();
		setN(0);
	}
	
	private void initP() {
		int i;
		for(i=0; i<30 ;i++)
			coef[i] = 0;
	}
}
