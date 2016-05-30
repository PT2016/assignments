package controllers;

import models.*;
import views.*;

public class Main {
	public static void main(String[] args) {
		Bank.deserialize();
		new LogInController(new LogIn("Bank"), false);
	}
}
