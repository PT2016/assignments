package UserInterface;

import javax.swing.JTextArea;

public class OutputArea extends JTextArea{
	
	private static final long serialVersionUID = -9037894298398507284L;

	private OutputArea(){}
	
	private static OutputArea instance = new OutputArea();
	
	public static OutputArea getInstance(){
		return instance;
	}
}
