package Ejercicio03;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Metodos {
	public static void leerXML() {
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document document =builder.parse(new File("src\\Ejercicio01\\contactos.xml"));
			document.getDocumentElement().normalize();
			System.out.println("Elemento raíz: "+document.getDocumentElement().getNodeName());
			NodeList personas = document.getElementsByTagName("contacto");
			for (int i = 0; i < personas.getLength(); i++) {
				Node contacto =personas.item(i);
				Element elemento= (Element)contacto;
				System.out.println("Nombre: "+ getNodo("nombre", elemento));
				System.out.println("Apellidos: "+ getNodo("apellidos", elemento));
				System.out.println("E-Mail: "+ getNodo("email", elemento));
				System.out.println("Teléfono: "+ getNodo("telefono", elemento));
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private static String getNodo(String etiqueta, Element elem) {
		Node nodo = elem.getElementsByTagName(etiqueta).item(0).getFirstChild();
		return nodo.getTextContent();
	}
}
