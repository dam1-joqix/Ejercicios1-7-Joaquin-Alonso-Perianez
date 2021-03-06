package Ejercicio03;

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
	 * Este metodo recorre un xml de contactos y muestra sus datos
	 */
	public static void leerXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("src\\Ejercicio01\\contactos.xml"));
			document.getDocumentElement().normalize();
			System.out.println("Elemento ra�z: " + document.getDocumentElement().getNodeName());
			NodeList personas = document.getElementsByTagName("contacto");
			for (int i = 0; i < personas.getLength(); i++) {
				Node contacto = personas.item(i);
				Element elemento = (Element) contacto;
				System.out.println("Nombre: " + MetodosGenericos.getNodo("nombre", elemento));
				System.out.println("Apellidos: " + MetodosGenericos.getNodo("apellidos", elemento));
				System.out.println("E-Mail: " + MetodosGenericos.getNodo("email", elemento));
				System.out.println("Tel�fono: " + MetodosGenericos.getNodo("telefono", elemento));
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
