package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Person;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class GenerateReport {
	private Document document;

	public GenerateReport() {
	}

	public void printReport(Person person, String message) {
		try {
			document = new Document();
			PdfWriter.getInstance(document,
					new FileOutputStream(person.getFirstName() + " " + person.getLastName() + ".pdf"));
			document.open();
			document.add(new Paragraph(person.toString() + " " + message + "\n"));
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
