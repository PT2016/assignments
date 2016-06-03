package View;

import javax.swing.plaf.synth.SynthSeparatorUI;

import GUI.MainWindow;
import Model.Operations;
import Model.Polynomial;
import Model.Useful;

public class Main {

	public static void main(String[] args) {
		int[] a = { 1, 2, 3};
		int[] b = { 1, 1, 1};

		//MainWindow m = new 	MainWindow();
		Polynomial p = new Polynomial(a);
		Polynomial p1 = new Polynomial(b);

		Operations op = new Operations();
		
		 Polynomial rez = op.dividePolynomials(p, p1);
		  
		  for(int i = 0; i<=rez.getDegree(); i++){
		  System.out.println(rez.getThisCoeff(i));
		
		 }
		  p1 = new Polynomial(p.reverseCoefficients(a));
		 System.out.println(p1.toString());

}
}
