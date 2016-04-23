package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.swing.JOptionPane;

import Helpers.FileHelp;

public class Accounts {

	private static boolean admin;
	private static String path = "C:/a/Polipoly/P2/Accounts.txt";

	public static boolean searchAccount(String name, String pass, boolean justUsername) {
		boolean result = false;
		FileHelp.openFile(path);
		String fname, fpass, fadmin;

		fname = FileHelp.readFile(path);
		fpass = FileHelp.readFile(path);
		fadmin = FileHelp.readFile(path);

		while (!Objects.equals(fname, "end of file")) {
			if (justUsername) {
				if (Objects.equals(fname, name)) {
					result = true;
					break;
				}
			} else if (Objects.equals(fname, name) && Objects.equals(fpass, pass)) {
				setAdmin(Boolean.parseBoolean(fadmin));
				result = true;
				break;
			}
			fname = FileHelp.readFile(path);
			fpass = FileHelp.readFile(path);
			fadmin = FileHelp.readFile(path);
		}
		FileHelp.closeFile();
		return result;
	}

	public static void addAccount(String name, String pass, boolean admin) {
		try (PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter(path , true)))) {
			out.println(name + " " + pass + " " + admin);
		} catch (IOException e) {

		}
	}



	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean isAdmin() {
		return admin;
	}

	private static void setAdmin(boolean admin1) {
		admin = admin1;
	}
}