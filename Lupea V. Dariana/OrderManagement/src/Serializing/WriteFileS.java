package Serializing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Model.OPD;
import Model.Warehouse;

/**
 * 
 * @author Dariana Lupea
 *
 */
public class WriteFileS {

	public WriteFileS(Warehouse o) {

		try {
			FileOutputStream fileOut = new FileOutputStream(
					"C:/Users/Dariana Lupea/Desktop/DariJavaSem2/OrderManagement/SerFile/dd.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in dd.ser\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public  WriteFileS(OPD o) {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"C:/Users/Dariana Lupea/Desktop/DariJavaSem2/OrderManagement/SerFile/orders.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in OPD.ser\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

}
