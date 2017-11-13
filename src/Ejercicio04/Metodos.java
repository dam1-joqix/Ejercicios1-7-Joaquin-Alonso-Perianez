package Ejercicio04;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Utilidades.MetodosGenericos;

/**
 * Esta clase contioene los metodos necesarios para dar funcionalidad al
 * programa
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class Metodos {
	/**
	 * Este metodo recorre un xml de libros y muestra sus datos
	 */
	public static void leerXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("libros.xml"));
			document.getDocumentElement().normalize();
			System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());
			NodeList libros = document.getElementsByTagName("libro");
			for (int i = 0; i < libros.getLength(); i++) {
				Node libro = libros.item(i);
				Element elemento = (Element) libro;
				System.out.println("ISBN: "+elemento.getAttribute("ISBN"));
				System.out.println("Titulo: " + MetodosGenericos.getNodo("titulo", elemento));
				System.out.println("Autor: " + MetodosGenericos.getNodo("autor", elemento));
				System.out.println("Editorial: " + MetodosGenericos.getNodo("editorial", elemento));
				
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	
}
