package pt.MyDictionary.strategy;

import java.awt.GridLayout;

public class Remove extends Operation{
    public Remove(){
		super();
		getPanel().setLayout(new GridLayout(2,2));
		addContents();
		this.getFrame().setVisible(true);
    }
	@Override
	protected void addContents() {
		getFrame().getContentPane().add(getPanel());
		getPanel().add(this.getWordL());
		getPanel().add(this.getWordF1());
		getPanel().add(this.getExecute());
	}

}
