package Ejercicio01;

import java.io.*;

import java.util.ArrayList;


import org.w3c.dom.*;

import Utilidades.MetodosGenericos;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 * La clase métodos contiene metodos para la funcionalidad del programa Contiene
 * un menu, un método para escribir un fichero de objetos, un método para leer
 * un fichero de objetos, un método para obtener un arrayList de contactos desde
 * el fichero, un método para crear un xml y métodos para falicitar la
 * introducción de datos por teclado
 * 
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class Metodos {
	private static File fichero = null;
	private static ObjectOutputStream oos = null;
	private static FileOutputStream fos = null;
	private static ObjectInputStream ois = null;
	private static FileInputStream fis = null;

	

	/**
	 * El metodo menú se usa para gestionar las distintas acciones que puede
	 * hacer el programa en funcion del numero entero que introduzca el usuario
	 * se llamara al método indicadp 1.-Guardar contacto 2.-mostrar contactos
	 * 3.-Guardar xml de contactos 4.-Salir
	 */
	public static void menu() {
		int opcion = 0;
		fichero = new File("src\\Ejercicio01\\contactos.obj");

		do {
			System.out.println("1.- Guardar nuevo contacto ");
			System.out.println("2.- Mostrar contactos");
			System.out.println("3.-Guardar contactos en XML");
			System.out.println("4.- Salir");
			opcion = MetodosGenericos.pideNum("opción");
			switch (opcion) {
			case 1:
				guardarContacto();
				break;
			case 2:
				mostrarContactos();
				break;
			case 3:

				escribeXML(leerContactos());
				break;
			case 4:
				System.out.println("Adios");
				break;
			default:
				System.out.println("Te has equivocado");
				break;

			}
		} while (opcion != 4);
	}

	/**
	 * Este metodo pide al usuario los datos de un nuevo contacto y escribe ese
	 * contacto en el fichero al finalizar pregunta al usuario si desea
	 * introducir otro contacto y si dice que sí el metodo se llama a si mismo
	 */
	private static void guardarContacto() {
		Contacto contactoAux = new Contacto(MetodosGenericos.pideString("nombre"), MetodosGenericos.pideString("Apellidos"), MetodosGenericos.pideString("e-mail"),
				MetodosGenericos.pideNum("teléfono"));
		try {

			if (fichero.exists()) {
				fos = new FileOutputStream(fichero, true);
				oos = new MiObjectOutputStream(fos);
			} else {
				fos = new FileOutputStream(fichero);
				oos = new ObjectOutputStream(fos);
			}
			oos.writeObject(contactoAux);
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
		System.out.println("¿Deseas introducir más contactos?");
		if (MetodosGenericos.pideString("si o no").equalsIgnoreCase("si")) {
			guardarContacto();
		}

	}

	/**
	 * Este metodo lee el fichero de contactos y va mostrando los contactos que
	 * hay en el
	 */
	private static void mostrarContactos() {
		if (!fichero.exists() || fichero == null) {
			System.out.println("No hay contactos que mostrar");
		} else {
			try {
				fis = new FileInputStream(fichero);
				ois = new ObjectInputStream(fis);
				Contacto contactoAux;
				while (true) {
					contactoAux = (Contacto) ois.readObject();
					System.out.println(contactoAux);
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

	}

	

	/**
	 * Este metodo escribe un xml a partir de un ArrayList de objetos de tipo
	 * contacto
	 * 
	 * @param contactos
	 *            lista de contactos que queremos guardar en el xml
	 */
	public static void escribeXML(ArrayList<Contacto> contactos) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Contactos", null);
			document.setXmlVersion("1.0");
			System.out.println("Creando documento XML");
			for (int i = 0; i < contactos.size(); i++) {
				Element nodo = document.createElement("contacto");
				document.getDocumentElement().appendChild(nodo);
				MetodosGenericos.crearElemento("nombre", contactos.get(i).getNombre(), nodo, document);
				MetodosGenericos.crearElemento("apellidos", contactos.get(i).getApellidos(), nodo, document);
				MetodosGenericos.crearElemento("email", contactos.get(i).getEmail(), nodo, document);
				MetodosGenericos.crearElemento("telefono", Integer.toString(contactos.get(i).getTelefono()), nodo, document);
			}
			Source source = new DOMSource(document);
			Result result = new StreamResult(new File("src//Ejercicio01//contactos.xml"));
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

	/**
	 * Este metodo lee el fichero de contactos y los devuelve a modo de
	 * ArrayList
	 */
	public static ArrayList<Contacto> leerContactos() {
		fichero = new File("src//Ejercicio01//contactos.obj");

		if (!fichero.exists() || fichero == null) {
			System.out.println("No hay contactos que mostrar");
			return null;
		} else {
			ArrayList<Contacto> contactos = new ArrayList<>();
			try {
				fis = new FileInputStream(fichero);
				ois = new ObjectInputStream(fis);
				Contacto contactoAux;
				System.out.println("Leyendo fichero de objetos de contactos");
				while (true) {
					contactoAux = (Contacto) ois.readObject();
					contactos.add(contactoAux);

				}

			} catch (FileNotFoundException e) {
				System.out.println("\tArchivo de objetos no encontrado");
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
			return contactos;
		}
	}
}
