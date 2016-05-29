package pt.MyDictionary.strategy;

import java.awt.GridLayout;

public class Add extends Operation{
	public Add(){
		super();
		getPanel().setLayout(new GridLayout(4,2));
		addContents();
		this.getFrame().setVisible(true);
	}
	@Override
	protected void addContents() {
		getFrame().getContentPane().add(getPanel());
		getPanel().add(this.getWordL());
		getPanel().add(this.getWordF1());
		getPanel().add(this.getSynonymL());
		getPanel().add(this.getSynonymF1());
		getPanel().add(this.getDescriptionL());
		getPanel().add(this.getDescriptionF1());
		getPanel().add(this.getExecute());
	}

}
