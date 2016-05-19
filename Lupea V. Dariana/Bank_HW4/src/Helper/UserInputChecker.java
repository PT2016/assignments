package Helper;

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

	public int checkNumber(JTextField number) {
		String userInput;
		int realNumber = 0;
		try {
			userInput = number.getText().trim();
			realNumber = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input:" + number);
		}
		return realNumber;
	}

}
