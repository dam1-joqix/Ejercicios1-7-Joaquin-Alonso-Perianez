package Utilidades;

import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Esta clase contiene metodos para la funcionalidad de todos los programas del
 * proyecto
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class MetodosGenericos {
	/**
	 * El metodo pide al usuario un String por teclado y lo devuelve En caso de
	 * producirse una excepcion lo vuelve a pedir Recibe un String como
	 * parametro que se usa para indicarle al usuario que dato debe introducir
	 * 
	 * @param tipo
	 *            de dato que queremos
	 * @return texto obtenido
	 */
	public static String pideString(String tipo) {
		Scanner scan;
		String variable1 = "";
		boolean booleano1 = true;
		System.out.println("Introduce " + tipo);
		scan = new Scanner(System.in);
		do {
			try {
				variable1 = scan.nextLine();
				booleano1 = false;

			} catch (Exception e) {
				System.out.println("Introduce de nuevo " + tipo);
			}
		} while (booleano1);
		return variable1;
	}

	/**
	 * El metodo pide al usuario un int por teclado y lo devuelve En caso de
	 * producirse una excepcion lo vuelve a pedir Recibe un String como
	 * parametro que se usa para indicarle al usuario que dato debe introducir
	 * 
	 * @param tipo
	 *            de dato que queremos
	 * @return texto obtenido
	 */
	public static int pideNum(String tipo) {
		Scanner scan;
		int variable1 = 0;
		boolean booleano1 = true;
		System.out.println("Introduce " + tipo);
		scan = new Scanner(System.in);
		do {
			try {
				variable1 = scan.nextInt();
				booleano1 = false;

			} catch (Exception e) {
				System.out.println("Introduce de nuevo " + tipo);
			}
		} while (booleano1);
		return variable1;
	}

	/**
	 * Crea un elemento de un xml con dom
	 * 
	 * @param elemento
	 *            nombre del elemento
	 * @param valor
	 *            contenido
	 * @param nodo
	 *            elemento padre
	 * @param document
	 *            documento que estamos modificando
	 */
	public static void crearElemento(String elemento, String valor, Element nodo, Document document) {
		Element element = document.createElement(elemento);
		Text text = document.createTextNode(valor);
		element.appendChild(text);
		nodo.appendChild(element);

	}
	/**
	 * Este metodo obtiene un nodo del xml
	 * 
	 * @param etiqueta
	 *            el nombre de la etiqueta del nodo
	 * @param elem
	 *            el elemento padre
	 * @return nodo
	 */
	public static String getNodo(String etiqueta, Element elem) {
		Node nodo = elem.getElementsByTagName(etiqueta).item(0).getFirstChild();
		return nodo.getTextContent();
	}
}
