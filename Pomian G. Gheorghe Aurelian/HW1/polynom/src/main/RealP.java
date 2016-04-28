package main;

public class RealP extends Polynom {
	public float[] coef = new float[30];
	
	public RealP() {
		initP();
		setN(0);
	}
	
	private void initP() {
		int i;
		for(i=0; i<30 ;i++)
			coef[i] = 0;
	}

}
