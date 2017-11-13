package Ejercicio07;

import java.io.*;
import java.util.ArrayList;
import javax.xml.stream.*;
import Ejercicio01.Contacto;
import Utilidades.*;

/**
 * La clase contiene los metodos necesarios para dar funcionalidad al programa
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
	private static File xml;
	private static XMLOutputFactory factory = null;
	private static XMLStreamWriter writer = null;

	/**
	 * Menu con opciones para leer escribir y crear xml de contactos
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
				mostrarContactos(leerContactos());
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
	 * Crea un xml a partir de una lista de contactos, en el xml se reflejan
	 * todos sus datos
	 * 
	 * @param leerContactos
	 *            lista de contactos
	 */
	private static void escribeXML(ArrayList<Contacto> leerContactos) {
		xml = new File("src\\Ejercicio07\\contactos.xml");

		try {
			fos = new FileOutputStream(xml);
			factory = XMLOutputFactory.newInstance();
			writer = factory.createXMLStreamWriter(fos, "UTF-8");
			System.out.println("\tEscribiendo XML");
			writer.writeStartDocument();// abre xml
			writer.writeStartElement("contactos");// abre contactos
			for (int i = 0; i < leerContactos.size(); i++) {
				System.out.println("\t\tAñadiendo contacto " + (i + 1));
				writer.writeStartElement("contacto");// abre contacto
				writer.writeStartElement("nombre");// abre nombre
				writer.writeCharacters(leerContactos.get(i).getNombre());
				writer.writeEndElement();// cierra nombre
				writer.writeStartElement("apellidos");// abre apellidos
				writer.writeCharacters(leerContactos.get(i).getApellidos());
				writer.writeEndElement();// cierraq apellidos
				writer.writeStartElement("email");// abre email
				writer.writeCharacters(leerContactos.get(i).getEmail());
				writer.writeEndElement();// cierra email
				writer.writeStartElement("telefono");// abre telefono
				writer.writeCharacters(Integer.toString(leerContactos.get(i).getTelefono()));
				writer.writeEndElement();// cierra telefono
				writer.writeEndElement();// cierra contacto
			}
			writer.writeEndElement();// cierra contactos
			writer.writeEndDocument();// cierra xml
			System.out.println("\tXML creado correctamente");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
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

	/**
	 * Este metodo recibe una lista de contactos y muestra los contactos que hay
	 * en ella
	 * 
	 * @param leerContactos
	 *            lista de contactos
	 */
	private static void mostrarContactos(ArrayList<Contacto> leerContactos) {
		for (int i = 0; i < leerContactos.size(); i++) {
			System.out.println(leerContactos.get(i));
		}

	}

	/**
	 * Este metodo pide al usuario los datos de un nuevo contacto y escribe ese
	 * contacto en el fichero al finalizar pregunta al usuario si desea
	 * introducir otro contacto y si dice que sí el metodo se llama a si mismo
	 */
	private static void guardarContacto() {
		Contacto contactoAux = new Contacto(MetodosGenericos.pideString("nombre"),
				MetodosGenericos.pideString("Apellidos"), MetodosGenericos.pideString("e-mail"),
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
