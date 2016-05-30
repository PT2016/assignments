package UserInterface;

import javax.swing.JFrame;

public abstract class CustomizedFrame extends JFrame {

	private static final long serialVersionUID = 2743179795271197035L;

	abstract void setTheSize();
	abstract void setTheLocation();
	abstract void setTheTitle();
	abstract void addComponents();
	
	public final void adjustFrame(){
		setTheSize();
		setTheLocation();
		setTheTitle();
		addComponents();
	}
}
