package Ejercicio02;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Utilidades.MetodosGenericos;
/**
 * la clase contiene metodos para la funcionalidad del programa
 * @author Joaquin Alonso Perianez
 *
 */
public class Metodos {
	public static File ficheroObjetos;
	private static ObjectOutputStream oos = null;
	private static FileOutputStream fos = null;
	private static ObjectInputStream ois = null;
	private static FileInputStream fis = null;
	/**
	 * menu con varias opciones
	 * 1.-Guardar libros
	 * 2.-Mostrar libros
	 * 3.-Escribir xml
	 * 4.-Salir
	 */
	public static void menu() {
		ficheroObjetos = new File("libros.obj");
		int opcion = 0;
		do {
			System.out.println("1.-Guardar Libros");
			System.out.println("2.-MostrarLibros");
			System.out.println("3.-Escribir XML");
			System.out.println("4.-Salir");
			opcion = MetodosGenericos.pideNum("opción");
			switch (opcion) {
			case 1:
				guardarLibros();
				break;
			case 2:
				mostrarLibros(obtenerListaLibros());
				break;
			case 3:
				escribeXML(obtenerListaLibros());
				break;
			case 4:

				break;
			default:
				break;
			}
		} while (opcion != 4);
	}

	private static void escribeXML(ArrayList<Utilidades.Libro> listaLibros) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document documento = implementation.createDocument(null, "Libros", null);
			documento.setXmlVersion("1.0");
			for (int i = 0; i < listaLibros.size(); i++) {
				Element nodo = documento.createElement("libro");
				nodo.setAttribute("ISBN", listaLibros.get(i).isbn);
				documento.getDocumentElement().appendChild(nodo);
				MetodosGenericos.crearElemento("titulo", listaLibros.get(i).titulo, nodo, documento);
				MetodosGenericos.crearElemento("autor", listaLibros.get(i).autor, nodo, documento);
				MetodosGenericos.crearElemento("editorial", listaLibros.get(i).editorial, nodo, documento);
			}
			Source source = new DOMSource(documento);
			Result result = new StreamResult(new File("libros.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			System.out.println("\tXML creado");
		} catch (ParserConfigurationException e) {
			System.out.println("\tXML no creado");
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			System.out.println("\tXML no creado");
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			System.out.println("\tXML no creado");
			e.printStackTrace();
		} catch (TransformerException e) {
			System.out.println("\tXML no creado");
			e.printStackTrace();
		}
	}

	private static void mostrarLibros(ArrayList<Utilidades.Libro> listaLibros) {
		for (int i = 0; i < listaLibros.size(); i++) {
			System.out.println(listaLibros.get(i));
		}

	}

	private static void guardarLibros() {
		Utilidades.Libro libroAux = new Utilidades.Libro(MetodosGenericos.pideString("ISBN"),
				MetodosGenericos.pideString("título"), MetodosGenericos.pideString("autor"),
				MetodosGenericos.pideString("Editorial"));
		try {

			if (ficheroObjetos.exists()) {
				fos = new FileOutputStream(ficheroObjetos, true);
				oos = new Utilidades.MiObjectOutputStream(fos);
			} else {
				fos = new FileOutputStream(ficheroObjetos);
				oos = new ObjectOutputStream(fos);
			}
			oos.writeObject(libroAux);
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada o salida");
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {

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
		System.out.println("¿Deseas introducir más libross?");
		if (MetodosGenericos.pideString("si o no").equalsIgnoreCase("si")) {
			guardarLibros();
		}

	}

	private static ArrayList<Utilidades.Libro> obtenerListaLibros() {
		ArrayList<Utilidades.Libro> libros = null;
		if (!ficheroObjetos.exists() || ficheroObjetos == null) {
			System.out.println("No hay libros que mostrar");
		} else {
			try {
				fis = new FileInputStream(ficheroObjetos);
				ois = new ObjectInputStream(fis);
				Utilidades.Libro libroAux;
				libros = new ArrayList<>();
				while (true) {
					libroAux = (Utilidades.Libro) ois.readObject();
					libros.add(libroAux);
				}

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Fin de lectura");
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}

		}
		return libros;
	}
}
