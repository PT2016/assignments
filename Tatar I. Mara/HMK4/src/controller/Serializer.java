package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import models.Bank;



public class Serializer {
	public void SerializeBank(Bank bank){
		try{
			FileOutputStream fileOut=new FileOutputStream("bank.ser");;
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
		    out.writeObject(bank);
		    out.close();
		    fileOut.close();
		    System.out.printf("Serialized data is saved in bank.ser");
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}
	public Bank deserializeBank(){
		Bank bank=new Bank();
		try{
			FileInputStream fileIn=new FileInputStream("bank.ser");
			ObjectInputStream in=new ObjectInputStream(fileIn);
		    bank=(Bank)in.readObject();
		    in.close();
		    fileIn.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
		catch(ClassNotFoundException c){
			System.out.println("Bank class not found");
			c.printStackTrace();
		}
		
		return bank;
	}
}
