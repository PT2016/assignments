package Helpers;

import java.io.File;
import java.util.Scanner;

public class FileHelp {
	
	private static Scanner scanner;

	public static void openFile(String path) {
		try {
			scanner = new Scanner(new File(path));
		} catch (Exception e) {
			System.out.println("File not found!");
		}
	}

	public static String readFile(String path) {
		String a = "";
		if (scanner == null)
			openFile(path);

		if (scanner.hasNext()) {
			a = scanner.next();
		} else {
			a = "end of file";
		}
		return a;
	}

	public static void closeFile() {
		scanner.close();
	}
}
