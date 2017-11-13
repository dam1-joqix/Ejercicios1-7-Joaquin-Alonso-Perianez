package Ejercicio06;

import java.io.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import Utilidades.MetodosGenericos;

/**
 * Laq clase metodos guarda los metodos necesarios para dar funcionalidad al
 * programa
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class Metodos {
	/**
	 * Este metodo escribe un xml de libros pidiendo cada dato al usuario por teclado
	 */
	public static void escribeXML() {
		FileOutputStream fos = null;
		XMLOutputFactory factory = null;
		XMLStreamWriter xmlStreamWriter = null;

		int i = 0;
		try {
			fos = new FileOutputStream("src\\Ejercicio06\\libros.xml");
			factory = XMLOutputFactory.newInstance();
			xmlStreamWriter = factory.createXMLStreamWriter(fos, "UTF-8");
			System.out.println("Vamos a escribir un xml de libros");
			xmlStreamWriter.writeStartDocument();// abre xml
			xmlStreamWriter.writeStartElement("libros");// abre libros
			do {
				i++;
				System.out.println("vamos con el libro " + i);
				xmlStreamWriter.writeStartElement("libro");// abre libro
				xmlStreamWriter.writeAttribute("ISBN", MetodosGenericos.pideString("ISBN"));
				xmlStreamWriter.writeStartElement("titulo");// abre titulo
				xmlStreamWriter.writeCharacters(MetodosGenericos.pideString("título"));
				xmlStreamWriter.writeEndElement();// cierra titulo
				xmlStreamWriter.writeStartElement("autores");// abre autores
				do {
					xmlStreamWriter.writeStartElement("autor");// abre autor
					xmlStreamWriter.writeCharacters(MetodosGenericos.pideString("autor"));
					xmlStreamWriter.writeEndElement();// cierra autor
					System.out.println("¿Desea introducir más autores?");
				} while (MetodosGenericos.pideString("opcion").equalsIgnoreCase("si"));
				xmlStreamWriter.writeEndElement();// cierra autores
				xmlStreamWriter.writeStartElement("editorial");// abre editorial
				xmlStreamWriter.writeCharacters(MetodosGenericos.pideString("editorial"));
				xmlStreamWriter.writeEndElement();// cierra editorial
				xmlStreamWriter.writeEndElement();// cierra libro
				System.out.println("¿Desea introducir más libros?");
			} while (MetodosGenericos.pideString("respuesta").equalsIgnoreCase("si"));
			xmlStreamWriter.writeEndElement();// cierra libros
			xmlStreamWriter.writeEndDocument();// cierra xml
			System.out.println("XML creado");

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (XMLStreamException e) {
			
			e.printStackTrace();
		} finally {

			if (xmlStreamWriter != null) {
				try {
					xmlStreamWriter.flush();
					xmlStreamWriter.close();
				} catch (XMLStreamException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	}

}
