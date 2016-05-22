package Helper;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextField;

public class UserInputChecker {

	public UserInputChecker() {

	}

	public String checkString(JTextField input) {
		String userInput = null;
		try {
			userInput = input.getText().trim();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input:" + input);
		}

		return userInput;

	}

	public ArrayList<String> checkArrayOfStrings(JTextField input) {
		String token = null;
		ArrayList<String> syno = new ArrayList<String>();
		String[] splitActionCommand = null;
		try {
			token = input.getText().trim();
			splitActionCommand = token.split(",");
		} catch (NumberFormatException e) {
			System.out.println("Invalid input:" + input);
		}
		Collections.addAll(syno, splitActionCommand);

		return syno;
	}

}
